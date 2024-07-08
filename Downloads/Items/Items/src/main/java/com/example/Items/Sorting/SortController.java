package com.example.Items.Sorting;

import com.example.Items.Student.Student;
import com.example.Items.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/sort")
public class SortController {

    private final StudentService studentService;
    private final SortingService sortingService;

    @Autowired
    public SortController(StudentService studentService, SortingService sortingService) {
        this.studentService = studentService;
        this.sortingService = sortingService;
    }

    @GetMapping
    public String sortStudents(@RequestParam("sortAlgorithm") String sortAlgorithm, Model model) {
        List<Student> sortedStudents = null;
        switch (sortAlgorithm) {
            case "heap":
                sortedStudents = sortingService.heapSortByName(studentService.getstudents());
                break;
            case "quick":
                sortedStudents = sortingService.quickSortByName(studentService.getstudents());
                break;
            case "merge":
                sortedStudents = sortingService.mergeSortByName(studentService.getstudents());
                break;
            case "radix":
                sortedStudents = sortingService.radixSortByName(studentService.getstudents());
                break;
            case "bucket":
                sortedStudents = sortingService.bucketSortByName(studentService.getstudents());
                break;
            default:
                // Handle default case
                break;
        }
        model.addAttribute("sortedStudents", sortedStudents);
        return "sorted";
    }
}
