package com.example.lms.controller;

import com.example.lms.entity.Assignment;
import com.example.lms.repository.AssignmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    private final AssignmentRepository repo;
    public AssignmentController(AssignmentRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Assignment> all() { return repo.findAll(); }

    @PostMapping
    public Assignment create(@RequestBody Assignment a) { return repo.save(a); }
}
