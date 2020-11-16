package com.example.demo.controllers;

import com.example.demo.modules.student.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementCtrl {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1,"Jane Doe"),
            new Student(2,"John Doe"),
            new Student(3,"Mike Miles")
    );

    @GetMapping
    public List<Student> getAllStudents(){
        System.out.println("getAllStudents...");
        return STUDENTS;
    }

    @PostMapping
    public void registerStudent(@RequestBody Student student){
        System.out.println("registerStudent...");
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentID){
        System.out.println("deleteStudent...");
        System.out.println(studentID);
    }

    @PutMapping(path = "{studentId}" )
    public void updateStudent(@PathVariable("studentId") Integer studentID,@RequestBody Student student){
        System.out.println("updateStudent...");
        System.out.println(String.format("%s %s",studentID,student));
    }
}
