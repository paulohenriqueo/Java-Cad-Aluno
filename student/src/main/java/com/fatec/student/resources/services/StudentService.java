package com.fatec.student.resources.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.student.entities.Student;
import com.fatec.student.repositories.StudentRepositoriry;

@Service
public class StudentService {

    @Autowired
    private StudentRepositoriry studentRepositoriry;

    public List<Student> getStudents(){
        return studentRepositoriry.findAll();
    }
}
