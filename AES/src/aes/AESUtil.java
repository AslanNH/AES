package aes;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;


public class AESUtil {
	private static String password = "zgyhxakfb0000000";
	private static String IV = "1234567891011222";
	@SuppressWarnings("static-access")
	//文件加密的实现方法
	public static void encryptFile(String fileName,String encryptedFileName){
		try {
			FileInputStream fis = new FileInputStream(fileName);
			FileOutputStream fos = new FileOutputStream(encryptedFileName);
			
			SecretKeySpec encryKey= new SecretKeySpec(password.getBytes(),"AES");//加密秘钥
			
			IvParameterSpec iv = new IvParameterSpec(IV.getBytes());//获取系统时间作为IV
			
			Cipher cipher = Cipher.getInstance("AES/CFB/PKCS5Padding");
			cipher.init(cipher.ENCRYPT_MODE, encryKey,iv);
			
			CipherInputStream cis=new CipherInputStream(fis, cipher);
			
			byte[] buffer=new byte[1024];
			int n=0;
			while((n=cis.read(buffer))!=-1){
				fos.write(buffer,0,n);		
			}
			cis.close();
			fos.close();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	@SuppressWarnings("static-access")
	//文件解密的实现代码
	public static void decryptedFile(String encryptedFileName,String decryptedFileName){
		
			try {
				FileInputStream fis = new FileInputStream(encryptedFileName);
				FileOutputStream fos = new FileOutputStream(decryptedFileName);	
				
				SecretKeySpec key= new SecretKeySpec(password.getBytes(),"AES");					
				IvParameterSpec iv= new IvParameterSpec(IV.getBytes());
				Cipher cipher = Cipher.getInstance("AES/CFB/PKCS5Padding");
				cipher.init(cipher.DECRYPT_MODE, key,iv);
				CipherInputStream cis= new CipherInputStream(fis, cipher);												
				byte[] buffer=new byte[1024];
				int n=0;
				while((n=cis.read(buffer))!=-1){
					fos.write(buffer,0,n);		
				}
				cis.close();
				fos.close();
				JOptionPane.showMessageDialog(null, "解密成功");	
				
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidAlgorithmParameterException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
	}
	public static void main(String[] args) {
		encryptFile("/opt/BaiduExporter-master.zip","/opt/b.zip");
		decryptedFile("/opt/b.zip","/opt/c.zip");
	}
}
