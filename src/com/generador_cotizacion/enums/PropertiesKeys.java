package com.generador_cotizacion.enums;

public enum PropertiesKeys {
	NAME("name"),RESPONSABLE("responsable"),LOCATED_AT("ubicacion"),PHONE("contacto"), PASSWORD("GENERADOR_HUESCA");
	
	private String id;
	
	private PropertiesKeys(final String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}
