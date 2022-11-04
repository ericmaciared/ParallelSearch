package exercise3;

import java.util.LinkedList;

/**
 * 3. Write a class in Java that creates two threads in order to do a parallel search of an integer number in a
 * LinkedList. Specifically, the first thread has to start the search from the beginning of the LinkedList and the
 * other thread has to start the search from the end of the LinkedList. At the end, the system has to tell which
 * was (if any) the first thread to find the number. The LinkedList size and the number to search must be class
 * attributes.
 */

public class TwoThreadParallelSearch {
    private LinkedList<Integer> numArray;
    private int arraySize;
    private int toSearch;

    public TwoThreadParallelSearch(int arraySize, int toSearch) {
        this.arraySize = arraySize;
        this.toSearch = toSearch;

        // Create number array
        numArray = new LinkedList<Integer>();
        for (int i = 0; i < arraySize; i++) {
            numArray.add(i+1);
        }

        search();
    }

    private void search(){
        // Create two threads with assigned directions, number array, and value to search
        SearchThread thread0 = new SearchThread(numArray, toSearch, true);
        SearchThread thread1 = new SearchThread(numArray, toSearch, false);

        // Start threads
        thread0.start();
        thread1.start();
    }

}
