package exercise4;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 4. Write a class in Java that allows to do a parallel search of an integer number in an array. Specifically, the class
 * has to implement this method:
 * public static int ParallelSearch(int toSearch, int[] Array, int NumThreads)
 * This method creates as many threads as NumThreads, splits the array Array in as many as slices as specified in
 * NumThreads, and gives a copy of the slice to each thread. Then, each thread searches in its slice the number
 * toSearch. If a thread has found the value, the method will return the position of the number in the initial
 * array, otherwise it will return -1.
 */

public class MultiThreadParallelSearch {
     public static int ParallelSearch(int toSearch, int[] Array, int NumThreads){

          // Split array into equal chunks
          LinkedList<LinkedList<Integer>> arrayChunks = ChunkSplitter.splitArrayIntoChunks(Array, NumThreads);

          // Create threads, assign chunks
          ArrayList<SearchThread> threadPool = new ArrayList<SearchThread>(NumThreads);
          for (int i = 0; i < NumThreads; i++) {
               SearchThread searchThread = new SearchThread(arrayChunks.get(i), toSearch);
               threadPool.add(searchThread);
          }

          // Start threads
          for (SearchThread st: threadPool) st.start();

          return -1;
     }
}
