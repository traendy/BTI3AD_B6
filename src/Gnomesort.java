
/**
 * Implementation of Gnome sort based on pseudocode on Wikipedia.
 *
 * @author Nathan Fiedler
 */
public class Gnomesort {

    private static Counter counter = null;
  
    public Gnomesort(Counter c) {
      this.counter = c;
    }

    /**
     * Sort the input array using the Gnome sort algorithm.
     *  O(n^2) running time.
     *
     * @param  <T>    type of comparable to be sorted.
     * @param  input  array of comparable objects to be sorted.
     */
    public static <T extends Comparable<? super T>> void sort(T[] input) {
        if (input == null || input.length < 2) {
            return;
        }
        int i = 1;
        int j = 2;
        while (i < input.length) {
            if (input[i - 1].compareTo(input[i]) < 1) {
                i = j;
                count();
                j++;
            } else {
                // swap a[i-1] and a[i]
                count();
                T t = input[i - 1];
                input[i - 1] = input[i];
                input[i] = t;
                i--;
                if (i == 0) {
                    i = j;
                    j++;
                }
            }
        }
    }
    
    /**
     * Count.
     */
    private static void count() {
      if (counter != null) {
        counter.counterUp(1);
      }
    }
}