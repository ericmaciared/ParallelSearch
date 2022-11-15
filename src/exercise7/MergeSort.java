package exercise7;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static List<Integer> mergeSort(List<Integer> numArray) {
        // Check number of items to not be atomic
        if (numArray.size() == 1) return numArray;

        // Calculate its mid-point
        int midPoint = numArray.size() / 2;

        // Divide array and sort
        List<Integer> left = MergeSort.mergeSort(numArray.subList(0, midPoint-1));
        List<Integer> right = MergeSort.mergeSort(numArray.subList(midPoint, numArray.size()-1));

        // Merge array
        numArray = merge(numArray, left, right);

        return numArray;
    }

    public static List<Integer> merge(List<Integer> numArray, List<Integer> left, List<Integer> right) {
        System.out.println("Merging");
        numArray = new ArrayList<Integer>();
        int li = 0, ri = 0;

        // Add items in order to initial list
        while (li < left.size() && ri < right.size()) {
            if (left.get(li) > right.get(ri)) {
                numArray.add(right.get(ri++));
            }
            else numArray.add(left.get(li++));
        }

        // Add reminders
        while (li < left.size()) numArray.add(left.get(li++));
        while (ri < right.size()) numArray.add(right.get(ri++));

        for (Integer i : numArray) {
            System.out.print(i + " ");
        }
        System.out.println();

        return numArray;
    }
}
