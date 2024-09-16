package com.ejercicio.ejercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ejercicio.ejercicio.entity.PagosEntity;

@Repository
public interface PagosRepository extends JpaRepository<PagosEntity, Long> {

	@Query("select p from PagosEntity p where p.id =:id")
	PagosEntity getStatusById(@Param("id") Long id);

}
