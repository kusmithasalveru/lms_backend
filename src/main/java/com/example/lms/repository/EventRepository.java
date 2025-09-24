package com.example.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lms.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {}
