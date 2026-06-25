package com.example.crudhw.assignment.domain.repository;

import com.example.crudhw.assignment.domain.Assignment;
import com.example.crudhw.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByStudent(Student student);
}