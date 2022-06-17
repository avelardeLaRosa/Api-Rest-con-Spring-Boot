package com.autodidacta.excepciones;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice //le dice a la clase que manejara excepciones handler, manejara todas las excepciones de nuestra aplicacion
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//ResponseEntityExceptionHandler : añade un metodo para indicar cuando no esta valido
	//este metodo manejara los notfounexception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetalles>manejarResourceNotFoundException(ResourceNotFoundException exception,WebRequest webRequest) {
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(),exception.getMessage(),webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles,HttpStatus.NOT_FOUND);//q no se ha encontrado
	}
	
	//este metodo maneja 
	@ExceptionHandler(CarAppException.class)
	public ResponseEntity<ErrorDetalles>manejarBlogAppException(CarAppException blogException,WebRequest webRequest) {
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(),blogException.getMessage(),webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles,HttpStatus.BAD_REQUEST);//mala peticion
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetalles>manejarGlobalException(Exception exception,WebRequest webRequest) {
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(),exception.getMessage(),webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//este metodo cuando lo que se le envia no es valido maneja la excepcion 
	@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {
			Map<String, String>errores=new HashMap<>();
			ex.getBindingResult().getAllErrors().forEach((error)->{
				String nombreCampo = ((FieldError)error).getField();//para obtener el campo que bota el error
				String mensaje = error.getDefaultMessage();
				errores.put(nombreCampo, mensaje);
			});//obtiene todos los errores
			return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);
		}
	
	
	
}
