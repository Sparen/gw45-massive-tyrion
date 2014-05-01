import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.awt.Color;
import java.util.*;

public class RockHound extends Critter {

    public void processActors(ArrayList<Actor> actors){
	int n = actors.size();
	if (n == 0){
	    return;
	}
	else {
	    for (int i = 0; i < n; i++){
		if (actors.get(i) instanceof Rock){
		    actors.get(i).removeSelfFromGrid();
		}
	    }
	}
    }

}
