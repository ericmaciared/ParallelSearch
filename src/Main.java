import exercise4.MultiThreadParallelSearch;
import exercise5.MultiThreadConcurrentSearch;
import exercise7.MultiThreadRecursiveMergeSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int arraySize = 10;
        int toSearch = 6;
        int numThreads = 5;
        int[] numArray = new int[arraySize];

        // Create Array
        for (int i = 0; i < arraySize; i++) {
            numArray[i] = i+1;
        }

        // Randomize array
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            int randomIndexSwap = random.nextInt(arraySize);
            int temp = numArray[randomIndexSwap];
            numArray[randomIndexSwap] = numArray[i];
            numArray[i] = temp;
        }


        // Exercise 3
        //new TwoThreadParallelSearch(arraySize, toSearch);

        // Exercise 4
        /*
        long startTime = System.nanoTime();
        int exercise4 = MultiThreadParallelSearch.ParallelSearch(toSearch, numArray, numThreads);
        System.out.println("Exercise 4> Found value " + toSearch + " at index " + exercise4);
        long endTime = System.nanoTime();
        long durationMillis = (endTime - startTime) / 1000000;
        System.out.println("Exercise 4> Time elapsed: " + durationMillis+ "ms");


        // Exercise 5
        startTime = System.nanoTime();
        int exercise5 = MultiThreadConcurrentSearch.ConcurrentSearch(toSearch, numArray, numThreads);
        System.out.println("Exercise 5> Found value " + toSearch + " at index " + exercise5);
        endTime = System.nanoTime();
        durationMillis = (endTime - startTime) / 1000000;
        System.out.println("Exercise 5> Time elapsed: " + durationMillis+ "ms");
        */

        // Exercise 7
        MultiThreadRecursiveMergeSort sorter = new MultiThreadRecursiveMergeSort(Arrays.stream(numArray).boxed().toList(), 0);
        System.out.print("Exercise 7> Multithread Array to Sort: ");
        for (int i: numArray) {
            System.out.print(i + " ");
        }
        System.out.println();
        sorter.run();
        List<Integer> sorted = sorter.getNumArray();
        System.out.print("Exercise 7> Multithread Sorted Array: ");
        for (Integer i: sorted) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}