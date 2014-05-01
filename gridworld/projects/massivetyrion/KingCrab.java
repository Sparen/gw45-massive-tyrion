//Andrew Fan

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>KingCran</code> looks at a limited set of neighbors when it eats and moves. The assignment is to literally not do anything because if it eats other actors, it can't then push them one space (because they don't exist anymore...)
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter
{
    public KingCrab()
    {
        setColor(Color.MAGENTA);
    }

    public void processActors(ArrayList<Actor> actors){
	for(Actor a : actors){
	    if(!(a instanceof Rock) && !(a instanceof Critter)){
		if(getGrid().get(a.getLocation().getAdjacentLocation(Location.HALF_CIRCLE))==null){
		    a.moveTo(a.getLocation().getAdjacentLocation(Location.HALF_CIRCLE));
		}else{
		    a.removeSelfFromGrid();
		}
	    }
	}
    }
}
