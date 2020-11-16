package com.example.demo.controllers;

import com.example.demo.modules.student.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentCtrl {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1,"Jane Doe"),
            new Student(2,"John Doe"),
            new Student(3,"Mike Miles")
    );

    @GetMapping(path="/{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
        return STUDENTS.stream().filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(()-> new IllegalStateException(String.format("Student %s does not exist",studentId)));
    }
}
