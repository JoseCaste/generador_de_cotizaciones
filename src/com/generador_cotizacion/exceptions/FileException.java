package com.generador_cotizacion.exceptions;

public class FileException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public FileException() {
		super("El archivo de configuración no está definido, favor de crearlo");
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
