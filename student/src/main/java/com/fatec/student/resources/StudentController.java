package com.fatec.student.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.student.dto.StudentRequest;
import com.fatec.student.dto.StudentResponse;
import com.fatec.student.resources.services.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @GetMapping
        public ResponseEntity<List<StudentResponse>> getStudents() {
            return ResponseEntity.ok(studentService.getStudents());
        }

    @GetMapping("{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable int id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {

        this.studentService.deleteStudentById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<StudentResponse> save(@RequestBody StudentRequest student) { 
        StudentResponse newStudent = this.studentService.save(student);
        URI location = ServletUriComponentsBuilder
                                                .fromCurrentRequest()
                                                .path("/{id}")
                                                .buildAndExpand(newStudent.id())
                                                .toUri();

        return ResponseEntity.created(location).body(newStudent);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> uptade(@PathVariable int id, @RequestBody StudentRequest student){
        this.studentService.update(id, student);;
        return ResponseEntity.ok().build();
    }
    

}
