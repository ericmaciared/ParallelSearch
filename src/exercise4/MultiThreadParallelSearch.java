package exercise4;

import java.util.ArrayList;
import java.util.Arrays;
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
          LinkedList<LinkedList<Integer>> arrayChunks = splitArrayIntoChunks(Array, NumThreads);

          // Create threads, assign chunks
          ArrayList<Searcher> searchers = new ArrayList<>(NumThreads);
          ArrayList<Thread> threads = new ArrayList<>();
          for (int i = 0; i < NumThreads; i++) {
               Searcher searcher = new Searcher(arrayChunks.get(i), toSearch);
               searchers.add(searcher);
               threads.add(new Thread(searcher));
          }

          // Start threads
          for (Thread t: threads) t.start();

          // Try to Join threads
          for (Thread t: threads) {
               try {
                    t.join();
               } catch (InterruptedException e) {
                    throw new RuntimeException(e);
               }
          }

          // Check if result found by any searcher
          for (Searcher searcher: searchers) {
               if (searcher.isFound()) return Arrays.stream(Array).boxed().toList().indexOf(toSearch);
          }

          return -1;
     }

     private static LinkedList<LinkedList<Integer>> splitArrayIntoChunks(int[] numArray, int numChunks) {
          LinkedList<LinkedList<Integer>> chunks = new LinkedList<LinkedList<Integer>>();

          int numPerChunk = numArray.length / numChunks;
          int remainderNums = numArray.length % numChunks;

          int currentIndex = 0;
          for (int i = 0; i < numChunks; i++) {
               LinkedList<Integer> current = new LinkedList<Integer>();

               // Add first chunk
               for (int j = currentIndex; j < currentIndex + numPerChunk; j++) {
                    current.add(numArray[j]);
               }
               currentIndex += numPerChunk;

               // Add remainder
               if (remainderNums != 0){
                    current.add(numArray[currentIndex++]);
                    remainderNums--;
               }

               chunks.add(current);
          }

        /*
        for (LinkedList<Integer> a: chunks) {
            for (int i: a) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        */

          return chunks;
     }
}
