import java.util.ArrayList;
import java.util.Collections;

/**
 * Compares the Sorting Algorithms from
 * source https://github.com/nlfiedler/burstsort4j/blob/master/src/org/burstsort4j
 * @author peter
 * TODO: check all count();
 * TODO: If a Insertionsort is called change it to create instance of insertionsSort and give it the counter
 * TODO: Check if the algos are counting
 * TODO: finally compare again
 */
public class Benchmark {
  private static String[] sorted = null;
  private static String[] shuffled = null;
  private static Counter counter = null;
  
  
  public static void main(String[] args){
    counter = new Counter();
    generateData(6);
    counter = new Counter();
    DualPivotQuicksort dpq = new DualPivotQuicksort(counter);
    dpq.sort(shuffled);
    System.out.println("DualPivotQuicksort: " +counter.getCounter());
    counter.setCounter(0);
    Gnomesort gs = new Gnomesort(counter);
    gs.sort(shuffled);
    System.out.println("Gnomesort: " +counter.getCounter());
    counter.setCounter(0);
    HybridCombsort hcs = new HybridCombsort(counter);
    hcs.sort(shuffled);
    System.out.println("HybridCombsort: " +counter.getCounter());
    counter.setCounter(0);
    Insertionsort insertionsort = new Insertionsort(counter);
    insertionsort.sort(shuffled);
    System.out.println("Insertionsort: " +counter.getCounter());
    counter.setCounter(0);
    Introsort is = new Introsort(counter);
    is.sort(shuffled);
    System.out.println("Introsort: " +counter.getCounter());
    counter.setCounter(0);
    MultikeyQuicksort mkq = new MultikeyQuicksort(counter);
    mkq.sort(shuffled);
    System.out.println("MultikeyQuicksort: " +counter.getCounter());
    counter.setCounter(0);
    Quicksort qs = new Quicksort(counter);
    qs.sort(shuffled);
    System.out.println("Quicksort: " +counter.getCounter());
    counter.setCounter(0);
    Shellsort ss = new Shellsort(counter);
    ss.sort(shuffled);
    System.out.println("Shellsort: " +counter.getCounter());
    System.out.println("alle fertig");
    
  }
  
  
  
  /**
   * generates the sorted set and shuffles it
   * @param N between 1 and 6
   * @return
   */
  private static void generateData(int N){
    N = (int)Math.pow(10, N);
    sorted = new String[N];
    shuffled = new String[N];
    ArrayList<String> list = new ArrayList<>();
    for(int i = 0; i<N; i++){
      sorted[i]=String.valueOf((700+i)*N);
      list.add(sorted[i]);
    }
    Collections.shuffle(list);
    for(int i = 0; i<N; i++){
      shuffled[i]=list.get(i);
    }
    
  }
}
