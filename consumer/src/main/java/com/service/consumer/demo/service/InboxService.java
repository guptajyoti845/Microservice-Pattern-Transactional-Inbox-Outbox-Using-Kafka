package com.service.consumer.demo.service;

import com.service.consumer.demo.model.EventStatus;
import com.service.consumer.demo.model.InboxEvent;
import com.service.consumer.demo.repository.InboxRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InboxService {

  @Autowired private InboxRepository inboxRepository;

  public void save(String payload) {
    InboxEvent inboxEvent = new InboxEvent();
    inboxEvent.setPayload(payload);
    inboxEvent.setEventStatus(EventStatus.CREATED);
    inboxEvent.setCreatedAt(LocalDateTime.now());
    inboxRepository.save(inboxEvent);
  }
}
