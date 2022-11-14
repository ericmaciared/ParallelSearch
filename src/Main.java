import exercise3.TwoThreadParallelSearch;
import exercise4.MultiThreadParallelSearch;
import exercise5.MultiThreadConcurrentSearch;

public class Main {
    public static void main(String[] args) {
        int arraySize = 1000;
        int toSearch = 62;
        int numThreads = 100;

        // Exercise 3
        //new TwoThreadParallelSearch(arraySize, toSearch);

        // Exercise 4
        // Create array
        int[] numArray = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            numArray[i] = i+1;
        }

        // Exercise 4
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

    }
}