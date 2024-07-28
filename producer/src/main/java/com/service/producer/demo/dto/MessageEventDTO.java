package com.service.producer.demo.dto;

import com.service.producer.demo.model.EventStatus;
import java.time.LocalDateTime;

public class MessageEventDTO {
	private Long id;
	private String payload;
	private EventStatus eventStatus;
	private LocalDateTime createdAt;
	private int retryCount;

	// Getters and Setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getPayload() { return payload; }
	public void setPayload(String payload) { this.payload = payload; }
	public EventStatus getEventStatus() { return eventStatus; }
	public void setEventStatus(EventStatus eventStatus) { this.eventStatus = eventStatus; }
	public LocalDateTime getCreatedAt() { return createdAt; }
	public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
	public int getRetryCount() { return retryCount; }
	public void setRetryCount(int retryCount) { this.retryCount = retryCount; }
}
