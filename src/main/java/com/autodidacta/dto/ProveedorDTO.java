package com.autodidacta.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.autodidacta.entidad.Carro;

public class ProveedorDTO {
	
	private Long id;
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String direccion;
	private Set<Carro>carros;
	
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
