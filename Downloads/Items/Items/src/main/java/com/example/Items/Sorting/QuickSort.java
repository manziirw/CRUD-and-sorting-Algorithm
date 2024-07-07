package com.example.Items.Sorting;

import com.example.Items.Student.Student;

public class QuickSort {

    public void quickSort(Student[] students, int low, int high) {
        if (low < high) {
            int pi = partition(students, low, high);

            quickSort(students, low, pi - 1);
            quickSort(students, pi + 1, high);
        }
    }

    private int partition(Student[] students, int low, int high) {
        Student pivot = students[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (students[j].getAge() < pivot.getAge()) {
                i++;

                // swap students[i] and students[j]
                Student temp = students[i];
                students[i] = students[j];
                students[j] = temp;
            }
        }

        // swap students[i+1] and students[high] (or pivot)
        Student temp = students[i + 1];
        students[i + 1] = students[high];
        students[high] = temp;

        return i + 1;
    }
}

