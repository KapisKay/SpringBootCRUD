package com.example.demo.RestController;

import com.example.demo.Service.StudentService;
import com.example.demo.Student.Student;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "api/v1/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getStudents(){

        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentID}")
    public void deleteStudent(@PathVariable("studentID") Long id ){
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentID}")
    public void updateStudent(@PathVariable("studentID")Long id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        studentService.updateStudent(id, name, email);
    }
}
