package com.autodidacta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autodidacta.entidad.Proveedor;

public interface ProveedorRepositorio extends JpaRepository<Proveedor, Long> {

}
