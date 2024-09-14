package com.ejercicio.ejercicio.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.ejercicio.ejercicio.entity.PagosEntity;
import com.ejercicio.ejercicio.modelo.Pagos;

public interface PagosService {
	ResponseEntity<Pagos> guardar(Pagos pagos);

	Optional<PagosEntity> getPagosById(Long id);

	void savePagos(PagosEntity pagosEntity);

	ResponseEntity<String> getStatusById(Long id);

}
