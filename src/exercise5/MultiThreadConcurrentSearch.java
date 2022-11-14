package exercise5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 5. Modify the previous method so all the threads access concurrently to the array (no slice copies are done).
 *    Compare the search times with the ones obtained when using the previous method. Discuss the obtained results.
 *    You are encouraged to play with different array sizes and different number of threads.
 */


public class MultiThreadConcurrentSearch {
     public static int ConcurrentSearch(int toSearch, int[] Array, int NumThreads){
          // Segment array, find start and ending indices for chunks to search of array
          LinkedList<LinkedList<Integer>> indices = splitArrayIntoIndexChunks(Array, NumThreads);

          // Create threads, assign array
          ArrayList<Searcher> searchers = new ArrayList<>(NumThreads);
          ArrayList<Thread> threads = new ArrayList<>();
          for (int i = 0; i < NumThreads; i++) {
               Searcher searcher = new Searcher(Array, toSearch, indices.get(i).get(0), indices.get(i).get(1));
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

     private static LinkedList<LinkedList<Integer>> splitArrayIntoIndexChunks(int[] numArray, int numChunks) {
          LinkedList<LinkedList<Integer>> chunks = new LinkedList<LinkedList<Integer>>();

          int numPerChunk = numArray.length / numChunks;
          int remainderNums = numArray.length % numChunks;

          int currentIndex = 0;
          for (int i = 0; i < numChunks; i++) {
               LinkedList<Integer> current = new LinkedList<Integer>();
               // Add start index
               current.add(currentIndex);

               // Count chunk
               currentIndex += numPerChunk;

               // Count remainder
               if (remainderNums != 0){
                    currentIndex++;
                    remainderNums--;
               }
               current.add(currentIndex - 1);

               chunks.add(current);
          }

          /*
          for (LinkedList<Integer> a: chunks) {
            for (int i: a) {
                System.out.print(i + " ");
            }
            System.out.println();
          } */

          return chunks;
     }
}
