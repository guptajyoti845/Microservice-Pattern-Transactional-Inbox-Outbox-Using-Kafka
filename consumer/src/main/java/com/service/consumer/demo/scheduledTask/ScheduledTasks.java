package com.service.consumer.demo.scheduledTask;

import com.service.consumer.demo.model.EventStatus;
import com.service.consumer.demo.model.InboxEvent;
import com.service.consumer.demo.repository.InboxRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
  private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

  @Autowired private InboxRepository inboxRepository;

  @Scheduled(fixedRate = 9000)
  public void processInboxEvents() {
    List<InboxEvent> events = inboxRepository.findByEventStatus(EventStatus.CREATED);

    for (InboxEvent event : events) {
      try {
        String payload = event.getPayload();
        log.info("Consumed payload: "+payload);

        event.setEventStatus(EventStatus.CONSUMED);
        inboxRepository.save(event);
      } catch (Exception e) {
        log.error("Failed to process the event", e);
      }
    }
  }
}
