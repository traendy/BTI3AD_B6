import java.util.ArrayList;
import java.util.Collections;

/**
 * Compares the Sorting Algorithms from source
 * https://github.com/nlfiedler/burstsort4j/blob/master/src/org/burstsort4j
 * 
 * @author peter TODO: check all count(); TODO: If a Insertionsort is called
 *         change it to create instance of insertionsSort and give it the
 *         counter TODO: Check if the algos are counting TODO: finally compare
 *         again
 */
public class Benchmark {
	private static String[] sorted = null;
	private static String[] shuffledQuick = null;
	private static String[] shuffledMultiQuick = null;
	private static Counter counter = null;
	private static int numberOfRuns = 10;
	private static long timeEnd = 0;
	private static long timeStart = 0;
  private static long timeEndQuick = 0;
  private static long timeStartQuick = 0;  
  private static long timeEndMultiQuick = 0;
  private static long timeStartMultiQuick = 0;
	public static void main(String[] args) {
		counter = new Counter();
		StringBuilder builder = new StringBuilder();
		builder.append("N").append("\tQCount").append("\tQTime").append("\tMCount").append("\tMTime").append("\n");
		for(int arraySize =1; arraySize<8; arraySize++){
		  builder.append(arraySize);
		
  		for (int i = 0; i < numberOfRuns; i++) {
  		  
  			generateData(arraySize);
  			
  			counter = new Counter();
  			Quicksort quicksort = new Quicksort(counter);
  			timeStartQuick = System.currentTimeMillis();
  			quicksort.sort(shuffledQuick);
  			timeEndQuick = System.currentTimeMillis();
  			
  			builder.append("\t").append(counter.getCounter()).append("\t").append(timeEndQuick-timeStartQuick);
  			
  			counter.setCounter(0);
  			MultikeyQuicksort multisort = new MultikeyQuicksort(counter);
  			timeStartMultiQuick = System.currentTimeMillis();
  			multisort.sort(shuffledMultiQuick);
  			timeEndMultiQuick = System.currentTimeMillis();
  			
  			builder.append("\t").append(counter.getCounter()).append("\t").append(timeEndMultiQuick-timeStartMultiQuick).append("\n");
  	
  		}
		}
		System.out.println(builder.toString());
	}

	/**
	 * generates the sorted set and shuffles it
	 * 
	 * @param N
	 *            between 1 and 6
	 * @return
	 */
	private static void generateData(int N) {
		N = (int) Math.pow(10, N);
		sorted = new String[N];
		shuffledQuick = new String[N];
		shuffledMultiQuick = new String[N];
    
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			sorted[i] = String.valueOf((700 + i) * N);
			list.add(sorted[i]);
		}
		Collections.shuffle(list);
		for (int i = 0; i < N; i++) {
			shuffledQuick[i] = list.get(i);
			shuffledMultiQuick[i] = list.get(i);
		}

	}
	private static void printArray(String [] s){
	  StringBuilder builder = new StringBuilder();
	  for(int i =0; i<s.length; i++){
	    builder.append(sorted[i]).append("\t - \t").append(s[i]).append("\n");
	  }
	  System.out.println(builder.toString());
	  
	}
}
