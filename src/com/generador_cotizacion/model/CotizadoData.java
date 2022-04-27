package com.generador_cotizacion.model;

public class CotizadoData {

	private String nameEnterprise;
	private String responsable;
	private String email;
	private String phone;
	private String atendidoBy;
	
	public CotizadoData(String nameEnterprise, String responsable, String email, String phone, String atendidoBy) {
		this.nameEnterprise = nameEnterprise;
		this.responsable = responsable;
		this.email = email;
		this.phone = phone;
		this.atendidoBy = atendidoBy;
	}

	public String getNameEnterprise() {
		return nameEnterprise;
	}

	public void setNameEnterprise(String nameEnterprise) {
		this.nameEnterprise = nameEnterprise;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAtendidoBy() {
		return atendidoBy;
	}

	public void setAtendidoBy(String atendidoBy) {
		this.atendidoBy = atendidoBy;
	}
	
	
	
}
