package exercise3;

import java.util.LinkedList;

public class SearchThread extends Thread{
    private LinkedList<Integer> numArray;
    private int toSearch;
    private Boolean leftToRight;

    public SearchThread(LinkedList<Integer> numArray, int toSearch, Boolean leftToRight) {
        this.numArray = numArray;
        this.toSearch = toSearch;
        this.leftToRight = leftToRight;
    }

    public void run(){
        if (leftToRight) {
            for (Integer i: numArray) {
                // System.out.println(Thread.currentThread().getName() + "> Looking at " + i);
                if (i == toSearch) {
                    System.out.println("Exercise 3> " + Thread.currentThread().getName() + "> Found " + toSearch);
                }
            }
        }
        else {
            for (int i = numArray.size()-1; i >= 0; i--) {
                // System.out.println(Thread.currentThread().getName() + "> Looking at " + numArray.get(i));
                if (numArray.get(i) == toSearch) {
                    System.out.println(Thread.currentThread().getName() + "> Found " + toSearch);
                }
            }
        }
    }
}
