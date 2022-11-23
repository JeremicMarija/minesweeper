package com.marija.students.controller;

import com.marija.students.model.Mesto;
import com.marija.students.model.Student;
import com.marija.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public @ResponseBody ResponseEntity<Student> save(@Valid @RequestBody Student student){

//        return ResponseEntity.ok(studentService.createStudent(student));

        return new ResponseEntity<Student>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Student>>findAll(){

        List<Student> studenti = studentService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(studenti);
    }

    @GetMapping("/get/{studentId}")
    public @ResponseBody ResponseEntity<Student>getStudent(@PathVariable String studentId){

        Optional<Student> student = studentService.findByID(studentId);

        if (student.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(student.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{studentId}/fakultet/{fakultetId}")
    public Student assignFakultetToStudent(@PathVariable String studentId, @PathVariable String fakultetId){
        return studentService.assignFakultetToStudent(studentId,fakultetId);
    }
}
