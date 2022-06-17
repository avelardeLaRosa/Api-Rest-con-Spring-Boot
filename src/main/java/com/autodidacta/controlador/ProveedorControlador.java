package com.autodidacta.controlador;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autodidacta.dto.ProveedorDTO;
import com.autodidacta.servicio.ProveedorServicio;
import com.autodidacta.utils.AppConstantes;
import com.autodidacta.utils.PaginacionProveedores;

@RestController
@RequestMapping("/vlexApi/")
public class ProveedorControlador {

	@Autowired
	private ProveedorServicio proveedorServicio;
	
	@PostMapping("proveedores/ingresarProveedor")
	public ResponseEntity<ProveedorDTO> ingresarProveedor(@RequestBody ProveedorDTO proveedorDTO){
		
		return new ResponseEntity<>(proveedorServicio.ingresar(proveedorDTO),HttpStatus.OK);
	}
	
	@PutMapping("proveedores/actualizarProveedor/{proveedorId}")
	public ResponseEntity<ProveedorDTO> actualizarProveedor(@PathVariable(value = "proveedorId")Long proveedorId,
			@Valid @RequestBody ProveedorDTO proveedorDTO){

		return new ResponseEntity<>(proveedorServicio.actualizar(proveedorId, proveedorDTO),HttpStatus.OK);
	}
	
	@GetMapping("proveedores/listaProveedores")
	public PaginacionProveedores listarProveedores(
			@RequestParam(value = "pageNum",defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO,required = false) int numeroPagina, 
			@RequestParam(value = "medidaPagina",defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO,required = false) int medida, 
			@RequestParam(value = "orderBy",defaultValue = AppConstantes.ORDENAR_POR_DEFECTO,required = false) String ordenarPor, 
			@RequestParam(value = "orientacion",defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO,required = false) String sortDir){
		
		return proveedorServicio.listarProveedores(numeroPagina, medida, ordenarPor, sortDir);
		
	}
	
	@GetMapping("proveedores/listaPorId/{proveedorId}")
	public ResponseEntity<ProveedorDTO> obtenerProveedorPorId(@PathVariable(value = "proveedorId")Long proveedorId){
		return new ResponseEntity<>(proveedorServicio.listarPorId(proveedorId),HttpStatus.OK);
	}
	
	@DeleteMapping("proveedores/eliminarProveedor/{proveedorId}")
	public ResponseEntity<String> eliminarProveedor(@PathVariable(value = "proveedorId")Long proveedorId){
		proveedorServicio.eliminar(proveedorId);
		return new ResponseEntity<>("Proveedor eliminado correctamente",HttpStatus.OK);
	}
	
}
