package com.autodidacta.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.autodidacta.dto.ProveedorDTO;
import com.autodidacta.entidad.Proveedor;
import com.autodidacta.excepciones.ResourceNotFoundException;
import com.autodidacta.repositorio.ProveedorRepositorio;
import com.autodidacta.utils.PaginacionCarros;
import com.autodidacta.utils.PaginacionProveedores;

@Service
public class ProveedorServicioImp implements ProveedorServicio {
	
	@Autowired
	private ProveedorRepositorio proveedorRepositorio;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ProveedorDTO ingresar(ProveedorDTO proveedorDTO) {
		
		Proveedor proveedor = mapearDTO(proveedorDTO);
		
		Proveedor nuevoProveedor = proveedorRepositorio.save(proveedor);
		
		return mapearEntidad(nuevoProveedor);
	}

	@Override
	public ProveedorDTO actualizar(Long proveedorId, ProveedorDTO proveedorDTO) {
		//traje al proveedor de la bd
		Proveedor proveedor = proveedorRepositorio.findById(proveedorId)
				.orElseThrow(() -> new ResourceNotFoundException("Proveedor", "id", proveedorId));
		//encontrado el proveedor de la bd , sus valores los reemplazo con la data dto
		proveedor.setNombre(proveedorDTO.getNombre());
		proveedor.setDireccion(proveedorDTO.getDireccion());
		//y esa info lo guardo en la bd
		Proveedor proveedorActualizado = proveedorRepositorio.save(proveedor);
		//para por ultimo devolverlo en dto al servidor
		return mapearEntidad(proveedorActualizado);
	}

	@Override
	public PaginacionProveedores listarProveedores(int numPag , int medidaPag,String ordenarPor, String sortDir) {
		
		//Orden en forma Ascendente
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
		//Agregamos numero de pagina,medida y orientacion
		Pageable pageable = PageRequest.of(numPag, medidaPag,sort);
		//devuelve todos los proveedores de la base de datos
		Page<Proveedor> proveedores = proveedorRepositorio.findAll(pageable);
		//la data obtenida de page se pasa a un instancia de tipo lista proveedor
		List<Proveedor> listaProveedores = proveedores.getContent();
		//el contenido devuelto lo transforma a dto
		List<ProveedorDTO> proveedoresDTO = listaProveedores.stream().map(contenidoProveedores -> mapearEntidad(contenidoProveedores)).collect(Collectors.toList());
		//Se hace una instancia de la clase PaginacionProveedores
		 PaginacionProveedores paginacionProveedores = new PaginacionProveedores();
		 //a esta instancia se le pasa la data recogida 
		 paginacionProveedores.setProveedores(proveedoresDTO);//se le pasa la lista de proveedores en dto
		 paginacionProveedores.setMedidaPagina(proveedores.getSize()); //obtiene medida de la data recogia en la bd
		 paginacionProveedores.setNumeroPagina(proveedores.getNumber());//obtiene el numero de la data recogida en la bd
		 paginacionProveedores.setTotalElementos(proveedores.getTotalElements());//recoger el total de elementos de la bd
		 paginacionProveedores.setTotalPaginas(proveedores.getTotalPages());//total de paginas
		 paginacionProveedores.setUltima(proveedores.isLast());//devuelve boolean si es la ultima pagina
		 
		 return paginacionProveedores;
	}

	@Override
	public ProveedorDTO listarPorId(Long proveedorId) {

		Proveedor proveedor = proveedorRepositorio.findById(proveedorId)
				.orElseThrow(()-> new ResourceNotFoundException("proveedor", "id", proveedorId));
		
		return mapearEntidad(proveedor);
	}

	@Override
	public void eliminar(Long proveedorId) {

		Proveedor proveedor = proveedorRepositorio.findById(proveedorId)
				.orElseThrow(() -> new ResourceNotFoundException("Proveedor", "id", proveedorId));
		
		proveedorRepositorio.delete(proveedor);
	}
	
	//DTO -> PROVEEDOR
	public Proveedor mapearDTO(ProveedorDTO proveedorDTO) {
		Proveedor proveedor = modelMapper.map(proveedorDTO, Proveedor.class);
		return proveedor;
	}
	
	//PROVEEDOR -> DTO
	public ProveedorDTO mapearEntidad(Proveedor proveedor) {
		ProveedorDTO proveedorDTO = modelMapper.map(proveedor, ProveedorDTO.class);
		return proveedorDTO;
	}

}
