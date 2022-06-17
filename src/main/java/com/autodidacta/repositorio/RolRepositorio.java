package com.autodidacta.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autodidacta.entidad.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Long> {
	
	public Optional<Rol> findByNombre(String nombre);

}
