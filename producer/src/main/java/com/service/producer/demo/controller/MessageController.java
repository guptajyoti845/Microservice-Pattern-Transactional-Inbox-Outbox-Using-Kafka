package com.service.producer.demo.controller;

import com.service.producer.demo.dto.MessageEventDTO;
import com.service.producer.demo.model.EventStatus;
import com.service.producer.demo.model.MessageEvent;
import com.service.producer.demo.service.MessageEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private MessageEventService messageEventService;

	private static final String TOPIC = "my-topic";

	@GetMapping("/send")
	public MessageEventDTO sendMessage(@RequestParam("message") String message) {
		MessageEvent success = messageEventService.saveMessageEvent(message, EventStatus.CREATED);

		return success.toDTO();
	}
}
