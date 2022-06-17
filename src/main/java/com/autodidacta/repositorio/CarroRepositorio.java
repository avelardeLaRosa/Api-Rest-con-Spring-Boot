package com.autodidacta.repositorio;



import org.springframework.data.jpa.repository.JpaRepository;


import com.autodidacta.entidad.Carro;


public interface CarroRepositorio extends JpaRepository<Carro, Long> {

	
}
