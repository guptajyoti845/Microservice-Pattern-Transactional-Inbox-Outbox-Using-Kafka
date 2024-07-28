package com.service.consumer.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

  private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
  @Autowired private InboxService inboxService;

  @KafkaListener(
      topics = "my-topic",
      groupId = "my-group",
      containerFactory = "kafkaListenerContainerFactory")
  public void consume(String message, Acknowledgment ack) {
    try {
      inboxService.save(message);
      ack.acknowledge();
    } catch (Exception e) {
      log.error("Failed to save message: " + e.getMessage());
    }
  }
}
