package com.example.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lms.entity.Submission;
import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByStudent_Id(Long studentId);
}
