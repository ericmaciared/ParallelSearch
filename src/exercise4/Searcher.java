package exercise4;

import java.util.LinkedList;

public class Searcher implements Runnable{
    private LinkedList<Integer> numArray;
    private int toSearch;
    private volatile boolean found;

    public Searcher(LinkedList<Integer> numArray, int toSearch) {
        this.numArray = numArray;
        this.toSearch = toSearch;
        this.found = false;
    }

    public void run(){
        for (Integer i: numArray) {
            //System.out.println(Thread.currentThread().getName() + "> Searching " + i);
            if (i == toSearch) {
                //System.out.println(Thread.currentThread().getName() + "> Found " + toSearch);
                this.found = true;
            }
        }
    }

    public boolean isFound() {
        return found;
    }
}
