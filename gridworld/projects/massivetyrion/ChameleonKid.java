import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.awt.Color;
import java.util.*;

public class ChameleonKid extends ChameleonCritter {

    //adopted from CrabCritter
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }

        return actors;
    }

public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
        }
        return locs;
    }    
 
    public void processActors(ArrayList<Actor> actors){
	int n = actors.size();
	if (n == 0){
	    Color c = getColor();
	    int red = (int) (c.getRed() * (1 - .05));
	    int green = (int) (c.getGreen() * (1 - .05));
	    int blue = (int) (c.getBlue() * (1 - .05));
	    
	    setColor(new Color(red, green, blue));
	    return;
	}
        setColor(actors.get(0).getColor());
    }
 
}
