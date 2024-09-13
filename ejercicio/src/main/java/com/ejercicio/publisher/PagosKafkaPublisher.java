package com.ejercicio.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.ejercicio.ejercicio.entity.PagosEntity;

@Component
public class PagosKafkaPublisher {
	
	@Autowired
	KafkaTemplate<String, PagosEntity> kafkaTemplate;
	
	@Value("${spring.kafka.producer.topic}")
	String topic;
	
	public void send(PagosEntity pagos) {
		try {
			kafkaTemplate.send(topic, pagos);
		} catch (Exception e) {
			System.out.println("An error ocurred while ");
		}
	}

}
