package com.ejercicio.ejercicio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ejercicio.ejercicio.entity.PagosEntity;
import com.ejercicio.ejercicio.modelo.Pagos;
import com.ejercicio.ejercicio.repository.PagosRepository;

@Service
public class PagosServiceImpl implements PagosService {
	@Autowired
	private PagosRepository pagosRepository;

	@Override
	public ResponseEntity<Pagos> guardar(Pagos pagos) {
		Optional<PagosEntity> pagosEntity = getPagosById(pagos.getId());
		if (!pagosEntity.isEmpty()) {
			return new ResponseEntity<>(pagos, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		PagosEntity pagosEnt = new PagosEntity();
		pagosEnt.setId(pagos.getId());
		pagosEnt.setBeneficiario(pagos.getBeneficiario());
		pagosEnt.setCantidadProductos(pagos.getCantidadProductos());
		pagosEnt.setConcepto(pagos.getConcepto());
		pagosEnt.setDeudor(pagos.getDeudor());
		pagosEnt.setMonto(pagos.getMonto());
		pagosEnt.setEstatusPago(pagos.getEstatusPago());
		pagosRepository.save(pagosEnt);
		return new ResponseEntity<>(pagos, HttpStatus.OK);
	}

	@Override
	public List<PagosEntity> getPagos() {
		return pagosRepository.findAll();
	}

	@Override
	public Optional<PagosEntity> getPagosById(Long id) {
		return pagosRepository.findById(id);
	}

	@Override
	public void savePagos(PagosEntity pagosEntity) {
		pagosRepository.save(pagosEntity);
	}

}
