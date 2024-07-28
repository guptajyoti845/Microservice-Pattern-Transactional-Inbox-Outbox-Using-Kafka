package com.service.producer.demo.repository;

import com.service.producer.demo.model.EventStatus;
import com.service.producer.demo.model.MessageEvent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageEventRepository extends JpaRepository<MessageEvent, Long> {
	@Query("SELECT me FROM MessageEvent me WHERE me.eventStatus = :eventStatus")
	List<MessageEvent> findByEventStatus(@Param("eventStatus") EventStatus eventStatus);
}
