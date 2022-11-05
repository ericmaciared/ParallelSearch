package exercise4;

import java.util.LinkedList;

public class ChunkSplitter {
    public static LinkedList<LinkedList<Integer>> splitArrayIntoChunks (int[] numArray, int numChunks) {
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
