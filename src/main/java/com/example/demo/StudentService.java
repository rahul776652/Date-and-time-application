package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student){
        student.setCreatedDate(LocalDateTime.now());
        return studentRepository.save(student);
    }

    public List<Student> students(){
        List<Student>studentList=studentRepository.findAll();
        return studentList;
    }
    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    public void updateStudent(Student student, Long id) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isPresent()) {
            Student foundStudent = existingStudent.get();
            foundStudent.setName(student.getName());
            foundStudent.setEmail(student.getEmail());
            foundStudent.setCreatedDate(LocalDateTime.now());
            studentRepository.save(foundStudent);
        }
    }

    public List<Student> getStudentsByDate(LocalDateTime date) {
        LocalDateTime startOfDay = date.with(LocalTime.MIN);
        LocalDateTime endOfDay = date.with(LocalTime.MAX);
        return studentRepository.findByCreatedDateBetween(startOfDay, endOfDay);
    }
    public List<Student> getStudentsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        LocalDateTime startOfDay = startDate.with(LocalTime.MIN);
        LocalDateTime endOfDay = endDate.with(LocalTime.MAX);
        return studentRepository.findByCreatedDateBetween(startOfDay, endOfDay);
    }


    public List<Student> getAllstudents (Student student){
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    public Student getStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            throw new IllegalStateException("Student with id " + id + " not found.");
        }
    }

    public Student getStudentByName(String name){
        Optional<Student> optionalStudent = studentRepository.findByName(name);
        if(optionalStudent.isPresent()) {
            return optionalStudent.get();
        }
        return null;
    }


}
