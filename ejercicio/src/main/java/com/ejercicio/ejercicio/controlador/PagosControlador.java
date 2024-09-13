package com.ejercicio.ejercicio.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.ejercicio.entity.PagosEntity;
import com.ejercicio.ejercicio.modelo.Pagos;
import com.ejercicio.ejercicio.service.PagosService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/pagos")
public class PagosControlador {
	@Autowired
	private PagosService pagosService;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${spring.kafka.producer.topic}")
	String topic;

	@PostMapping("/agregar")
	public ResponseEntity<Pagos> guardar(@RequestBody Pagos pagos) {
		return pagosService.guardar(pagos);
	}

	@GetMapping("/obtener")
	public List<PagosEntity> getPagos() {
		return pagosService.getPagos();
	}

	@GetMapping("/obtener/{id}")
	public Optional<PagosEntity> getPagosById(@PathVariable("id") Long id) {
		return pagosService.getPagosById(id);
	}

	@PatchMapping("/modificar/{id}/{estatusPago}")
	public ResponseEntity<?> updatePagosById(@PathVariable("id") Long id,
			@PathVariable("estatusPago") String estatusPago) {

		PagosEntity pagosEntity = pagosService.getPagosById(id).get();
		ObjectMapper objectMapper = new ObjectMapper();

		if (pagosEntity == null) {
			return ResponseEntity.ok("No se encontró pago");
		} else {
			pagosEntity.setEstatusPago(estatusPago);
			pagosService.savePagos(pagosEntity);
			String entity = null;
			try {
				entity = objectMapper.writeValueAsString(pagosEntity);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			kafkaTemplate.send(topic, entity);

		} // hhtstatus 204
		return ResponseEntity.ok("Operación exitosa");
	}
}
