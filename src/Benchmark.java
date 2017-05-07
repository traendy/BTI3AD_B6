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
	private static String[] shuffled = null;
	private static Counter counter = null;
	private static int numberOfRuns = 10;
	private static long timeEnd = 0;
	private static long timeStart = 0;

	public static void main(String[] args) {
		counter = new Counter();
		
		for (int i = 0; i < numberOfRuns; i++) {
			generateData(2);
			counter = new Counter();
//			DualPivotQuicksort dpq = new DualPivotQuicksort(counter);
//			//timeStart = System.currentTimeMillis();
//			timeStart = System.nanoTime();
//			dpq.sort(shuffled);
//			//timeEnd = System.currentTimeMillis();
//			timeEnd = System.nanoTime();
//			System.out.println("DualPivotQuicksort: \t" + counter.getCounter() + "\nBenötigte Zeit: \t"
//					+ (timeEnd - timeStart) + "\n");
//
//			counter.setCounter(0);
//			Gnomesort gs = new Gnomesort(counter);
//			timeStart = System.currentTimeMillis();
//			//timeStart = System.nanoTime();
//			gs.sort(shuffled);
//			timeEnd = System.currentTimeMillis();
//			//timeEnd = System.nanoTime();
//			System.out.println(
//					"Gnomesort: \t" + counter.getCounter() + "\nBenötigte Zeit: \t" + (timeEnd - timeStart) + "\n");
//
//			counter.setCounter(0);
//			HybridCombsort hcs = new HybridCombsort(counter);
//			timeStart = System.currentTimeMillis();
//			//timeStart = System.nanoTime();
//			hcs.sort(shuffled);
//			timeEnd = System.currentTimeMillis();
//			//timeEnd = System.nanoTime();
//			System.out.println(
//					"HybridCombsort: \t" + counter.getCounter() + "\nBenötigte Zeit: \t" + (timeEnd - timeStart) + "\n");
//
//			counter.setCounter(0);
//			Insertionsort insertionsort = new Insertionsort(counter);
//			timeStart = System.currentTimeMillis();
//			//timeStart = System.nanoTime();
//			insertionsort.sort(shuffled);
//			timeEnd = System.currentTimeMillis();
//			//timeEnd = System.nanoTime();
//			System.out.println(
//					"Insertionsort: \t" + counter.getCounter() + "\nBenötigte Zeit: \t" + (timeEnd - timeStart) + "\n");
//
//			counter.setCounter(0);
//			Introsort is = new Introsort(counter);
//			timeStart = System.currentTimeMillis();
//			//timeStart = System.nanoTime();
//			is.sort(shuffled);
//			timeEnd = System.currentTimeMillis();
//			//timeEnd = System.nanoTime();
//			System.out.println(
//					"Introsort: \t" + counter.getCounter() + "\nBenötigte Zeit: \t" + (timeEnd - timeStart) + "\n");
//
//			counter.setCounter(0);
//			MultikeyQuicksort mkq = new MultikeyQuicksort(counter);
//			timeStart = System.currentTimeMillis();
//			//timeStart = System.nanoTime();
//			mkq.sort(shuffled);
//			timeEnd = System.currentTimeMillis();
//			//timeEnd = System.nanoTime();
//			System.out.println(
//					"MultikeyQuicksort: \t" + counter.getCounter() + "\nBenötigte Zeit: \t" + (timeEnd - timeStart) + "\n");
//
//			counter.setCounter(0);
//			Quicksort qs = new Quicksort(counter);
//			timeStart = System.currentTimeMillis();
////			timeStart = System.nanoTime();
//			qs.sort(shuffled);
//			timeEnd = System.currentTimeMillis();
////			timeEnd = System.nanoTime();
//			System.out.println(
//					"Quicksort: \t" + counter.getCounter() + "\nBenötigte Zeit: \t" + (timeEnd - timeStart) + "\n");
//
			counter.setCounter(0);
			Shellsort ss = new Shellsort(counter);
			timeStart = System.currentTimeMillis();
//			timeStart = System.nanoTime();
			ss.sort(shuffled);
			timeEnd = System.currentTimeMillis();
//			timeEnd = System.nanoTime();
			System.out.println(
					i+"Shellsort: \t" + counter.getCounter() + "\nBenötigte Zeit: \t" + (timeEnd - timeStart) + "\n");
//			System.out.println("alle fertig");
		}
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
		shuffled = new String[N];
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			sorted[i] = String.valueOf((700 + i) * N);
			list.add(sorted[i]);
		}
		Collections.shuffle(list);
		for (int i = 0; i < N; i++) {
			shuffled[i] = list.get(i);
		}

	}
}
