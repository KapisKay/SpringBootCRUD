package com.example.demo.ServiceImplementation;

import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.StudentService;
import com.example.demo.Student.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private  final StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail =studentRepository
                .findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException(
                    "student with id" + id + "does not exist");
        }
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateStudent(Long id,
                              String name,
                              String email) {
       Student student = studentRepository.findById(id)
               .orElseThrow( () -> new IllegalStateException(
                       "Student with id" + id + "does not exist"
               ));
       if( name != null &&
               name.length() > 0 &&
               !Objects.equals(student.getName(), name)){
           student.setName(name);
       }
       if( email != null &&
                email.length() > 0 &&
                Objects.equals(student.getEmail(),email)){
           Optional<Student> studentOptional = studentRepository
                   .findStudentByEmail(email);
           if(studentOptional.isPresent()){
               throw new IllegalStateException("email taken");
           }
           student.setEmail(email);
       }
    }
}
