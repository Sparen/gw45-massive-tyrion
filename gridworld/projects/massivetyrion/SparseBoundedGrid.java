/*
SparseBoundedGrid has a more efficient runtime since the regular bounded grid needs to use two for loops to find the stored Location target, giving it a runtime dependent on how many rows and columns there are. It makes unecessary traversals over rows that don't belong to the target. However, this one finds the row quicker and only performs the traversal after having the target's row.
 */

//REFERENCED BOUNDEDGRID WHILE WRITING

package info.gridworld.grid;

import java.util.*;

public class SparseBoundedGrid{ 

    private SparseGridNode[] arr;
    private int rows;
    private int cols;

    class SparseGridNode {
	
	private Object occupant;
	private int col;
	private SparseGridNode next;
	
	public SparseGridNode (Object occ, int c,SparseGridNode nextNode){
	    occupant = occ;
	    col = c;
	    next = nextNode;
	}

	public Object getOccupant(){
	    return occupant;
	}

	public int getCol(){
	    return col;
	}

	public SparseGridNode getNext(){
	    return next;
	}

	public void setOccupant(Object obj){
	    occupant = obj;
	}

	public void setCol(int k){
	    col = k;
	}

	public void setNext(SparseGridNode node){
	    next = node;
	}

    }

    public SparseBoundedGrid (int a, int b){
	if (a >= 0 && b >= 0){
	    rows = a;
	    cols = b;
	}
	else {
	    throw new IllegalArgumentException();
	}
	arr = new SparseGridNode[rows];
    }

    public int getNumRows(){
	return rows;
    }

    public int getNumCols(){
	return cols;
    }

    public boolean isValid (Location loc){
	return loc.getRow() >= 0 && loc.getRow() < rows && loc.getCol() >= 0 && loc.getCol() < cols;
    }

    public Object put (Location loc, Object obj){
	if (!isValid(loc)){
	    throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
	}
	if (obj == null){
	    throw new NullPointerException ("obj == null");
	}

	SparseGridNode fr = arr[loc.getRow()];
	SparseGridNode node = new SparseGridNode(loc.getRow(),loc.getCol(),fr);
	return fr.getOccupant();
    }

    public Object remove (Location loc){
        if (!isValid(loc)){
	    throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
	}
		
	SparseGridNode t = arr[loc.getRow()];
	while (t.getNext().getCol() != loc.getCol()){
	    t = t.getNext();
	}
	Object obj = t.getNext().getOccupant();
	t.setNext(t.getNext().getNext());
	return obj;
    }

    public Object get (Location loc){
	if (!isValid(loc)){
	    throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
	}
	
	SparseGridNode t = arr[loc.getRow()];
	while (t.getCol() != loc.getCol()){
	    t.getNext();
	}
	return t.getOccupant();
    }

    public ArrayList<Location> getOccupiedLocations() {
	ArrayList<Location> occupied = new ArrayList<Location>();
	for (int i = 0; i < rows; i++){
	    SparseGridNode tmp = arr[i];
	    while (tmp != null){
		if (tmp.getOccupant() != null){
		    occupied.add(new Location (i,tmp.getCol()));
		}
	    }
	}
	return occupied;
    }
    /* I don't see these methods in BoundedGrid so I guess I won't do it either.
    public ArrayList<Location> getValidAdjacentLocations(Location loc){

    }

    public ArrayList<Location> getEmptyAdjacentLocations(Location loc){

    }

    public ArrayList<Location> getOccupiedAdjacentLocations(Location loc){

    }

    public ArrayList<Object> getNeighbors(Location loc){

    }
    */
}
