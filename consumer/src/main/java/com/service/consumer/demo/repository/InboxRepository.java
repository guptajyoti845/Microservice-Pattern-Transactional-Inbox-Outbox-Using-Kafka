package com.service.consumer.demo.repository;

import com.service.consumer.demo.model.EventStatus;
import com.service.consumer.demo.model.InboxEvent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InboxRepository extends JpaRepository<InboxEvent, Long> {
	@Query("SELECT me FROM InboxEvent me WHERE me.eventStatus = :eventStatus")
	List<InboxEvent> findByEventStatus(@Param("eventStatus") EventStatus eventStatus);
}
