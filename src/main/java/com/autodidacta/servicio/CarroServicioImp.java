package com.autodidacta.servicio;


import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.autodidacta.dto.CarroDTO;
import com.autodidacta.entidad.Carro;
import com.autodidacta.entidad.Proveedor;
import com.autodidacta.excepciones.CarAppException;
import com.autodidacta.excepciones.ResourceNotFoundException;
import com.autodidacta.repositorio.CarroRepositorio;
import com.autodidacta.repositorio.ProveedorRepositorio;
import com.autodidacta.utils.PaginacionCarros;


@Service
public class CarroServicioImp implements CarroServicio {
	
	@Autowired
	private CarroRepositorio carroRepositorio;
	
	@Autowired
	private ProveedorRepositorio proveedorRepositorio;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CarroDTO ingresar(Long proveedorId, CarroDTO carroDTO) {
		//recibe la data en dto y lo pasa
		Carro carro = mapearEntidad(carroDTO);
		//Busca proveedor de vehiculo, con el parametro ingresado
		Proveedor proveedor = proveedorRepositorio.findById(proveedorId)
				.orElseThrow(() -> new ResourceNotFoundException("carro", "id" , proveedorId));
		//una vez lo encuentre asigna el id ingresado al proveedor para ser insertado en la bd
		carro.setProveedor(proveedor);
		//ingresa data a la bd
		Carro nuevoCarro = carroRepositorio.save(carro);
		//devuelve info en dto al servicio 
		return mapearDTO(nuevoCarro);
	}

	@Override
	public CarroDTO actualizar(Long proveedorId,Long carroId,CarroDTO solicitudCarroDTO) {
		
		
		//primero busca el carro por id
		Carro carro = carroRepositorio.findById(carroId)
				.orElseThrow(() -> new ResourceNotFoundException("Carro","id", carroId));
		//busca con el paramtero de proveedor id y lo llene en el cuerpo
		Proveedor proveedor = proveedorRepositorio.findById(proveedorId)
				.orElseThrow(() -> new ResourceNotFoundException("Proveedor", "id", proveedorId));
		
		if(!carro.getProveedor().getId().equals(proveedor.getId())) {
			//si el fk proveedorId de Carro no es igual al pk id de proveedor , tirara el siguiente error
			throw new CarAppException(HttpStatus.BAD_REQUEST, "El vehiculo no pertenece al proveedor");
		}
		//y luego el valor obtenido lo setea con la data entrante
				carro.setMarca(solicitudCarroDTO.getMarca());
				carro.setNombre(solicitudCarroDTO.getNombre());
				carro.setPrecio(solicitudCarroDTO.getPrecio());
				carro.setStock(solicitudCarroDTO.getStock());
		//luego convierte el dto a entidad para ser ingresado
		//carro = modelMapper.map(solicitudCarroDTO, Carro.class);
		
		//guarda esta data en la bd
		Carro carroActualizado = carroRepositorio.save(carro);
		//y retorna DTO serializado
		return mapearDTO(carroActualizado);
	}

	@Override
	public void eliminarCarro(Long carroId) {

		Carro carro = carroRepositorio.findById(carroId)
				.orElseThrow(() -> new ResourceNotFoundException("Carro", "id", carroId));
		
		carroRepositorio.delete(carro);

	}

	@Override
	public PaginacionCarros obtenerCarros(int numPag , int medidaPag,String ordenarPor, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
											 Sort.by(ordenarPor).ascending():
											 Sort.by(ordenarPor).descending();
		
		Pageable pageable = PageRequest.of(numPag, medidaPag,sort);
		
		Page<Carro> carros = carroRepositorio.findAll(pageable);
		
		List<Carro> listaCarros = carros.getContent();
		
		List<CarroDTO> contenido =listaCarros.stream().map(carrosCont -> mapearDTO(carrosCont)).collect(Collectors.toList());
		
		PaginacionCarros paginacionCarros = new PaginacionCarros();
		
		paginacionCarros.setCarros(contenido);
		paginacionCarros.setMedidaPagina(carros.getSize());
		paginacionCarros.setNumeroPagina(carros.getNumber());
		paginacionCarros.setTotalElementos(carros.getTotalElements());
		paginacionCarros.setTotalPaginas(carros.getTotalPages());
		paginacionCarros.setUltima(carros.isLast());
		
		return paginacionCarros;
		
	}

	@Override
	public CarroDTO obtenerCarroPorId(Long carroId) {
		
		Carro carro = carroRepositorio.findById(carroId)
				.orElseThrow(() -> new ResourceNotFoundException("Carro", "id", carroId));
		
		return mapearDTO(carro);
	}
	
	//CONVERSION DE ENTIDAD A DTO
	private CarroDTO mapearDTO(Carro carro) {
		
		CarroDTO carroDTO = modelMapper.map(carro, CarroDTO.class);
	
		return carroDTO;
	}
	
	//CONVERSION DE DTO A ENTIDAD
	private Carro mapearEntidad(CarroDTO carroDTO) {
		
		Carro carro = modelMapper.map(carroDTO, Carro.class);
		
		return carro;
	}

}
