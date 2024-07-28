package com.service.producer.demo.service;

import com.service.producer.demo.model.EventStatus;
import com.service.producer.demo.model.MessageEvent;
import com.service.producer.demo.repository.MessageEventRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageEventService {

	public static final int MAX_RETRY_COUNT = 3;

	@Autowired
	private MessageEventRepository messageEventRepository;

	public MessageEvent saveMessageEvent(String payload, EventStatus eventStatus) {
		MessageEvent messageEvent = new MessageEvent();
		messageEvent.setPayload(payload);
		messageEvent.setEventStatus(eventStatus);
		messageEvent.setCreatedAt(LocalDateTime.now());
		messageEvent.setRetryCountAttempted(MAX_RETRY_COUNT);

		return messageEventRepository.save(messageEvent);
	}
}
