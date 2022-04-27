package com.generador_cotizacion.model;

public class EnterpriseDataPayload {
	
	private String enterpriseName;
	private String responsable;
	private String locatedAt;
	private String phone;
	
	public EnterpriseDataPayload(String enterpriseName, String responsable, String locatedAt, String phone) {
		super();
		this.enterpriseName = enterpriseName;
		this.responsable = responsable;
		this.locatedAt = locatedAt;
		this.phone = phone;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getLocatedAt() {
		return locatedAt;
	}

	public void setLocatedAt(String locatedAt) {
		this.locatedAt = locatedAt;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
