package com.example.Items.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Student> studentByEmail = studentRepository.findByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Email exists, please use another one.");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with id " + studentId + " does not exist.");
        }
        studentRepository.deleteById(studentId);
    }

    public List<Student> getstudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public void updateStudent(Long id, Student updatedStudent) {
        Optional<Student> existingStudentOptional = studentRepository.findById(id);
        if (existingStudentOptional.isEmpty()) {
            throw new IllegalStateException("Student with id " + id + " does not exist.");
        }

        Student existingStudent = existingStudentOptional.get();
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setDob(updatedStudent.getDob());

        studentRepository.save(existingStudent);
    }
}
