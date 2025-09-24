package com.example.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lms.entity.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {}
