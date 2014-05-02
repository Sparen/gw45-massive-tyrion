/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

package info.gridworld.grid;

import java.util.ArrayList;
import java.util.*;


/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid<E> extends AbstractGrid<E>
{
    private Map<Location, E> occupantMap;
    private E[][] grid;

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid()
    {
        // grid = new E[16][16];
	grid = (E[][]) new Object[16][16];
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return true;
    }


    public E get(Location loc) //I believe this function has constant runtime, as it simply returns an element from a 2d array
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return grid[loc.getRow()][loc.getCol()];
    }

    public E put(Location loc, E obj) //I beleive this has a constant runtime when the location is within bounds and an n^2 runtime when it is outside of bounds.
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");

        if (loc.getRow() > grid.length-1 || loc.getCol() > grid.length-1) {
	    int n = 1;
	    while (loc.getRow() > (grid.length*(int)Math.pow(2,n))-1 || loc.getCol() > (grid.length*(int)Math.pow(2,n))-1) 
		n++;

	    E[][] tmp = (E[][])new Object[grid.length*(int)Math.pow(2,n)][grid.length*(int)Math.pow(2,n)]; //is a square so should be able to use both
	    for (int i = 0; i < grid.length; i++) {
		for (int j = 0; j < grid.length; j++) {
		    tmp[i][j] = grid[i][j];
		}
	    }
	    grid = tmp;
	}
	E retval = grid[loc.getRow()][loc.getCol()];
        grid[loc.getRow()][loc.getCol()] = obj;
	return retval;
    }

    public E remove(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        E retval = grid[loc.getRow()][loc.getCol()];
        grid[loc.getRow()][loc.getCol()] = null;
	return retval;
    }

    public ArrayList<Location> getOccupiedLocations() {
	ArrayList<Location> retvals = new ArrayList<Location>();
	for (int i = 0; i < grid.length; i++) {
		for (int j = 0; j < grid.length; j++) {
		    tmp = grid[i][j];
		    if (tmp != null) {
			retvals.add(new Location(i,j));
		    }
		}
	}
	return retvals;
    }

    public ArrayList<Location> getValidAdjacentLocations(Location loc) {
	if (loc == null)
            throw new NullPointerException("loc == null");

	ArrayList<Location> retvals = new ArrayList<Location>();
	for (int i = loc.getRow()-1; i < loc.getRow()+1; i++) {
		for (int j = loc.getCol()-1; j < loc.getCol()+1; j++) {
		    if (i != loc.getRow() && j != loc.getCol()) {
			tmp = new Location(i,j);
			if (isValid(tmp)) {
			    retvals.add(tmp);
			}
		    }
		}
	}
	return retvals;
    }
    public ArrayList<Location> getEmptyAdjacentLocations(Location loc) {
	if (loc == null)
            throw new NullPointerException("loc == null");

	ArrayList<Location> locs = getValidAdjacentNeighbors(loc);
	ArrayList<Location> retval = new ArrayList<Location>();
	for (Location l : locs) {
	    if (get(l) = null)
		retval.add(l);
	}
	return retval;
    }

    public ArrayList<Location> getOccupiedAdjacentLocations(Location loc) {
	if (loc == null)
            throw new NullPointerException("loc == null");

	ArrayList<Location> locs = getValidAdjacentNeighbors(loc);
	ArrayList<Location> retval = new ArrayList<Location>();
	for (Location l : locs) {
	    if (get(l) != null)
		retval.add(l);
	}
	return retval;

    }

    public ArrayList<E> getNeighbors(Location loc) {
	if (loc == null)
            throw new NullPointerException("loc == null");

	ArrayList<Location> locs = getOccupiedAdjacentLocations(loc);
	ArrayList<E> retval = new ArrayList<E>();
	for (Location l : locs)
	    retval.add(get(l));
	return retval;
}

}
