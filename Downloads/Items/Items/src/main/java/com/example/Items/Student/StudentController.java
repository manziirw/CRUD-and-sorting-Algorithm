package com.example.Items.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Items.Sorting.SortingService;

import java.util.List;

@RestController
@RequestMapping(path="api/student")
public class StudentController {

    private final StudentService studentService;
    private final SortingService sortingService;

    @Autowired
    public StudentController(StudentService studentService, SortingService sortingService){
        this.studentService = studentService;
        this.sortingService = sortingService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getstudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @GetMapping("/sort/heap")
    public List<Student> getStudentsSortedByHeapName() {
        List<Student> students = studentService.getstudents();
        return sortingService.heapSortByName(students);
    }

    @GetMapping("/sort/quick")
    public List<Student> getStudentsSortedByQuickName() {
        List<Student> students = studentService.getstudents();
        return sortingService.quickSortByName(students);
    }

    @GetMapping("/sort/merge")
    public List<Student> getStudentsSortedByMergeName() {
        List<Student> students = studentService.getstudents();
        return sortingService.mergeSortByName(students);
    }

    @GetMapping("/sort/radix")
    public List<Student> getStudentsSortedByRadixName() {
        List<Student> students = studentService.getstudents();
        return sortingService.radixSortByName(students);
    }

    @GetMapping("/sort/bucket")
    public List<Student> getStudentsSortedByBucketName() {
        List<Student> students = studentService.getstudents();
        return sortingService.bucketSortByName(students);
    }
}
