package com.autodidacta.utils;

import java.util.List;

import com.autodidacta.dto.CarroDTO;
import com.autodidacta.entidad.Carro;

public class PaginacionCarros {
	
	private List<CarroDTO>carros;
	private int numeroPagina;
	private int medidaPagina;
	private long totalElementos;
	private int totalPaginas;
	private boolean ultima;
	
	

	
	public PaginacionCarros() {
		
	}


	public int getNumeroPagina() {
		return numeroPagina;
	}


	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}


	public int getMedidaPagina() {
		return medidaPagina;
	}


	public void setMedidaPagina(int medidaPagina) {
		this.medidaPagina = medidaPagina;
	}


	public long getTotalElementos() {
		return totalElementos;
	}


	public void setTotalElementos(long totalElementos) {
		this.totalElementos = totalElementos;
	}


	public int getTotalPaginas() {
		return totalPaginas;
	}


	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}


	public boolean isUltima() {
		return ultima;
	}


	public void setUltima(boolean ultima) {
		this.ultima = ultima;
	}


	public List<CarroDTO> getCarros() {
		return carros;
	}


	public void setCarros(List<CarroDTO> carros) {
		this.carros = carros;
	}
	
	
	
	

}
