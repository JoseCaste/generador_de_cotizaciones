package com.generador_cotizacion.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.jasypt.util.text.AES256TextEncryptor;

import com.generador_cotizacion.enums.PropertiesKeys;

public class PropertiesTes {

	/*static {
	    try{
	    	Properties props2 = new Properties();
			File file2 =new File("/home/jose/Documents/PDFs/dataenterprise.xml");
			
			FileInputStream inputStream = new FileInputStream(file2);
			props2.load(inputStream);
			
			AES256TextEncryptor aes256TextEncryptor = new AES256TextEncryptor();
			
			aes256TextEncryptor.setPassword(PropertiesKeys.PASSWORD.getId());
			
			//props.getProperty(aes256TextEncryptor.decrypt(PropertiesKeys.NAME.getId()));
			String key = props2.getProperty(PropertiesKeys.NAME.getId());
			System.out.println(aes256TextEncryptor.decrypt(key));
			inputStream.close();
	    }catch(Exception e){
	        
	        e.printStackTrace();
	    }
	}*/
	public static void main(String[] args) throws IOException {
		
		Properties props = new Properties();
		File file =new File("/home/jose/Documents/PDFs/user.xml");
		if(!file.canRead())
			file.createNewFile();
		
		FileOutputStream fos = new FileOutputStream(file);
		props.setProperty("key1", "Value33");
		props.setProperty("key2", "Value2");
		
		props.storeToXML(fos, "Datos de empresa cotizadora");
		fos.close();

		File file2 = new File("/home/jose/Documents/PDFs/dataenterprise.xml");
		
		FileInputStream fis = new FileInputStream(file2);
		Properties properties2 = new Properties();
		
		AES256TextEncryptor encryptor = new AES256TextEncryptor();
		encryptor.setPassword(PropertiesKeys.PASSWORD.getId());
		
		properties2.loadFromXML(fis);
		System.out.println(encryptor.decrypt(properties2.getProperty(PropertiesKeys.NAME.getId())));
		
		
		
	}

}
