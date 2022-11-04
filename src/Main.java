import exercise3.TwoThreadParallelSearch;
import exercise4.MultiThreadParallelSearch;

public class Main {
    public static void main(String[] args) {
        int arraySize = 100;
        int toSearch = 62;

        // Exercise 3
        // new TwoThreadParallelSearch(arraySize, toSearch);

        // Exercise 4
        // Create array
        int[] numArray = new int[arraySize];
        int numThreads = 4;

        for (int i = 0; i < arraySize; i++) {
            numArray[i] = i+1;
        }

        int exercise4 = MultiThreadParallelSearch.ParallelSearch(toSearch, numArray, numThreads);

    }
}