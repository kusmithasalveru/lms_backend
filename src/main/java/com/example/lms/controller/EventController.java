package com.example.lms.controller;

import com.example.lms.entity.Event;
import com.example.lms.repository.EventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventRepository repo;
    public EventController(EventRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Event> all() { return repo.findAll(); }

    @PostMapping
    public Event create(@RequestBody Event e) { return repo.save(e); }
}
