package com.service.producer.demo.scheduledTask;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.service.producer.demo.model.EventStatus;
import com.service.producer.demo.model.MessageEvent;
import com.service.producer.demo.repository.MessageEventRepository;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final String TOPIC = "my-topic";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private MessageEventRepository messageEventRepository;

	@Scheduled(fixedRate = 9000)
	public void processMessageEvents() {
		List<MessageEvent> events = messageEventRepository.findByEventStatus(EventStatus.CREATED);

		for (MessageEvent event : events) {
			try {
				int retryCount = event.getRetryCountAttempted();

				if (retryCount > 0) {
					CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC,
						event.getPayload());

					future.whenComplete((result, ex) -> {
						if (ex != null) {
							log.error("Failed to send message: " + ex.getMessage());
						} else {
							event.setEventStatus(EventStatus.PUBLISHED);
							event.setRetryCountAttempted(event.getRetryCountAttempted() - 1);

							messageEventRepository.save(event);
							log.info(
								"Message sent for event id: {}, updated status to PUBLISHED and decremented retry count",
								event.getId());
						}
					});

				} else {
					event.setEventStatus(EventStatus.FAILED);
					messageEventRepository.save(event);
				}

			} catch (Exception e) {
				log.error("Failed to send message for event id: {}", event.getId(), e);
			}
		}
	}
}
