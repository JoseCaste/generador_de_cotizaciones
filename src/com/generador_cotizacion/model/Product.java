package com.generador_cotizacion.model;

public class Product {

	private Integer totalItem;
	
	private String unidadMedida;
	
	private String codigo;
	
	private String description;
	
	private Double UnitPrice;
	
	private Integer sale;
	
	private Double importe;

	
	
	public Product() {}

	public Product(Integer totalItem, String unidadMedida, String codigo, String description, Double unitPrice,
			Integer sale, Double importe) {
		this.totalItem = totalItem;
		this.unidadMedida = unidadMedida;
		this.codigo = codigo;
		this.description = description;
		UnitPrice = unitPrice;
		this.sale = sale;
		this.importe = importe;
	}

	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		UnitPrice = unitPrice;
	}

	public Integer getSale() {
		return sale;
	}

	public void setSale(Integer sale) {
		this.sale = sale;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "Product [totalItem=" + totalItem + ", unidadMedida=" + unidadMedida + ", codigo=" + codigo
				+ ", description=" + description + ", UnitPrice=" + UnitPrice + ", sale=" + sale + ", importe="
				+ importe + "]";
	}
	
	
}
