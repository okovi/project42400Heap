import java.io.File;
import java.io.IOException; 
public class heapTest{ 
    public static void main(String[] args) { 
        try { 
            MaxHeap sortedHeap= MaxHeap.succCreateHeap(new File("data_random.txt")) ; 
            sortedHeap.writeAddHeapFile(new File ("data_sorted.txt"));
            
        } catch (IOException e) {
            System.out.print("ioexception");
        }
        
        
        
        
    }
}