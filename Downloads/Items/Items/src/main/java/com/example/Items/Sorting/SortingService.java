package com.example.Items.Sorting;

import com.example.Items.Student.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SortingService {

    // Heap Sort by Name
    public List<Student> heapSortByName(List<Student> students) {
        if (students == null || students.size() <= 1) {
            return students;
        }

        int n = students.size();

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyByName(students, n, i);
        }

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            Collections.swap(students, 0, i);

            // Call max heapify on the reduced heap
            heapifyByName(students, i, 0);
        }

        return students;
    }

    private void heapifyByName(List<Student> students, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && students.get(left).getName().compareTo(students.get(largest).getName()) > 0) {
            largest = left;
        }

        if (right < n && students.get(right).getName().compareTo(students.get(largest).getName()) > 0) {
            largest = right;
        }

        if (largest != i) {
            Collections.swap(students, i, largest);

            // Recursively heapify the affected sub-tree
            heapifyByName(students, n, largest);
        }
    }

    // Quick Sort by Name
    public List<Student> quickSortByName(List<Student> students) {
        if (students == null || students.size() <= 1) {
            return students;
        }

        quickSortRecursiveByName(students, 0, students.size() - 1);

        return students;
    }

    private void quickSortRecursiveByName(List<Student> students, int low, int high) {
        if (low < high) {
            int pi = partitionByName(students, low, high);

            quickSortRecursiveByName(students, low, pi - 1);
            quickSortRecursiveByName(students, pi + 1, high);
        }
    }

    private int partitionByName(List<Student> students, int low, int high) {
        Student pivot = students.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (students.get(j).getName().compareTo(pivot.getName()) < 0) {
                i++;
                Collections.swap(students, i, j);
            }
        }

        Collections.swap(students, i + 1, high);
        return i + 1;
    }

    // Merge Sort by Name
    public List<Student> mergeSortByName(List<Student> students) {
        if (students == null || students.size() <= 1) {
            return students;
        }

        mergeSortRecursiveByName(students);

        return students;
    }

    private void mergeSortRecursiveByName(List<Student> students) {
        if (students.size() > 1) {
            int mid = students.size() / 2;

            List<Student> left = students.subList(0, mid);
            List<Student> right = students.subList(mid, students.size());

            mergeSortRecursiveByName(left);
            mergeSortRecursiveByName(right);

            mergeByName(students, left, right);
        }
    }

    private void mergeByName(List<Student> students, List<Student> left, List<Student> right) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getName().compareTo(right.get(j).getName()) < 0) {
                students.set(k++, left.get(i++));
            } else {
                students.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            students.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            students.set(k++, right.get(j++));
        }
    }

    // Radix Sort by Name
    public List<Student> radixSortByName(List<Student> students) {
        if (students == null || students.size() <= 1) {
            return students;
        }

        int maxLength = getMaxNameLength(students);

        for (int i = maxLength - 1; i >= 0; i--) {
            countSortByName(students, i);
        }

        return students;
    }

    private void countSortByName(List<Student> students, int digitIndex) {
        int n = students.size();
        List<Student> output = new ArrayList<>(Collections.nCopies(n, null));
        int[] count = new int[256]; // Assuming ASCII characters

        for (Student student : students) {
            int charIndex = (digitIndex < student.getName().length()) ?
                    (int) student.getName().charAt(digitIndex) : 0;
            count[charIndex]++;
        }

        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int charIndex = (digitIndex < students.get(i).getName().length()) ?
                    (int) students.get(i).getName().charAt(digitIndex) : 0;
            output.set(count[charIndex] - 1, students.get(i));
            count[charIndex]--;
        }

        for (int i = 0; i < n; i++) {
            students.set(i, output.get(i));
        }
    }

    private int getMaxNameLength(List<Student> students) {
        int max = 0;
        for (Student student : students) {
            max = Math.max(max, student.getName().length());
        }
        return max;
    }

    // Bucket Sort by Name
    public List<Student> bucketSortByName(List<Student> students) {
        if (students == null || students.size() <= 1) {
            return students;
        }

        int n = students.size();
        List<Student>[] buckets = new List[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (Student student : students) {
            int bucketIndex = (int) (n * student.getName().hashCode() / (double) (Integer.MAX_VALUE + 1));
            buckets[bucketIndex].add(student);
        }

        for (List<Student> bucket : buckets) {
            Collections.sort(bucket, Comparator.comparing(Student::getName));
        }

        int index = 0;
        for (List<Student> bucket : buckets) {
            for (Student student : bucket) {
                students.set(index++, student);
            }
        }

        return students;
    }
}
