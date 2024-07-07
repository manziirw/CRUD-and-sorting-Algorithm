package com.example.Items.Sorting;
import com.example.Items.Student.Student;
import java.util.LinkedList;
import java.util.List;

public class RadixSort {

    public void radixSort(Student[] students) {
        int maxAge = getMaxAge(students);
        for (int exp = 1; maxAge / exp > 0; exp *= 10) {
            countSort(students, exp);
        }
    }

    private void countSort(Student[] students, int exp) {
        int n = students.length;
        Student[] output = new Student[n];
        int[] count = new int[10];
        for (Student student : students) {
            count[(student.getAge() / exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            output[count[(students[i].getAge() / exp) % 10] - 1] = students[i];
            count[(students[i].getAge() / exp) % 10]--;
        }
        System.arraycopy(output, 0, students, 0, n);
    }

    private int getMaxAge(Student[] students) {
        int max = students[0].getAge();
        for (Student student : students) {
            if (student.getAge() > max) {
                max = student.getAge();
            }
        }
        return max;
    }
}

