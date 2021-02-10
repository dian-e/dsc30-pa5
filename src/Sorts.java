/*
 * NAME: Diane Li
 * PID:  A15773774
 */
import java.util.ArrayList;

/**
 * Sorts class.
 * @param <T> Generic type
 * @author Diane Li
 * @since  02/09/2021
 */
public class Sorts<T extends Comparable<? super T>> {

    private static final int MIDDLE_IDX = 2;
    private static final int INCREASE_PARAM = 2;

    /**
     * This method performs insertion sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The initial index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void InsertionSort(ArrayList<T> list, int start, int end) {
        int j;
        T temp, curEl, prevEl;
        for (int i = 1; i <= end; i++) {
            j = i;
            // continuously switches an element with its previous if its previous is greater
            while (j > 0 && list.get(j).compareTo(list.get(j - 1)) < 0) {
                temp = list.get(j);
                list.set(j, list.get(j - 1));
                list.set(j - 1, temp);
                j--;
            }
        }
    }

    /**
     * This method performs merge sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The initial index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void MergeSort(ArrayList<T> list, int start, int end) {
        if (start < end)
        {
            int mid = start + (end - start) / MIDDLE_IDX;
            MergeSort(list, start, mid);
            MergeSort(list , mid + 1, end);

            merge(list, start, mid, end);
        }
    }

    /**
     * merge helper function for MergeSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param m the middle index we want to merge
     * @param r right-most index we want to merge
     */
    private void merge(ArrayList<T> arr, int l, int m, int r) {

        int mergedSize = r - l + 1;

        ArrayList<T> mergedNums = new ArrayList<>();
        int left = l, right = m + 1;
        while (left <= m && right <= r) {
            if (arr.get(left).compareTo(arr.get(right)) <= 0) {
                mergedNums.add(arr.get(left));
                left++;
            }
            else {
                mergedNums.add(arr.get(right));
                right++;
            }
        }

        while (left <= m) {
            mergedNums.add(arr.get(left));
            left++;
        }
        while (right <= r) {
            mergedNums.add(arr.get(right));
            right++;
        }
        for (int i = 0; i < mergedSize; i++) {
            arr.set(l + i, mergedNums.get(i));
        }
    }

    /**
     * This method performs quick sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The initial index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void QuickSort(ArrayList<T> list, int start, int end) {
        if (start < end) {
            // sorts parts of lists around a secured partition
            int lowEndIndex = partition(list, start, end);
            QuickSort(list, start, lowEndIndex);
            QuickSort(list, lowEndIndex + 1, end);
        }
    }

    /**
     * partition helper function for QuickSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param h right-most index we want to merge
     */
    private int partition(ArrayList<T> arr, int l, int h) {
        int mid = l + (h - l) / MIDDLE_IDX;
        T pivot = arr.get(mid);
        boolean done = false;
        T temp, lowEl, highEl;

        while (!done) {
            // increment lowI while arr[lowI] < pivot
            while (arr.get(l).compareTo(pivot) < 0) { l++; }

            // decrement highI while pivot < arr[highI]
            while (pivot.compareTo(arr.get(h)) < 0) { h--; }

            // if zero or one elements remain, all numbers are partitioned
            if (l >= h) { done = true; }
            else {
                // swap arr[lowI] and arr[highI]
                temp = arr.get(l);
                arr.set(l, arr.get(h));
                arr.set(h, temp);

                l++;
                h--;
            }
        }
        return h;
    }

    /**
     * This method performs a modified QuickSort that switches to insertion sort after given cutoff
     *
     * @param list The arraylist we want to sort
     * @param start The initial index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     * @param cutoff the minimum length of an subsection of the arraylist
     *               such that we switch to Insertion Sort
     */
    public void Modified_QuickSort(ArrayList<T> list, int start, int end, int cutoff) {
        if (start >= end) { return; }
        // when a subarray is small enough, insertion sort is used instead
        else if ((end - start) + 1 <= cutoff) { this.InsertionSort(list, start, end); }

        // otherwise, traditional QuickSort is used until subarrays are small enough
        int lowEndIndex = partition(list, start, end);
        QuickSort(list, start, lowEndIndex);
        QuickSort(list, lowEndIndex + 1, end);
    }

    /**
     * This method performs a modified QuickSort that switches to insertion sort
     * after a certain cutoff
     *
     * @param list The arraylist we want to sort
     * @param start The initial index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     * @param param The length of the initial splits that are sorted prior to merging
     */
    public void TimSort(ArrayList<T> list, int start, int end, int param){
        int arrLen = end - start + 1;

        // defaults ot InsertionSort param >= length of array
        if (arrLen <= param) { this.InsertionSort(list, start, end); }

        // otherwise, divide sub-arrays of length param and InsertionSort each
        for (int i = start; i <= end; i += param) {
            if (i + (param - 1) > end) { this.InsertionSort(list, i, end); }
            else { this.InsertionSort(list, i, i + (param - 1));}
        }

        // sweep through array, merge sub-arrays and double sub-array size each iteration
        int subarrLen = param * INCREASE_PARAM;
        int idx;
        while (subarrLen <= arrLen) {
            idx = start;
            while (idx + subarrLen - 1 < arrLen) {
                this.merge(list, idx, idx + subarrLen / MIDDLE_IDX - 1, idx + subarrLen - 1);
                idx += subarrLen;
            }
            subarrLen *= INCREASE_PARAM;
        }

        // finally, merge with any remaining fraction of a sub-array leftover at the end
        int leftoverSub = arrLen % (param * INCREASE_PARAM);
        if (leftoverSub != 0) {
            this.merge(list, start, end - leftoverSub, end);
        }
    }

}
