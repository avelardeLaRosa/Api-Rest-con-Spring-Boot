package com.autodidacta.servicio;

import java.util.List;

import com.autodidacta.dto.ProveedorDTO;
import com.autodidacta.utils.PaginacionProveedores;

public interface ProveedorServicio{
	
	public ProveedorDTO ingresar(ProveedorDTO proveedorDTO);
	
	public ProveedorDTO actualizar(Long proveedorId, ProveedorDTO proveedorDTO);
	
	public PaginacionProveedores listarProveedores(int numPag , int medidaPag,String ordenarPor, String sortDir);
	
	public ProveedorDTO listarPorId(Long proveedorId);
	
	public void eliminar(Long proveedorId);

}
