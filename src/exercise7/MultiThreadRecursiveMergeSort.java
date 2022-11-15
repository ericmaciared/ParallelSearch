package exercise7;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.List;

/**
 * 7. Write a multithread program in Java that sorts an array recursively using merge sort technique.
 */

public class MultiThreadRecursiveMergeSort implements Runnable{
    // Fix max amount of depth
    private final int maxDepth = 5;
    private final int depth;
    private List<Integer> numArray;
    public MultiThreadRecursiveMergeSort(List <Integer> numArray, int depth) {
        this.depth = depth;
        this.numArray = numArray;
    }

    @Override
    public void run() {
        // If atomic values are achieved return
        if (numArray.size() == 1) return;

        // If thread depth is at max, perform basic mergesort and return
        if (this.depth == maxDepth) {
            numArray = MergeSort.mergeSort(numArray);
            return;
        }

        // Calculate its mid point
        int midPoint = numArray.size() / 2;

        /*
        System.out.print(Thread.currentThread().getName() + "> ");
        for (Integer i: numArray) {
            System.out.print(i + " ");
        }
        System.out.println();
        */

        // Create two threads to run array halves and increment depth
        MultiThreadRecursiveMergeSort left = new MultiThreadRecursiveMergeSort(numArray.subList(0, midPoint), depth+1);
        MultiThreadRecursiveMergeSort right = new MultiThreadRecursiveMergeSort(numArray.subList(midPoint, numArray.size()), depth+1);
        Thread leftThread = new Thread(left);
        Thread rightThread = new Thread(right);

        // Start threads, wait for join and merge
        leftThread.start();
        rightThread.start();

        try {
            leftThread.join();
            rightThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        numArray = MergeSort.merge(numArray, left.getNumArray(), right.getNumArray());
    }

    public List<Integer> getNumArray() {
        return numArray;
    }
}
