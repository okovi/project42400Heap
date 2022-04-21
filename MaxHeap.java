package project42400heap;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class MaxHeap implements MaxHeapInterface { 

    private int[] heap;
    private int lastIndex; 
    private boolean initialized=false; 
    private static final int DEFAULT_CAPACITY=25; 
    private static final int MAX_CAPACITY=10000; 
    private int numOfSwaps;
    
    
   // public  MaxHeap seqCreateHeap(T[] entries) throws IOException{ 
   //     return new MaxHeap(entries,true);
   // }
    
    /**
     * @param entries
     * @param sequential
     * @throws java.io.IOException
     */
    /*--------------Methods to be implemented for Project 4------------*/
    protected MaxHeap(int[] entries, boolean sequential) throws IOException{ //using add method repeatedly
        this(entries.length); // Call other constructor
        if (!sequential) {
            lastIndex = entries.length;
            // Assertion: integrityOK = true

            // Copy given array to data field
            for (int index = 0; index < entries.length; index++)
               heap[index + 1] = entries[index];

            // Create heap
            for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
               reheap(rootIndex);
        }
        else {
            heap = new int[101];           
            numOfSwaps=0; 
            heap[1]=entries[0];
            lastIndex++;
            initialized=true;
            for (int i=1; i<entries.length; i++) {
                add(entries[i]);
            }
        }
        
    }
    /**
     * 
     * @param elemIndex
     * @return
     */
    protected int remove(int elemIndex){ 
        checkInitalization();
        int elem=-1;
        if (!isEmpty())
        {
            elem=heap[elemIndex];                                                                                   //get copy of max
            heap[elemIndex]=heap[lastIndex];                                                                        //push last leaf to the top 
            lastIndex--;
            reheap(elemIndex);                                                                                      //reheap root
        }
        return elem;
    }
    
    /**
     * 
     * @param toWriteIn
     * @param isSequential
     * @throws java.io.IOException
     * 
     */
    public void writeAddHeapFile(File toWriteIn, boolean isSequential) throws IOException { 
        	
            FileWriter writer=new FileWriter(toWriteIn, true);
            if(isSequential) {
            	writer.write("=======================================================================================\n"); 
                writer.write("Heap built using sequential insertions: ");
    		    for (int i=1;i<=10;i++) 
    		    	writer.write(""+heap[i]+",");		
    	    	writer.write("...\n");
                writer.write("Number of swaps in the heap creation: " + numOfSwaps);
                writer.write("\n"); 
                for (int i=0; i<10;i++)
                    this.removeMax();
                writer.write("Heap after 10 removals: ");
                for (int i=1;i<=10;i++) 
    		    	writer.write(""+heap[i]+",");	
    	    	writer.write("...\n");
            }
            else {
            	  writer.write("\n");
                  writer.write("Heap built using optimal insertions: ");
      		    for (int i=1;i<=10;i++) 
      		    	writer.write(""+heap[i]+",");		
      	    	writer.write("...\n");
                  writer.write("Number of swaps in the heap creation: " + numOfSwaps);
                  writer.write("\n"); 
                  for (int i=0; i<10;i++)
                      this.removeMax();
                  writer.write("Heap after 10 removals: ");
                  for (int i=1;i<=10;i++) 
      		    	writer.write(""+heap[i]+",");		
      	    	writer.write("...\n");
                  writer.write("=======================================================================================\n");
            }
            writer.close();
         
    }
    
    /*--------------Functional methods to do project--------------------*/
    /**
     * 
     * @param potentialCapacity
     */
    public void checkCapcity(int potentialCapacity) { //to check edge cases 
        if (potentialCapacity>MAX_CAPACITY)
            throw new IllegalArgumentException("desired capacity is too large"); 
    }
    /**
     * 
     */
    public MaxHeap()
     { 
        this (DEFAULT_CAPACITY);
     }
     /**
      * 
      * @param initialCapacity
      */
     public MaxHeap(int initialCapacity) { 
        if (initialCapacity<DEFAULT_CAPACITY)
            initialCapacity=DEFAULT_CAPACITY;
        else
            checkCapcity(initialCapacity);
        int[] tempHeap = new int[initialCapacity + 1];
        heap=tempHeap;
        lastIndex=0;
        initialized=true;
     }
    /**
     * 
     * @param newEntry
     */
    @Override
    public void add ( int newEntry)  {
        checkInitalization();
        int newIndex=lastIndex+1;
        int parentIndex=newIndex/2; //int div takes care of floor fxn 
        while ((parentIndex > 0) && newEntry > heap[parentIndex])
        {
            heap[newIndex]=heap[parentIndex]; //move parent down
            newIndex=parentIndex; // get next possible posotion
            parentIndex=newIndex/2; //continue loop
            numOfSwaps++; //keeps track of how many swaps          ///////////////////////////////////////////////////////////////////////////
        }
        heap[newIndex]=newEntry;
        lastIndex++; 
    }
    /**
     * 
     */
    public void checkInitalization() {
        if(initialized==false)
            throw new IllegalStateException("Not initalized"); 
    }
    /**
     * 
     * @return
     */
    @Override
    public int getMax()
   {
	checkInitalization();
        int root = -1;
        if (!isEmpty())
            root = heap[1];
      return root;
   } // end getMax
    /**
     * 
     * @return
     */
    @Override
    public int removeMax (){
        checkInitalization();
        int root = -1; // only positive numbers, so if it returns negative then it isnt working correctly
        if (!isEmpty())
        {
            root=heap[1];                                                                                   //get copy of max
            heap[1]=heap[lastIndex];                                                                        //push last leaf to the top 
            lastIndex--;
            reheap(1);                                                                                      //reheap root
        }
        return root;
    }
    /**
     * 
     * @param rootIndex
     */
    private void reheap(int rootIndex) { 
        boolean done=false; 
        int orphan=heap[rootIndex];                                                                           //copy of the root, should make a copy/clone method because this will probably overwrite it, I think. 
        int leftChildIndex=2*rootIndex; 
        while (!done && (leftChildIndex<=lastIndex) ) 
        {
            int largerChildIndex=leftChildIndex;                                                            //assume left-child is larger
            int rightChildIndex=leftChildIndex+1;
            if ((rightChildIndex<=lastIndex) && (heap[rightChildIndex] > heap[largerChildIndex]))   // check for + get larger child
                largerChildIndex=rightChildIndex;
            if (orphan < heap[largerChildIndex])                                                 // compare to child-max, swap if current is smaller + continue loop
            {
                heap[rootIndex]=heap[largerChildIndex];                                                     // set up for top of while
                rootIndex=largerChildIndex;
                leftChildIndex=2*rootIndex;                 
                numOfSwaps++; //keeps track of how many swaps          ///////////////////////////////////////////////////////////////////////////
            }
            else                                                                                            //break out of loop
                done=true;  
        }
        heap[rootIndex]=orphan;                                                                             //insert orphan back into appropriate place 
    }

    /**
     * 
     * @return
     */
    @Override
    public boolean isEmpty(){
        return lastIndex<1; 
    }
    /**
     * 
     * @return
     */
    @Override
    public int getSize(){
        return lastIndex;
    }
    /**
     * 
     */
    @Override
    public void clear(){
        checkInitalization();
        while (lastIndex>-1) { 
            heap[lastIndex]=-1;
            lastIndex--;
        }
        lastIndex=0;
    }

}