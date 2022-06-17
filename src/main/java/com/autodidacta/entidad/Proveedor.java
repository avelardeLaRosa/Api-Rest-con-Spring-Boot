package com.autodidacta.entidad;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "proveedor", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})//nombre de proveedor unico
public class Proveedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre",nullable = false)
	private String nombre;
	@Column(name = "direccion",nullable = false)
	private String direccion;
	
	@JsonBackReference
	@OneToMany(mappedBy = "proveedor",cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<Carro> carros = new HashSet<>();
	
	public Proveedor() {}
	
	
	public Proveedor(Long id, String nombre, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Set<Carro> getCarros() {
		return carros;
	}
	public void setCarros(Set<Carro> carros) {
		this.carros = carros;
	}
	


	
	
	
	
	
	
	

}
