package com.generador_cotizacion.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import com.generador_cotizacion.model.EnterpriseDataPayload;
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
			EnterpriseDataPayload dataPayload = new EnterpriseDataPayload(enterpriseName, responsable, locatedAt, phone);
		}

	}

}
