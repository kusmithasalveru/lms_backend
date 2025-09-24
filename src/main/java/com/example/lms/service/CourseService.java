package com.example.lms.service;

import org.springframework.stereotype.Service;
import com.example.lms.entity.Course;
import com.example.lms.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository repo;
    public CourseService(CourseRepository repo) { this.repo = repo; }
    public List<Course> all() { return repo.findAll(); }
    public Optional<Course> get(Long id) { return repo.findById(id); }
    public Course save(Course c) { return repo.save(c); }
    public void delete(Long id) { repo.deleteById(id); }
}
