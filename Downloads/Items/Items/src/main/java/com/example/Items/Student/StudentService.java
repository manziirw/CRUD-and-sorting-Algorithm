package com.example.Items.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public void addNewStudent(Student student) {
       Optional<Student> studentbyemail = studentRepository.findByEmail(student.getEmail());
       if(studentbyemail.isPresent()){
           throw new IllegalStateException("email exists use another");
       }
      studentRepository.save(student);
    }
    public void deleteStudent(Long studentId){
        boolean exists =studentRepository.existsById(studentId);
        if(!exists){
            throw  new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    public List<Student> getstudents(){
        return studentRepository.findAll();
    }
}
