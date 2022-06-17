package com.autodidacta.servicio;

import java.util.List;
import java.util.Optional;

import com.autodidacta.dto.CarroDTO;
import com.autodidacta.utils.PaginacionCarros;


public interface CarroServicio {
	//LA CHAMBA LA HARA EL CARRODTO YA QUE LE ENTIDAD QUE ES EL DAO SU UNICA FUNCION ES LA DE PERSISTIR DATOS EN LA BD
	public CarroDTO ingresar(Long proveedorId,CarroDTO carroDTO);

	public CarroDTO actualizar(Long proveedorId,Long carroId,CarroDTO carroDTO);
	//para actualizar se necesitara el id de proveedor en caso cambie , el id del vehiculo para buscarlo y el cuerpo q se le pasara
	
	public void eliminarCarro(Long carroId);
	
	public PaginacionCarros obtenerCarros(int numPag , int medidaPag,String ordenarPor, String sortDir);
	
	public CarroDTO obtenerCarroPorId(Long carroId);
	
}
