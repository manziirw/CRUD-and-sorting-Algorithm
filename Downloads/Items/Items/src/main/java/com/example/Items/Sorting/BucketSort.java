package com.example.Items.Sorting;

import com.example.Items.Student.Student;

import java.io.OptionalDataException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {

    public void bucketSort(Student[] students) {
        int n = students.length;
        List<Student>[] buckets = new List[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (Student student : students) {
            int bucketIndex = hash(student);
            buckets[bucketIndex].add(student);
        }

        for (List<Student> bucket : buckets) {
            Collections.sort(bucket, (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
        }

        int index = 0;
        for (List<Student> bucket : buckets) {
            for (Student student : bucket) {
                students[index++] = student;
            }
        }
    }

    private int hash(Student student) {
        // Example hash function, adjust as needed
        OptionalDataException students = null;
        return student.getName().length() % students.length;
    }
}
