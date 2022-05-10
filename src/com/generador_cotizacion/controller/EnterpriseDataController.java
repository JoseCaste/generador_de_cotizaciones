package com.generador_cotizacion.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.jasypt.util.text.AES256TextEncryptor;
import com.generador_cotizacion.enums.PropertiesKeys;
import com.generador_cotizacion.view.EnterpriseData;

public class EnterpriseDataController implements ActionListener {
	
	private EnterpriseData enterpriseData;
	
	public EnterpriseDataController(EnterpriseData enterpriseData) {
		this.enterpriseData = enterpriseData;
		loadListener();
	}

	private void loadListener() {
		enterpriseData.btnGuardarDatos.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(Objects.equals(arg0.getSource(), enterpriseData.btnGuardarDatos)) {
			final String enterpriseName = this.enterpriseData.txtEnterpriseName.getText();
			final String responsable = this.enterpriseData.txtResponsable.getText();
			final String locatedAt = this.enterpriseData.txtLocatedAt.getText();
			final String phone = this.enterpriseData.txtPhone.getText();
			final String cp = this.enterpriseData.txtCP.getText();
			
			try {
				File propertiesData = new File(PropertiesKeys.DIR.getId().concat("/dataenterprise.xml"));
				Properties properties = new Properties();
				if(!propertiesData.canRead()) 
					propertiesData.createNewFile(); 
					
				FileOutputStream fos = new FileOutputStream(propertiesData);
				
				AES256TextEncryptor encryptor = new AES256TextEncryptor();
				encryptor.setPassword(PropertiesKeys.PASSWORD.getId());
				
				properties.setProperty(PropertiesKeys.NAME.getId(), encryptor.encrypt(enterpriseName));
				properties.setProperty(PropertiesKeys.RESPONSABLE.getId(), encryptor.encrypt(responsable));
				properties.setProperty(PropertiesKeys.LOCATED_AT.getId(), encryptor.encrypt(locatedAt));
				properties.setProperty(PropertiesKeys.CP.getId(), encryptor.encrypt(cp));
				properties.setProperty(PropertiesKeys.PHONE.getId(), encryptor.encrypt(phone));
				
				properties.storeToXML(fos, "Datos de la empresa cotizadora");
				
				JOptionPane.showMessageDialog(null, "Se ha creado la configuraci�n");
				
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Hubo un error al crear el arhivo de configuraci�n");
			}
		}

	}

}
