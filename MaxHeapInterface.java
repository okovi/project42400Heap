package project42400heap;
public  interface MaxHeapInterface{
    /**
     * 
     * @param newEntry
     * 
     */
    public void add ( int newEntry) ; 
    /**
     * 
     * @return
     */
    public int removeMax ();
    /**
     * 
     * @return
     */
    public int getMax();
    /**
     * 
     * @return
     */
    public boolean isEmpty();
    /**
     * 
     * @return
     */
    public int getSize();
    /**
     * 
     */
    public void clear();
}