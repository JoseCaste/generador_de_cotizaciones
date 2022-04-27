package com.generador_cotizacion.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTes {

	public static void main(String[] args) throws IOException {
		
		Properties props = new Properties();
		File file =new File("/home/jose/Documents/PDFs/user.xml");
		if(!file.canRead())
			file.createNewFile();
		
		FileOutputStream fos = new FileOutputStream(file);
		props.setProperty("key1", "Value3");
		props.setProperty("key2", "Value2");
		
		props.storeToXML(fos, "Datos de empresa cotizadora");
		fos.close();

	}

}
