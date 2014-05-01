//Andrew Fan

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>QuickCrab</code> is a crab that moves two spaces
 */
public class QuickCrab extends CrabCritter
{
    public QuickCrab()
    {
        setColor(Color.BLUE);
    }

    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
	Location here = this.getLocation();

        ArrayList<Location> locs = new ArrayList<Location>();

	Location loc = here.getAdjacentLocation(Location.LEFT);
	if(this.getGrid().isValid(loc)&&this.getGrid().get(loc)==null){
	    loc = loc.getAdjacentLocation(Location.LEFT);
	    if(this.getGrid().isValid(loc)&&this.getGrid().get(loc)==null){
		locs.add(loc);
	    }
	}

	loc = here.getAdjacentLocation(Location.RIGHT);
	if(this.getGrid().isValid(loc)&&this.getGrid().get(loc)==null){
	    loc = loc.getAdjacentLocation(Location.RIGHT);
	    if(this.getGrid().isValid(loc)&&this.getGrid().get(loc)==null){
		locs.add(loc);
	    }
	}

	if(locs.size()>0){return locs;}else{return super.getMoveLocations();}
        
    }

    public void makeMove(Location loc)
    {
        if (loc.equals(getLocation()))
        {
            double r = Math.random();
            int angle;
            if (r < 0.5)
                angle = Location.LEFT;
            else
                angle = Location.RIGHT;
            setDirection(getDirection() + angle);
        }
        else
            super.makeMove(loc);
    }
    
}
