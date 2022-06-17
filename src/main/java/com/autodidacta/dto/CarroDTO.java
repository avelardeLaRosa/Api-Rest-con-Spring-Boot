package com.autodidacta.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.autodidacta.entidad.Proveedor;

public class CarroDTO {
	//EN UN DTO EL TIPO DE DATO DEL ID DEBE SER PRIMITIVO , nose porq xd
	private long id;
	@NotEmpty(message = "Debe ingresar marca de carro")
	private String marca;
	@NotEmpty(message = "Nombre de vehiculo")
	private String nombre;
	@NotNull(message = "Debe ingresar precio")
	private double precio;
	@NotNull(message = "Debe ingresar stock")
	private int stock;
	private Proveedor proveedor;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	
	
	
}
