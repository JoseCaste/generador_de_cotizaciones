package com.generador_cotizacion.enums;

public enum Elements {

	TOTAL(0),UNIDAD_MEDIDA(1), CODIGO(2), DESCRIPTION(3),UNIT_PRICE(4),SALE(5),IMPORTE(6);

	private int id;
	Elements(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
