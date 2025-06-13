package com.example.intershiptracker.interntracker.controller;

import com.example.intershiptracker.interntracker.entity.InternshipApplication;
import com.example.intershiptracker.interntracker.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InternshipController {

    @Autowired
    private InternshipRepository repository;

    @GetMapping("/applications")
    public List<InternshipApplication> getAll() {
        return repository.findAll();
    }

    @GetMapping("/application/{id}")
    public Optional<InternshipApplication> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @GetMapping("/applications/status/{status}")
    public List<InternshipApplication> getByStatus(@PathVariable String status) {
        return repository.findByStatus(status);
    }

    @PostMapping("/application")
    public InternshipApplication create(@RequestBody InternshipApplication app) {
        return repository.save(app);
    }

    @PutMapping("/application/{id}")
    public InternshipApplication update(@PathVariable Long id, @RequestBody InternshipApplication updatedApp) {
        InternshipApplication app = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));
        app.setStudentName(updatedApp.getStudentName());
        app.setCompany(updatedApp.getCompany());
        app.setRole(updatedApp.getRole());
        app.setStatus(updatedApp.getStatus());
        return repository.save(app);
    }

    @DeleteMapping("/application/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
