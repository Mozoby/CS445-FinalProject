
package edu.cpp.cs445;

import java.util.LinkedList;
import java.util.ListIterator;

/** Custom LinkedList guaranteeing that elements remain sorted after adding.
 * 
 * @author Bryan Thornbury
 */
public class SortedLinkedList<T extends Comparable<T>> extends LinkedList<T> {
	//not sure why we need this, but we do it seems
	private static final long serialVersionUID = -7489312244551628717L;
	private boolean ascending;
	
	/** Constructor
	 * @param ascending if true this list will be in ascending order (first -> last)
	 */
	public SortedLinkedList(boolean ascending){
		super();
		this.ascending = ascending;
	}
	
	@Override
	public boolean add(T element){
		ListIterator<T> it = this.listIterator();
		
		boolean added = false;
		while(it.hasNext()){
			T c = it.next();
			if((element.compareTo(c) < 0 && ascending) || (element.compareTo(c) > 0 && !ascending)){
				added=true;
				//replace current element and then add it again after
				it.set(element);
				it.add(c);
				break;
			}
		}
		
		if(!added){
			this.addLast(element);
		}
		
		return true;
	}
}
