package com.example.Items.Sorting;

import com.example.Items.Student.Student;

public class MergeSort {

    public void mergeSort(Student[] students, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(students, left, mid);
            mergeSort(students, mid + 1, right);
            merge(students, left, mid, right);
        }
    }

    private void merge(Student[] students, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Student[] L = new Student[n1];
        Student[] R = new Student[n2];

        System.arraycopy(students, left, L, 0, n1);
        for (int j = 0; j < n2; j++) {
            R[j] = students[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i].getName().compareToIgnoreCase(R[j].getName()) <= 0) {
                students[k] = L[i];
                i++;
            } else {
                students[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            students[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            students[k] = R[j];
            j++;
            k++;
        }
    }
}


