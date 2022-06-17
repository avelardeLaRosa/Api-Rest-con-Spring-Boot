package com.autodidacta.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autodidacta.dto.CarroDTO;
import com.autodidacta.servicio.CarroServicio;
import com.autodidacta.utils.AppConstantes;
import com.autodidacta.utils.PaginacionCarros;

@RestController
@RequestMapping("/vlexApi/")
public class CarroControlador {
	
	@Autowired
	private CarroServicio carroServicio;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/carros/{proveedorId}/ingresarVehiculo")
	public ResponseEntity<CarroDTO>insertarCarro(
			@PathVariable(value = "proveedorId") Long proveedorId,
			@Valid @RequestBody CarroDTO carroDTO){
		
		return new ResponseEntity<>(carroServicio.ingresar(proveedorId, carroDTO),HttpStatus.CREATED);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/carros/{proveedorId}/actualizarVehiculo/{carroId}")
	public ResponseEntity<CarroDTO> actualizarCarro(
			@PathVariable(value = "proveedorId") Long proveedorId,
			@PathVariable(value = "carroId") Long carroId,
			@Valid @RequestBody CarroDTO carroDTO){
		
		CarroDTO carroActualizado = carroServicio.actualizar(proveedorId,carroId, carroDTO);
		
		return new ResponseEntity<>(carroActualizado,HttpStatus.OK);
		
	}
	
	@GetMapping("/carros/listarVehiculos")
	public ResponseEntity<PaginacionCarros> listarCarros(@RequestParam(value = "numPag",defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO,required = false) int numPag , 
			@RequestParam(value = "medidaPag",defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO,required = false)  int medidaPag,
			@RequestParam(value = "ordenarPor",defaultValue = AppConstantes.ORDENAR_POR_DEFECTO,required = false) String ordenarPor, 
			@RequestParam(value = "sortDir",defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO,required = false) String sortDir){
		
		return new ResponseEntity<>(carroServicio.obtenerCarros(numPag, medidaPag, ordenarPor, sortDir),HttpStatus.OK);
	}
	
	@GetMapping("/carros/listarPorId/{carroId}")
	public ResponseEntity<CarroDTO> listarCarroPorId(@PathVariable(name = "carroId")Long carroId){
		//devuelve el cuerpo json en postman
		CarroDTO carroDTO = carroServicio.obtenerCarroPorId(carroId);
		return new ResponseEntity<>(carroDTO,HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/carros/deleteCarro/{carroId}")
	public ResponseEntity<String> eliminarCarro(@PathVariable(name = "carroId")Long carroId) {
		//devuele un mensaje en postman como eliminacion satisfactoria
		carroServicio.eliminarCarro(carroId);
		return new ResponseEntity<>("Se elimino el vehiculo correctamente",HttpStatus.OK);
	}
}
