package com.example.lms.controller;

import com.example.lms.dto.SubmissionRequest;
import com.example.lms.entity.Assignment;
import com.example.lms.entity.Submission;
import com.example.lms.entity.User;
import com.example.lms.repository.AssignmentRepository;
import com.example.lms.repository.SubmissionRepository;
import com.example.lms.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
    private final SubmissionRepository repo;
    private final AssignmentRepository assignmentRepo;
    private final UserRepository userRepo;

    public SubmissionController(SubmissionRepository repo, AssignmentRepository assignmentRepo,
            UserRepository userRepo) {
        this.repo = repo;
        this.assignmentRepo = assignmentRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<Submission> all() {
        return repo.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Submission> byUser(@PathVariable Long userId) {
        return repo.findByStudent_Id(userId);
    }

    @PostMapping
    public ResponseEntity<?> submit(@RequestBody SubmissionRequest request) {
        Optional<Assignment> assignmentOpt = assignmentRepo.findById(request.getAssignmentId());
        Optional<User> studentOpt = userRepo.findById(request.getStudentId());
        if (!assignmentOpt.isPresent() || !studentOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Invalid assignmentId or studentId");
        }
        Submission s = new Submission();
        s.setAssignment(assignmentOpt.get());
        s.setStudent(studentOpt.get());
        s.setContent(request.getContent());
        s.setSubmittedAt(LocalDateTime.now());
        return ResponseEntity.ok(repo.save(s));
    }
}
