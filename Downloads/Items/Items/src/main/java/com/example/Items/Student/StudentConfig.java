package com.example.Items.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student manzi = new Student(
                    "manzi",
                    25,
                    "manzi@gmail.com",
                    LocalDate.of(1990, Month.JANUARY, 21)
            );

            Student david = new Student(
                    "David",
                    22,
                    "David@gmail.com",
                    LocalDate.of(2003, Month.MARCH, 21)
            );
            Student Alice = new Student(
                    "Alice",
                    27,
                    "Alice@gmail.com",
                    LocalDate.of(2007, Month.MARCH, 7)
            );
            // Save students to the database
            repository.saveAll(List.of(manzi, david,Alice));
        };
    }
}
