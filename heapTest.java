package project42400heap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException; 
import java.util.Scanner;

public class heapTest{ 
    static public int[] parseFile(String fileName) throws IOException {
        try { 
            Scanner fReader = new Scanner(new File(fileName));
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
            FileWriter writer=new FileWriter("data_heaped.txt");
            writer.close();
            String fileName = "data_random.txt";
            int[] parsed = parseFile(fileName);
            MaxHeap sequentialHeap = new MaxHeap(parsed, true); 
            sequentialHeap.writeAddHeapFile(new File ("data_heaped.txt"), true);
            MaxHeap optimalHeap = new MaxHeap(parsed, false); 
            optimalHeap.writeAddHeapFile(new File ("data_heaped.txt"), false);
            fileName = "data_sorted.txt";
            parsed = parseFile(fileName);
            sequentialHeap = new MaxHeap(parsed, true); 
            sequentialHeap.writeAddHeapFile(new File ("data_heaped.txt"), true);
            optimalHeap = new MaxHeap(parsed, false); 
            optimalHeap.writeAddHeapFile(new File ("data_heaped.txt"), false);


        } catch (IOException e) {
            System.out.print("ioexception");
        }
    }
}