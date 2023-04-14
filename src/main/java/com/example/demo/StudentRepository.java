package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByCreatedDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
    List<Student> findByCreatedDate(LocalDateTime date);
    List<Student> findByCreatedDateGreaterThan(LocalDateTime date);

    Optional<Student> findByName(String name);
}
