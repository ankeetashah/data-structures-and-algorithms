<<<<<<< HEAD

=======
>>>>>>> 19071537c259f1cca0a6d2f4d0cbd488d222dcf1
/**
 * LinkedList class implements a doubly-linked list. Adapted from Weiss, Data
 * Structures and Algorithm Analysis in Java. 3rd ed.
 * http://users.cis.fiu.edu/~weiss/dsaajava3/code/SimpleLinkedList.java
 *
 *SimpleLinkedList.java contains 4 methods written by @author ankeetashah	
 *They include:
 *(a) public int indexOf(Object o)
 *(b) public void reverse()
 *(c) public void removeDuplicates()
 *(d) public void interleave (SimpleLinkedList<T> other
 */
public class SimpleLinkedList<T> implements Iterable<T> {

	private int size;
	private Node<T> beginMarker;
	private Node<T> endMarker;

	/**
	 * This is the doubly-linked list node class.
	 */
	private class Node<NodeT> {
		public Node(NodeT d, Node<NodeT> p, Node<NodeT> n) {
			data = d;
			prev = p;
			next = n;
		}

		public NodeT data;
		public Node<NodeT> prev;
		public Node<NodeT> next;
	}

	/**
	 * Construct an empty LinkedList.
	 */
	public SimpleLinkedList() {
		doClear();
	}

	/**
	 * Change the size of this collection to zero by initializing the beginning
	 * and end marker.
	 */
	public void doClear() {
<<<<<<< HEAD
		beginMarker = new Node<T>(null, null, null);
		endMarker = new Node<T>(null, beginMarker, null);
=======
		beginMarker = new Node<>(null, null, null);
		endMarker = new Node<>(null, beginMarker, null);
>>>>>>> 19071537c259f1cca0a6d2f4d0cbd488d222dcf1
		beginMarker.next = endMarker;

		size = 0;
	}

	/**
	 * @return the number of items in this collection.
	 */
	public int size() {
		return size;
	}

	/**
	 * @return boolean indicating if the linked list is empty
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * 
	 * @param idx
	 *          index to search at.
	 * @param lower
	 *          lowest valid index.
	 * @param upper
	 *          highest valid index.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *           if index is not between lower and upper, inclusive.
	 */
	private Node<T> getNode(int idx, int lower, int upper) {
		Node<T> p;

		if (idx < lower || idx > upper)
			throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: "
					+ size());

		if (idx < size() / 2) { // Search through list from the beginning
			p = beginMarker.next;
			for (int i = 0; i < idx; i++)
				p = p.next;
<<<<<<< HEAD
		} else { // search through the list from the end
=======
		} else { // serch through the list from the end
>>>>>>> 19071537c259f1cca0a6d2f4d0cbd488d222dcf1
			p = endMarker;
			for (int i = size(); i > idx; i--)
				p = p.prev;
		}
<<<<<<< HEAD
		
=======

>>>>>>> 19071537c259f1cca0a6d2f4d0cbd488d222dcf1
		return p;
	}

	/**
	 * Gets the Node at position idx, which must range from 0 to size( ) - 1.
	 * 
	 * @param idx
	 *          index to search at.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *           if index is out of range.
	 */
	private Node<T> getNode(int idx) {
		return getNode(idx, 0, size() - 1);
	}

	/**
	 * Returns the item at position idx.
	 * 
	 * @param idx
	 *          the index to search in.
	 * @throws IndexOutOfBoundsException
	 *           if index is out of range.
	 */
	public T get(int idx) {
		return getNode(idx).data;
	}

	/**
	 * Changes the item at position idx.
	 * 
	 * @param idx
	 *          the index to change.
	 * @param newVal
	 *          the new value.
	 * @return the old value.
	 * @throws IndexOutOfBoundsException
	 *           if index is out of range.
	 */
	public T set(int idx, T newVal) {
		Node<T> p = getNode(idx);
		T oldVal = p.data;

		p.data = newVal;
		return oldVal;
	}

	/**
	 * Adds an item in front of node p, shifting p and all items after it one
	 * position to the right in the list.
	 * 
	 * @param p
	 *          Node to add before.
	 * @param x
	 *          any object.
	 * @throws IndexOutOfBoundsException
	 *           if idx < 0 or idx > size()
	 */
	private void addBefore(Node<T> p, T x) {
<<<<<<< HEAD
		Node<T> newNode = new Node<T>(x, p.prev, p);
=======
		Node<T> newNode = new Node<>(x, p.prev, p);
>>>>>>> 19071537c259f1cca0a6d2f4d0cbd488d222dcf1
		newNode.prev.next = newNode;
		p.prev = newNode;
		size++;
	}

	/**
	 * Adds an item at specified index. Remaining items shift up one index.
	 * 
	 * @param x
	 *          any object.
	 * @param idx
	 *          position to add at.
	 * @throws IndexOutOfBoundsException
	 *           if idx < 0 or idx > size()
	 */
