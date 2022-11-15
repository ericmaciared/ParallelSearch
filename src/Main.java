import exercise3.TwoThreadParallelSearch;
import exercise4.MultiThreadParallelSearch;
import exercise5.MultiThreadConcurrentSearch;
import exercise7.MergeSort;
import exercise7.MultiThreadRecursiveMergeSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    static int arraySize = 1;
    static int toSearch = 6;
    static int maxThreads = 16;
    static int maxTestingRounds = 5;
    static long startTime, endTime, durationMillis;
    public static void main(String[] args) {
        // Exercise 3
        new TwoThreadParallelSearch(arraySize, toSearch);

        // Exercises 4 and 5
        exercise4and5();

        // Exercise 7
        exercise7();
    }

    private static void exercise4and5() {
        ArrayList<Long> parallelResults = new ArrayList<>();
        ArrayList<Long> concurrentResults = new ArrayList<>();
        arraySize = 1;

        for (int testRound = 1; testRound < maxTestingRounds; testRound++) {
            for (int numThreads = 2; numThreads < maxThreads; numThreads++) {
                int[] numArray = createRandomizedArray(arraySize);

                // Exercise 4
                startTime = System.nanoTime();
                int exercise4 = MultiThreadParallelSearch.ParallelSearch(toSearch, numArray, numThreads);
                // System.out.println("Exercise 4> Found value " + toSearch + " at index " + exercise4);
                endTime = System.nanoTime();
                durationMillis = (endTime - startTime) / 1000000;
                parallelResults.add(durationMillis);

                // Exercise 5
                startTime = System.nanoTime();
                int exercise5 = MultiThreadConcurrentSearch.ConcurrentSearch(toSearch, numArray, numThreads);
                //System.out.println("Exercise 5> Found value " + toSearch + " at index " + exercise5);
                endTime = System.nanoTime();
                durationMillis = (endTime - startTime) / 1000000;
                concurrentResults.add(durationMillis);
            }
        }

        // Show results
        arraySize = 1;
        for (int testRound = 1; testRound < maxTestingRounds; testRound++) {
            arraySize+=10;
            for (int numThreads = 2; numThreads < maxThreads; numThreads++) {
                System.out.println("Array Size: " + arraySize + " Number of Threads: ");
                System.out.print("\nParallel Results>  ");
                for (Long l: parallelResults) System.out.print(l + " - ");
                System.out.print("\nConcurrent Results> ");
                for (Long l: concurrentResults) System.out.print(l + " - ");
            }
        }




    }

    private static void exercise7() {
        ArrayList<Long> multiThreadResults = new ArrayList<Long>();
        ArrayList<Long> regularResults = new ArrayList<Long>();

        arraySize = 1;
        for (int testRound = 1; testRound < maxTestingRounds; testRound++) {
            arraySize *= 10;
            int[] numArray = createRandomizedArray(arraySize);

            // Test for multithread mergeSort
            startTime = System.nanoTime();
            MultiThreadRecursiveMergeSort sorter = new MultiThreadRecursiveMergeSort(Arrays.stream(numArray).boxed().toList(), 0);
            sorter.run();
            endTime = System.nanoTime();
            durationMillis = (endTime - startTime) / 1000000;
            List<Integer> sorted = sorter.getNumArray();
            multiThreadResults.add(durationMillis);

            // Test for regular mergeSort
            startTime = System.nanoTime();
            sorted = MergeSort.mergeSort(Arrays.stream(numArray).boxed().toList());
            endTime = System.nanoTime();
            durationMillis = (endTime - startTime) / 1000000;
            regularResults.add(durationMillis);
        }

        // Show results
        System.out.print("Array Size>           ");
        arraySize = 1;
        for (int i = 1; i < maxTestingRounds; i++) {
            arraySize *= 10;
            System.out.print(arraySize + " - ");
        }
        System.out.print("\nMultithread Results>  ");
        for (Long l: multiThreadResults) System.out.print(l + " - ");
        System.out.print("\nRegular Sort Results> ");
        for (Long l: regularResults) System.out.print(l + " - ");
    }

    private static int[] createRandomizedArray(int arraySize) {
        Random random = new Random();
        int[] numArray = new int[arraySize];
        // Create Array
        for (int i = 0; i < arraySize; i++) {
            numArray[i] = i+1;
        }

        // Randomize array
        for (int i = 0; i < arraySize; i++) {
            int randomIndexSwap = random.nextInt(arraySize);
            int temp = numArray[randomIndexSwap];
            numArray[randomIndexSwap] = numArray[i];
            numArray[i] = temp;
        }

        return numArray;
    }
}