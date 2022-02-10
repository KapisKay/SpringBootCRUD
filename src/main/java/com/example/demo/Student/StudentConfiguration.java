package com.example.demo.Student;

import com.example.demo.Repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
            Student konadu = new Student(
                    "Konadu", "tt",
                    LocalDate.of(1990,03,3)
                   );
            Student judge = new Student(
                    "Judge", "qq",
                    LocalDate.of(1993,07,13)
                    );
            repository.saveAll(
                    List.of(konadu,judge)
            );
        };
    }
}
