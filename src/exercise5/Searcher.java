package exercise5;

import java.util.LinkedList;

public class Searcher implements Runnable{
    private final int[] numArray;
    private final int toSearch;
    private final Integer startIndex;
    private final Integer endIndex;
    private boolean found;

    public Searcher(int[] numArray, int toSearch, Integer startIndex, Integer endIndex) {
        this.numArray = numArray;
        this.toSearch = toSearch;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.found = false;
    }

    public void run(){
        for (int i = this.startIndex; i <= this.endIndex; i++) {
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
