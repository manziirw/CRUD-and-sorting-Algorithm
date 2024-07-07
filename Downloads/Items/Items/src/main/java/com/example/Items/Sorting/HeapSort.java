package com.example.Items.Sorting;

import com.example.Items.Student.Student;

public class HeapSort {

    public void heapSort(Student[] students) {
        int n = students.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(students, n, i);
        }

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            Student temp = students[0];
            students[0] = students[i];
            students[i] = temp;

            // call max heapify on the reduced heap
            heapify(students, i, 0);
        }
    }

    private void heapify(Student[] students, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (left < n && students[left].getAge() > students[largest].getAge()) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && students[right].getAge() > students[largest].getAge()) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            Student swap = students[i];
            students[i] = students[largest];
            students[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(students, n, largest);
        }
    }
}

