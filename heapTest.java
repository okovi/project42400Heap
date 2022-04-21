package project42400heap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.util.Scanner;

public class heapTest{ 
    static public int[] parseFile() throws IOException {
        try { 
            Scanner fReader = new Scanner(new File("data_random.txt"));
            int[] parsed=new int[100];
            int i=0;
            while(fReader.hasNextInt()) {
                parsed[i]=fReader.nextInt();
                i++;
            }  
            fReader.close();
            return parsed;
        }catch (FileNotFoundException e) { 
            System.out.print(" error happened while parsing");
            //gives me errors without returning some kind of array
            int[] error= {-1,-1};
            return error;

        }
    }
    public static void main(String[] args) { 
        try { 
            int[] parsed = parseFile();
            MaxHeap sortedHeap = new MaxHeap(parsed, true); 
            sortedHeap.writeAddHeapFile(new File ("data_sorted.txt"));
            
        } catch (IOException e) {
            System.out.print("ioexception");
        }
    }
}