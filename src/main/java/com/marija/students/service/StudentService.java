package com.marija.students.service;


import com.marija.students.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();
    Student findByID(String brojIndeksa);

}
