package com.ejercicio.ejercicio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.ejercicio.ejercicio.entity.PagosEntity;
import com.ejercicio.ejercicio.modelo.Pagos;

public interface PagosService {
	ResponseEntity<Pagos> guardar(Pagos pagos);

	List<PagosEntity> getPagos();

	Optional<PagosEntity> getPagosById(Long id);
	
	void savePagos(PagosEntity pagosEntity);

}
