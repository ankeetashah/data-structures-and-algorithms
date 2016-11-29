/**
 * Range.java simulates python's range function
 */

import java.util.Iterator;

/**
 * The Range class implements the Iterable<> interface.
 * It takes in the instance variables (integers):
 * int start
 * int stop
 * int increment
 * int remaining
 *
 */
public class Range implements Iterable<Integer> {
	private int start;
	private int stop;
	private  int increment;
    private int remaining;
    
    /**
     * First constructor 
     * @param start
     * @param stop
     */   
    public Range(int start, int stop) {
    	this.start = start;
        this.stop = stop;
        increment = 1;
		remaining = stop-start;
		
	}

    /**
     * Second constructor 
     * @param start
     * @param stop
     * @param incr
     * 
     */
	public Range(int start, int stop, int incr) {
        this(start, stop);
		increment = incr;
		if (increment >1){
			remaining = Math.abs(stop)/Math.abs(increment);
		} else if (increment ==1 ) {
			remaining =Math.abs(stop)-Math.abs(start);
		} else if (increment ==-1) {
			remaining = Math.abs(start)-Math.abs(stop);
		} else if (increment <-1){
			remaining = Math.abs(start)/Math.abs(increment); // says there are two values, but really only one  6/3 = 2
		} else {
			remaining = 0;
		}
		
		
	}

	 
	/**
	 * The iterator() method iterates through the list of numbers outlined in the range.
	 *  
	 */
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
	        	private int current = start;
	            private int add = increment;
	           
	            /**
	             * @Override hasNext() from classical iterator() method	            
	             */
	            public boolean hasNext() {
	                return remaining != 0;
	            }

	            /**
	             * @Override next() from classical iterator() method	  	            
	             */
	            public Integer next() {
	            	if (!hasNext()) {
	
	            	}
	            	
	            	Integer nextItem = current;
	                current+=add;
	                remaining--;
	                return nextItem;
  
	            }

	           
	            public void remove() {
	                throw new UnsupportedOperationException("Unsupported");
	            }
	        };
	    }

}


