package com.example.intershiptracker.interntracker.controller;

import com.example.intershiptracker.interntracker.entity.InternshipApplication;
import com.example.intershiptracker.interntracker.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired
    private InternshipRepository repository;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("applications", repository.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("application", new InternshipApplication());
        return "form";
    }

    @PostMapping("/save")
    public String saveApplication(@ModelAttribute InternshipApplication application) {
        repository.save(application);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        InternshipApplication app = repository.findById(id).orElseThrow();
        model.addAttribute("application", app);
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String deleteApplication(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/filter/{status}")
    public String filterByStatus(@PathVariable String status, Model model) {
        model.addAttribute("applications", repository.findByStatus(status));
        return "index";
    }
}
