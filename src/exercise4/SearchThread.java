package exercise4;

import java.util.LinkedList;

public class SearchThread extends Thread{
    private LinkedList<Integer> numArray;
    private int toSearch;

    public SearchThread(LinkedList<Integer> numArray, int toSearch) {
        this.numArray = numArray;
        this.toSearch = toSearch;
    }

    public void run(){
        for (Integer i: numArray) {
            // System.out.println(Thread.currentThread().getName() + "> Searching " + i);
            if (i == toSearch) {
                System.out.println(Thread.currentThread().getName() + "> Found " + toSearch);
                return;
            }
        }
    }

}
