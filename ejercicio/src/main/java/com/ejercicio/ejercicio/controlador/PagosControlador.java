package com.ejercicio.ejercicio.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<?> guardar(@RequestBody Pagos pagos) {
		return pagosService.guardar(pagos);
	}

	@GetMapping("/obtener/estatus/{id}")
	public ResponseEntity<String> getStatusById(@PathVariable("id") Long id) {
		return pagosService.getStatusById(id);
	}

	@PatchMapping("/modificar/{id}/{estatusPago}")
	public ResponseEntity<?> updatePagosById(@PathVariable("id") Long id,
			@PathVariable("estatusPago") String estatusPago) {

		PagosEntity pagosEntity = pagosService.estatusPorId(id);

		if (pagosEntity == null) {
			return new ResponseEntity<>("No se encontró pago", HttpStatus.NO_CONTENT);
		} else {
			pagosEntity.setEstatusPago(estatusPago);
			pagosService.savePagos(pagosEntity);
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				String pagosEn = objectMapper.writeValueAsString(pagosEntity);
				kafkaTemplate.send(topic, pagosEn);
			} catch (JsonProcessingException e) {
				return new ResponseEntity<>("Error al enviar mensaje", HttpStatus.NO_CONTENT);
			}

		}
		return ResponseEntity.ok("Operación exitosa");
	}
}
