/**
 * s
 * @author peter
 *
 */

/**
 * A Java implementation of multikey quicksort, translated from the
 * original C implementation by J. Bentley and R. Sedgewick, from
 * their "Fast algorithms for sorting and searching strings" paper
 * published in 1997.
 *
 * @author Nathan Fiedler
 */
public class MultikeyQuicksort {

    /** As with GCC std::sort delegate to insertion sort for ranges of
     * size below 16. */
    private static final int GrenzeSelectionSort = 16;
    static Counter counter = null;
    /**
     * Creates a new instance of MultikeyQuicksort.
     */
    public MultikeyQuicksort(Counter c) {
      this.counter = c;
    }

    /**
     * Retrieve the character in string s at offset d. If d is greater
     * than or equal to the length of the string, return zero. This
     * simulates fixed-length strings that are zero-padded.
     * This is necessary for the functionality of MultikeyQuickSort
     *
     * @param  str  string.
     * @param  offset  offset.
     * @return  character in str at offset, or zero.
     */
    private static char charAt(CharSequence str, int offset) {
        return offset < str.length() ? str.charAt(offset) : 0;
    }

    /**
     * Swap the elements between to subarrays.
     *
     * @param  a  the array of elements.
     * @param  i  offset of first subarray.
     * @param  j  offset of second subarray.
     * @param  n  number of elements to swap.
     */
    private static void vecswap(Object[] a, int i, int j, int n) {
        while (n-- > 0) {
          count();
            Object t = a[i];
            a[i] = a[j];
            a[j] = t;
            i++;
            j++;
        }
    }

    /**
     * Sorts the array of strings using a multikey quicksort that chooses
     * a pivot point using a "median of three" rule (or pseudo median of
     * nine for arrays over a certain threshold). For very small subarrays,
     * an insertion sort is used.
     *
     * @param  strings  array of strings to be sorted.
     */
    public static void sort(CharSequence[] strings) {
        if (strings != null && strings.length > 1) {
            ssort(strings, 0, strings.length, 1);
        }
    }
 
    /**
     * Find the median of three characters, found in the given strings
     * at character position <em>depth</em>. One of the three integer
     * values will be returned based on the comparisons.
     *
     * @param  str      array of strings.
     * @param  lowIndex      low index.
     * @param  middleIndex      middle index.
     * @param  highIndex      high index.
     * @param  depth  character offset.
     * @return  the position of the median string.
     */
    private static int med3(CharSequence[] str, int lowIndex, int middleIndex, int highIndex, int depth) {
        char lowChar = charAt(str[lowIndex], depth);
        char middleChar = charAt(str[middleIndex], depth);
        if (lowChar == middleChar) {
            return lowIndex;
        }
        char highChar = charAt(str[highIndex], depth);
        if (highChar == lowChar || highChar == middleChar) {
            return highIndex;
        }
        return lowChar < middleChar ? (middleChar < highChar ? middleIndex : (lowChar < highChar ? highIndex : lowIndex))
                : (middleChar > highChar ? middleIndex : (lowChar < highChar ? lowIndex : highIndex));
    }

    /**
     * The recursive portion of multikey quicksort.
     *
     * @param  array  the array of strings to sort.
     * @param  base     zero-based offset into array to be considered.
     * @param  length   length of subarray to consider.
     * @param  depth    the zero-based offset into the strings.
     */
    private static void ssort(CharSequence[] array, int base, int length, int depth) {
        /*
         * If the array is small do InsertionSort
         */
        if (length < GrenzeSelectionSort) {
            Insertionsort.sort(array, base, base + length, depth, counter);
            return;
        }
        int pivotLow = base;
        int pivotMiddle = base + length / 2;
        int pivotHigh = base + length - 1;
        int neighborCharDif;
        // On larger arrays, find a pseudo median of nine elements.
        if (length > 30) {
            int offset = length / 8;
            pivotLow = med3(array, base, base + offset, base + 2 * offset, depth);
            pivotMiddle = med3(array, base + length / 2 - offset, pivotMiddle, base + length / 2 + offset, depth);
            pivotHigh = med3(array, base + length - 1 - 2 * offset, base + length - 1 - offset, pivotHigh, depth);
        }
        pivotMiddle = med3(array, pivotLow, pivotMiddle, pivotHigh, depth);
        CharSequence temp = array[base];
        array[base] = array[pivotMiddle];
        array[pivotMiddle] = temp;
        
        int charAtBase = charAt(array[base], depth);
        boolean allzeros = charAtBase == 0; //true if value == zero means array elements not equal length /dont care
        int lowerElement = base + 1, lowerTemp = lowerElement;
        int highertemp = base + length - 1, higherElement = highertemp;
        /**
         * Presorting
         * Compares the number by didgets und swap the numbers
         */
        while (true) {
            for (; lowerTemp <= highertemp && (neighborCharDif = charAt(array[lowerTemp], depth) - charAtBase) <= 0; lowerTemp++) {
                if (neighborCharDif == 0) {
                    temp = array[lowerElement];
                    array[lowerElement] = array[lowerTemp];
                    array[lowerTemp] = temp;
                    lowerElement++;
                } else {
                    allzeros = false;
                }
            }
            for (; lowerTemp <= highertemp && (neighborCharDif = charAt(array[highertemp], depth) - charAtBase) >= 0; highertemp--) {
                if (neighborCharDif == 0) {
                    temp = array[highertemp];
                    array[highertemp] = array[higherElement];
                    array[higherElement] = temp;
                    
                    higherElement--;
                } else {
                    allzeros = false;
                }
            }
            /**
             * If lower temp > highertemp break
             */
            if (lowerTemp > highertemp) {
                break;
            }
            
            /**
             * else swap and move to the next element
             */
            temp = array[lowerTemp];
            array[lowerTemp] = array[highertemp];
            array[highertemp] = temp;
            lowerTemp++;
            highertemp--;
        }
        
        pivotHigh = base + length;
        neighborCharDif = Math.min(lowerElement - base, lowerTemp - lowerElement);
        vecswap(array, base, lowerTemp - neighborCharDif, neighborCharDif);
        neighborCharDif = Math.min(higherElement - highertemp, pivotHigh - higherElement - 1);
        vecswap(array, lowerTemp, pivotHigh - neighborCharDif, neighborCharDif);
        //if lowerttemp>lowerelement
        if ((neighborCharDif = lowerTemp - lowerElement) > 1) {
            ssort(array, base, neighborCharDif, depth);
           
        }
        
        //if allzeros ==false
        if (!allzeros) {
            ssort(array, base + neighborCharDif, lowerElement + length - higherElement - 1, depth + 1);
            
        }
        //if higherElement > highertemp
        if ((neighborCharDif = higherElement - highertemp) > 1) {
            ssort(array, base + length - neighborCharDif, neighborCharDif, depth);
            
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