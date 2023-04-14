package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class StudentController {
    @Autowired
    private StudentService studentService;


    @GetMapping("/byDate")
    public ResponseEntity<List<Student>> getStudentsByDate(
            @RequestParam("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<Student> students = studentService.getStudentsByDate(date);
        return ResponseEntity.ok(students);
    }
     @PostMapping("post")
    public ResponseEntity<Student>registerNewStudent(@RequestBody Student student){
         Student student1 = studentService.addStudent(student);
         return ResponseEntity.status(HttpStatus.CREATED).body(student1);
    }
    @GetMapping("/studentsBetweenDates")
    public ResponseEntity<List<Student>> getStudentsBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Student> students = studentService.getStudentsBetweenDates(startDate, endDate);
        return ResponseEntity.ok(students);
    }
    @DeleteMapping("delete/{id}")
    public void deleteStudents(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

    @GetMapping("get")
    public List<Student> students (Student student){
        List<Student> studentList = studentService.getAllstudents(student);
        return studentList;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){
        studentService.updateStudent(student, id);
        Student updatedStudent = studentService.getStudentById(id);
        return ResponseEntity.ok(updatedStudent);
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> optionalStudent = Optional.ofNullable(studentService.getStudentById(id));
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getByName")
    public ResponseEntity<Student> getStudentByName(@RequestParam("name") String name) {
        Optional<Student> optionalStudent = Optional.ofNullable(studentService.getStudentByName(name));
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
