package com.service.producer.demo.model;

import com.service.producer.demo.dto.MessageEventDTO;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MessageEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String payload;
	private EventStatus eventStatus;
	private LocalDateTime createdAt;
	private int retryCountAttempted;

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public EventStatus getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(EventStatus eventStatus) {
		this.eventStatus = eventStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public int getRetryCountAttempted() {
		return retryCountAttempted;
	}

	public void setRetryCountAttempted(int retryCountAttempted) {
		this.retryCountAttempted = retryCountAttempted;
	}

	public MessageEventDTO toDTO() {
		MessageEventDTO dto = new MessageEventDTO();
		dto.setId(id);
		dto.setPayload(payload);
		dto.setEventStatus(eventStatus);
		dto.setCreatedAt(createdAt);
		dto.setRetryCount(retryCountAttempted);
		return dto;
	}
}

