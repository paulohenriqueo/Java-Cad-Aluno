package com.fatec.student.resources.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.student.entities.Student;
import com.fatec.student.repositories.StudentRepositoriry;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

    @Autowired
    private StudentRepositoriry studentRepositoriry;

    public List<Student> getStudents(){
        return studentRepositoriry.findAll();
    }

    public Student getStudentById(int id){
        return studentRepositoriry.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Aluno não cadstrado")
        );
    }

    public void deleteStudentById(int id){
        if (this.studentRepositoriry.existsById(id)) {
            this.studentRepositoriry.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Aluno não cadstrado");
        }
    }
    public Student save(Student student){
        return this.studentRepositoriry.save(student);
    }

    public void update (int id, Student student){
        try {
            Student aux = studentRepositoriry.getReferenceById(id);
            aux.setName(student.getName());
            aux.setCourse(student.getCourse());
            this.studentRepositoriry.save(aux);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Aluno não cadastrado");
        }
    }

}