<<<<<<< HEAD
	public void add(int idx, T x) { 
=======
	public void add(int idx, T x) {
>>>>>>> 19071537c259f1cca0a6d2f4d0cbd488d222dcf1
		addBefore(getNode(idx, 0, size()), x);
	}

	/**
	 * Adds an item to this collection, at the end.
	 * 
	 * @param x
	 *          any object.
	 */
<<<<<<< HEAD
	public void add(T x) { // THIS IS THE METHOD THEY WERE TALKING ABOUT ON PIAZZA
=======
	public void add(T x) {
>>>>>>> 19071537c259f1cca0a6d2f4d0cbd488d222dcf1
		add(size(), x);
	}

	/**
	 * Removes the object contained in Node p.
	 * 
	 * @param p
	 *          the Node containing the object.
	 * @return the item was removed from the collection.
	 */
	private T remove(Node<T> p) {
		p.next.prev = p.prev;
		p.prev.next = p.next;
		size--;

		return p.data;
	}

	/**
	 * Removes an item from this collection.
	 * 
	 * @param idx
	 *          the index of the object.
	 * @return the item was removed from the collection.
	 */
	public T remove(int idx) {
		return remove(getNode(idx));
	}

	/**
	 * Returns a String representation of this collection.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		for (T x : this) {
			sb.append(x + " ");
		}
		sb.append("]");

		return new String(sb);
	}

	/**
	 * a) Method that returns the index of the first occurrence
	 * of the element o. Otherwise, it returns -1 if o is not found.
	 * 
	 * @param Object o 
	 * 		o is the element we are searching the index of 
	 * 
	 */
	public int indexOf(Object o) {
		Node<T> p = beginMarker.next;
		int index = 0;
		//iterates through the linked list to search for the index of element o 
		while (p.data != null) {	
			if (p.data.equals(o)) {
				return index;
			} else {
				p = p.next;
				index++;
			}
			
		}
		return -1; // returns -1 if o is not found 
	} 
	
	/**
	 * b) A method that reverses the list. 
	 * This version depends on the the length of the list. 
	 * This version utilizes the set() and get() methods provided. 
	 * 
	 * Another version of this method that does not depend on the length of the list is:
	 * could be to iteratively reverse a linked list. 
	 * This can be done by declaring the beginMaker and endMarker nodes
	 * Must include incrementer and decrementer methods and altering the add() method
	 * A potential implementation is outlined below
	 * 
	 * 
	 * beginMarker.next points to the beginning
     * beginMarker points to the end of this list
	 * 
	
	// how to iterate through list
	public void setReversed(boolean x) {
		if (x) {
		increment = new Decrementer<T>();
		} else {
			increment = new Incrementer<T>();
		}
	
	//incrementer and decrementer classes 
	private Incrementer<T> getIncremeter() {
		return increment;
	}
	
	private static class Incrementer<T> {
	
 		Node<T> getNext(Node<T> node) {
   			return node.next;
 		}

 		Node<T> getStart(Node<T> beginMarker) {
   			return beginMarker;
 		}
	}

	private static class Decrementer<T> extends Incrementer<T> { // with overridden methods

 		Node<T> getNext(Node<T> node) {
   			return node.prev;
 		}

 		Node<T> getStart(Node<T> beginMarker) {
   			return beginMarker.next;
 		}
	}
 
	 */
	
	public void reverse() {
	    int half = size / 2;
	    for (int i = 0; i < half; i++) {
	        int j = size - 1 - i; // position of matching element at the other end
	        T item = get(i); 
	        set(i, get(j)); // swapping positions
	        set(j, item);
	    }
	}
	
	/**
	 * c) This method removes duplicate elements in the list.
	 * Makes changes to the original linked list.
	 * 
	 * 
	 * 
	 * in `[ 1 2 3 5 8 ]` .
How many `.equals` comparisons does `removeDuplicates()` take, as a function of **N** (the number of elements in the list)?
Can you come up with a faster algorithm if you assume that the list is sorted in increasing order (describe your answer in a comment)?

	 * There are removeDuplicates() method takes O(N^2) ".equals" 
	 * 		This is because we have nested for loops 
	 * 		Everything inside the first loop (inner) as a linear O(N)
	 * 		Outermost loop, takes O(N)
	 * 		Therefore, total is O(N^2)
	 * A faster algorithm if we had a sorted list:
	 * 
	 * 
	while( beiginMarker!= null && beginmarker.next != null){
            if(beginMarker.data.equals(beginMarker.next.data){
                beginMarker.next = beginMarker.next.next; // removing the duplicate 
            }else{
                beginMarker = beginMarker.next; 
            }
        }
 
 	 *
	 */
	public void removeDuplicates() {
		for(int i = 0; i < size; i++) { 
			for(int j = size; --j > i; )
			
		    {
		        if(get(i).equals(get(j)))
		
		        {
		        	remove(j);
		
		        }
		
		    }
		
		} 
		
	}


	/**
	 * d) This method interleaves the original list with list other.
	 * Appends the original list with remaining elements of other list if other is longer than original.
	 */

		public void interleave(SimpleLinkedList<T> other) {
			 java.util.Iterator<T> iterator  = other.iterator();

		    int count = 1;

		    if(this.size() >= other.size())
		    {
		       while(iterator.hasNext())
		       {
		           this.add(count, iterator.next());

		           count = count + 2;
		       }
		    }
		    else if(this.size() < other.size())
		    {
		       while(iterator.hasNext())
		       {
		           if(count <= this.size())
		           {
		               this.add(count, iterator.next());
		               count = count + 2;
		           }
		           else
		           {
		               //appends end 
		        	   this.add(iterator.next());
		           }
		       }
		    }
		}
		

	/**
	 * Obtains an Iterator object used to traverse the collection.
	 * 
	 * @return an iterator positioned prior to the first element.
	 */
	public java.util.Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	/**
	 * This is the implementation of the LinkedListIterator. It maintains a notion
	 * of a current position and of course the implicit reference to the
	 * SimpleLinkedList.
	 */
	private class LinkedListIterator implements java.util.Iterator<T> {
		private Node<T> current = beginMarker.next;
		private boolean okToRemove = false;

		public boolean hasNext() {
			return current != endMarker;
		}

		public T next() {
			if (!hasNext())
				throw new java.util.NoSuchElementException();

			T nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		public void remove() {
			if (!okToRemove)
				throw new IllegalStateException();

			SimpleLinkedList.this.remove(current.prev);
			// ensures that remove() cannot be called twice during a single step in
			// iteration
			okToRemove = false;
		}
	}
	
	public static void main (String [] args) {
		
	}
	
}

	/**
	 * Test the linked list.
	 */
	public static void main(String[] args) {
		SimpleLinkedList<Integer> lst = new SimpleLinkedList<>();

		for (int i = 0; i < 10; i++)
			lst.add(i);
		for (int i = 20; i < 30; i++)
			lst.add(0, i);

		lst.remove(0);
		lst.remove(lst.size() - 1);

		System.out.println(lst);

	}
}
