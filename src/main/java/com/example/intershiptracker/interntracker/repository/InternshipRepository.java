package com.example.intershiptracker.interntracker.repository;

import com.example.intershiptracker.interntracker.entity.InternshipApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InternshipRepository extends JpaRepository<InternshipApplication, Long> {
    List<InternshipApplication> findByStatus(String status);
}
