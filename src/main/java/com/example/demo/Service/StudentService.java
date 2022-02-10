package com.example.demo.Service;

import com.example.demo.Student.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface StudentService {
    public List<Student> getStudents();

    void addNewStudent(Student student);

    void deleteStudent(Long id);

    void updateStudent(Long id, String name, String email);
}


