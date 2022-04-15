import java.util.Arrays;
import java.util.Scanner;
public final class MaxHeap<Integer> { 

    private Integer[] heap; 
    private int lastIndex; 
    private boolean initialized=false; 
    private static final int DEFAULT_CAPACITY=25; 
    private static final int MAX_CAPACITY=10000; 

    /*--------------Methods to be implemented for Project 4------------*/
    /**
     * 
     * @param inputTextFile
     */
    private MaxHeap(File inputTextFile) { //using add method repeatedly
        boolean done=false;
        Integer[] parsedInput=new Comparable[100];
        parsedInput=parseFile(inputTextFile);                 
        int numOfSwaps=0; 
        heap[1]=parsedInput[0];
        lastIndex++;
        for (int i=1;i<parsedInput.length;i++) 
        {
            checkInitalization();
            int newIndex=lastIndex+1;
            int parentIndex=newIndex/2; 
            while ((parentIndex>0) && (parsedInput[i].compareTo(heap[parentIndex])>0))
            {
                heap[newIndex]=heap[parentIndex]; //move parent down
                newIndex=parentIndex; // get next possible posotion
                parentIndex=newIndex/2; //continue loop
                numOfSwaps++; //keeps track of how many swaps
            }
            heap[newIndex]=parsedInput[i];
            lastIndex++; 
            ensureCapacity();
        }
        checkCapcity(parsedInput.length);
        initialized=true;
    }
    /**
     * 
     * @param elemIndex
     * @return
     */
    private Integer remove(int elemIndex){ 
        checkInitalization();
        T elem =null;
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
     * @param toBeParsed
     * @return
     */
    public Integer[] parseFile(File toBeParsed) {
        Scanner reader=new Scanner(toBeParsed);
        Integer[] parsed=new Integer[100];
        int i=0;
        while (reader.hasNextLine())  
            parsed[i++]=reader.nextInt();
        return parsed;
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
        @SuppressWarnings("unchecked")
        T[] tempHeap=(T[]) new Comparable[initialCapacity+1];
        heap=tempHeap;
        lastIndex=0;
        initialized=true;
     }
    /**
     * 
     * @param newEntry
     */
    public void add ( T newEntry)  {
        checkInitalization();
        int newIndex=lastIndex+1;
        int parentIndex=newIndex/2; //int div takes care of floor fxn 
        while ((parentIndex>0) && (newEntry.compareTo(heap[parentIndex])>0))
        {
            heap[newIndex]=heap[parentIndex]; //move parent down
            newIndex=parentIndex; // get next possible posotion
            parentIndex=newIndex/2; //continue loop
        }
        heap[newIndex]=newEntry;
        lastIndex++; 
        ensureCapacity(); 
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
    public T removeMax (){
        checkInitalization();
        T root =null;
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
        bool done=false; 
        T orphan=heap[rootIndex];                                                                           //copy of the root, should make a copy/clone method because this will probably overwrite it, I think. 
        int leftChildIndex=2*rootIndex; 
        while (!done && (leftChildIndex<=lastIndex) ) 
        {
            int largerChildIndex=leftChildIndex;                                                            //assume left-child is larger
            int rightChildIndex=leftChildIndex+1;
            if ((rightChildIndex<=lastIndex)&& heap[rightChildIndex].compareTo(heap[largerChildIndex])>0)   // check for + get larger child
                largerChildIndex=rightChildIndex;
            if (orphan.compareTo(heap[largerChildIndex])<0)                                                 // compare to child-max, swap if current is smaller + continue loop
            {
                heap[rootIndex]=heap[largerChildIndex];                                                     // set up for top of while
                rootIndex=largerChildIndex;
                leftChildIndex=2*rootIndex;                 
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
    public T getMax(){
        checkInitalization();
        return heap[1];
    }
    /**
     * 
     * @return
     */
    public boolean isEmpty(){
        return lastIndex<1; 
    }
    /**
     * 
     * @return
     */
    public int getSize(){
        return lastIndex;
    }
    /**
     * 
     */
    public void clear(){
        checkInitalization();
        while (lastIndex>-1) { 
            heap[lastIndex]=null;
            lastIndex--;
        }
        lastIndex=0;
    }

}
