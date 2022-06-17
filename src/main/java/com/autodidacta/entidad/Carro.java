package com.autodidacta.entidad;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "carro", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})}) //nombre de vehiculo unico por tabla
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Carro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String marca;
	private String nombre;
	private double precio;
	private int stock;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)//muchos vehiculos le pertenecen a unproveedor
	@JoinColumn(name = "proveedor_id",nullable = false)
	private Proveedor proveedor;
	
	public Carro() {
		
	}

	public Carro(Long id, String marca, String nombre, double precio, int stock, Proveedor proveedor) {
		super();
		this.id = id;
		this.marca = marca;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.proveedor = proveedor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
