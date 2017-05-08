

/**
 * Simple implementation of insertion sort algorithm. Used by the
 * multikey quicksort implementation for small subarrays.
 *
 * @author Nathan Fiedler
 */
public class Insertionsort {

  static Counter counter = null;
  public Insertionsort(Counter c) {
      this.counter = c;
    }

    /**
     * Sort the array of comparables. Uses a simple insertion sort
     * algorithm, so expect O(n^2) running time.
     *
     * @param  <T>   type of comparable to be sorted.
     * @param  arr   comparables to be sorted.
     */
    public static <T extends Comparable<? super T>> void sort(T[] arr, Counter c) {
        if (arr != null) {
            sort(arr, 0, arr.length - 1, c);
        }
    }

    /**
     * Sort the array of comparables within the given range of elements.
     * Uses a simple insertion sort algorithm, so expect O(n^2) running
     * time.
     *
     * @param  <T>   type of comparable to be sorted.
     * @param  arr   comparables to be sorted.
     * @param  low   low end of range to sort (inclusive).
     * @param  high  high end of range to sort (inclusive).
     */
    public static <T extends Comparable<? super T>> void sort(T[] arr, int low, int high, Counter c) {
      
        if (arr == null || arr.length < 2 || low < 0 || high <= low) {
            return;
        }

        for (int i = low + 1; i <= high; i++) {
            T pivot = arr[i];
            int j = i;
            
            while (j > low && pivot.compareTo(arr[j - 1]) < 0) {
              c.counterUp(1);
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = pivot;
        }
    }

    /**
     * Sort the strings in the array using an insertion sort, but only
     * consider the characters in the strings starting from the given
     * offset <em>depth</em>. That is, the method will ignore all characters
     * appearing before the <em>depth</em> character.
     *
     * @param  strings  array of strings to sort.
     * @param  low      low offset into the array (inclusive).
     * @param  high     high offset into the array (exclusive).
     * @param  depth    offset of first character in each string to compare.
     */
    public static void sort(CharSequence[] strings, int low, int high, int depth, Counter c) {
      
        if (strings == null || low < 0 || high <= low || depth < 0) {
            return;
        }
        for (int i = low + 1; i < high; i++) {
            for (int j = i; j > low; j--) {
              c.counterUp(1);
                int idx = depth;
                char s = idx < strings[j - 1].length() ? strings[j - 1].charAt(idx) : 0;
                char t = idx < strings[j].length() ? strings[j].charAt(idx) : 0;
                while (s == t && idx < strings[j - 1].length()) {
                  
                    idx++;
                    s = idx < strings[j - 1].length() ? strings[j - 1].charAt(idx) : 0;
                    t = idx < strings[j].length() ? strings[j].charAt(idx) : 0;
                }
                if (s <= t) {
                    break;
                }
                CharSequence tmp = strings[j];
                
                strings[j] = strings[j - 1];
                strings[j - 1] = tmp;
            }
        }
    }
    
   
}