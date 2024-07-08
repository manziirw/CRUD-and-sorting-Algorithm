package com.example.Items.Student;


import com.example.Items.Sorting.SortingService;
import com.example.Items.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(path = "api/student")
public class StudentController {

    private final StudentService studentService;
    private final SortingService sortingService;

    @Autowired
    public StudentController(StudentService studentService, SortingService sortingService) {
        this.studentService = studentService;
        this.sortingService = sortingService;
    }

    @GetMapping
    public CustomResponse<CollectionModel<EntityModel<Student>>> getStudents() {
        List<Student> students = studentService.getstudents();
        List<EntityModel<Student>> studentModels = students.stream()
                .map(student -> EntityModel.of(student,
                        linkTo(methodOn(StudentController.class).getStudentById(student.getId())).withSelfRel(),
                        linkTo(methodOn(StudentController.class).getStudents()).withRel("students")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Student>> collectionModel = CollectionModel.of(studentModels,
                linkTo(methodOn(StudentController.class).getStudents()).withSelfRel(),
                linkTo(methodOn(StudentController.class).getStudentsSortedByHeapName()).withRel("sort_heap"),
                        linkTo(methodOn(StudentController.class).getStudentsSortedByQuickName()).withRel("sort-quick"),
                        linkTo(methodOn(StudentController.class).getStudentsSortedByMergeName()).withRel("sort-merge"),
                        linkTo(methodOn(StudentController.class).getStudentsSortedByRadixName()).withRel("sort-radix"),
                        linkTo(methodOn(StudentController.class).getStudentsSortedByBucketName()).withRel("sort-bucket"));

        return new CustomResponse<>("List of all students", collectionModel);
    }

    @GetMapping("/{id}")
    public CustomResponse<EntityModel<Student>> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalStateException("Student not found"));
        EntityModel<Student> entityModel = EntityModel.of(student,
                linkTo(methodOn(StudentController.class).getStudentById(id)).withSelfRel(),
                linkTo(methodOn(StudentController.class).getStudents()).withRel("students"));

        return new CustomResponse<>("Student details", entityModel);
    }

    @PostMapping
    public CustomResponse<EntityModel<Student>> registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
        EntityModel<Student> entityModel = EntityModel.of(student,
                linkTo(methodOn(StudentController.class).getStudentById(student.getId())).withSelfRel(),
                linkTo(methodOn(StudentController.class).getStudents()).withRel("students"));

        return new CustomResponse<>("Student registered successfully", entityModel);
    }

    @DeleteMapping(path = "{studentId}")
    public CustomResponse<Void> deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return new CustomResponse<>("Student deleted successfully", null);
    }

    @GetMapping("/sort/heap")
    public CustomResponse<CollectionModel<EntityModel<Student>>> getStudentsSortedByHeapName() {
        List<Student> students = sortingService.heapSortByName(studentService.getstudents());
        CollectionModel<EntityModel<Student>> collectionModel = createSortedCollectionModel(students, "heap");
        return new CustomResponse<>("Students sorted by heap sort", collectionModel);
    }

    @GetMapping("/sort/quick")
    public CustomResponse<CollectionModel<EntityModel<Student>>> getStudentsSortedByQuickName() {
        List<Student> students = sortingService.quickSortByName(studentService.getstudents());
        CollectionModel<EntityModel<Student>> collectionModel = createSortedCollectionModel(students, "quick");
        return new CustomResponse<>("Students sorted by quick sort", collectionModel);
    }

    @GetMapping("/sort/merge")
    public CustomResponse<CollectionModel<EntityModel<Student>>> getStudentsSortedByMergeName() {
        List<Student> students = sortingService.mergeSortByName(studentService.getstudents());
        CollectionModel<EntityModel<Student>> collectionModel = createSortedCollectionModel(students, "merge");
        return new CustomResponse<>("Students sorted by merge sort", collectionModel);
    }

    @GetMapping("/sort/radix")
    public CustomResponse<CollectionModel<EntityModel<Student>>> getStudentsSortedByRadixName() {
        List<Student> students = sortingService.radixSortByName(studentService.getstudents());
        CollectionModel<EntityModel<Student>> collectionModel = createSortedCollectionModel(students, "radix");
        return new CustomResponse<>("Students sorted by radix sort", collectionModel);
    }

    @GetMapping("/sort/bucket")
    public CustomResponse<CollectionModel<EntityModel<Student>>> getStudentsSortedByBucketName() {
        List<Student> students = sortingService.bucketSortByName(studentService.getstudents());
        CollectionModel<EntityModel<Student>> collectionModel = createSortedCollectionModel(students, "bucket");
        return new CustomResponse<>("Students sorted by bucket sort", collectionModel);
    }

    private CollectionModel<EntityModel<Student>> createSortedCollectionModel(List<Student> students, String sortType) {
        List<EntityModel<Student>> studentModels = students.stream()
                .map(student -> EntityModel.of(student,
                        linkTo(methodOn(StudentController.class).getStudentById(student.getId())).withSelfRel()))
                .collect(Collectors.toList());


        Link sortLink = linkTo(methodOn(StudentController.class).getStudentsSortedByHeapName()).withRel("sort-heap");
        Link quickSortLink = linkTo(methodOn(StudentController.class).getStudentsSortedByQuickName()).withRel("sort-quick");
        Link mergeSortLink = linkTo(methodOn(StudentController.class).getStudentsSortedByMergeName()).withRel("sort-merge");
        Link radixSortLink = linkTo(methodOn(StudentController.class).getStudentsSortedByRadixName()).withRel("sort-radix");
        Link bucketSortLink = linkTo(methodOn(StudentController.class).getStudentsSortedByBucketName()).withRel("sort-bucket");

        return CollectionModel.of(studentModels,sortLink, quickSortLink, mergeSortLink, radixSortLink, bucketSortLink);
    }
}
