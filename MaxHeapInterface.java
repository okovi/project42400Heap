public  interface MsxHeapInterface <T extends Comparable < ? super T >>{
    /**
     * 
     * @param newEntry
     */
    public void add ( T newEntry) ; 
    /**
     * 
     * @return
     */
    public T removeMax ();
    /**
     * 
     * @return
     */
    public T getMax();
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