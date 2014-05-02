/*****************************************************************
 * ANDREW FAN --- MASSIVE TYRION
 * APCS pd 9
 * HW35 -- GridWorld, Part 5
 * 2014-05-01
 *
 * class CUPIDCritter
 *
 * BEACUASE:
 * I do not know because I was out the day the critter was decided
 *
 * SPECIFICATIONS:
 * Will look at its 4 neighbors only. (directly ahead, to the back of, to the right and to the left of Cupid)
 * Will choose randomly whether to look up-down or sideways first.
 * If there are Actors (to the right && to the left) || (upwards && downwards), then the pair will be turned pink (or red) and Cupid will switch locations with one of the members of the couple (doesn't matter which member, can be random).
 *
 * TEST CASES:
 * 1. Cupid has no neighbors or 1 member or 2 members but they're not across from one another:
 * --> nothing should be performed, he should just take one move forward/turn
 * 2. 
		x A x   or  x x x    ---->  x C x   or   x x x
		x C x       A C A           x A x        A A C
		x A x       x x x           x A x        x x x
	
 * Illustrated above are the couples and their end positions after the move.
 * x = empty space or other critters or flowers( doesn't really matter what the space is occupied by)


 *
 * ERRATA:
 * <any extra test cases, any developer decisions, or
 * other judgment calls your team made to get the class working...>
 *****************************************************************/

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import java.awt.Color;
import java.util.*;


public class CupidCritter extends Critter {

    public ArrayList<Actor> getActors(){
	ArrayList<Actor> actors = new ArrayList<Actor>();

	int dirfirst = (int)(Math.random()*2); //0 or 1
	boolean nonull = false;
	
	if(dirfirst==0){
	    int[] dirs = { Location.AHEAD, Location.HALF_CIRCLE };
	    for (Location loc : getLocationsInDirections(dirs)){
		Actor a = getGrid().get(loc);
		if (a != null){
		    actors.add(a);
		    nonull=true;
		}else{
		    nonull=false;
		    break;
		}
	    }
	}else{
	    int[] dirs = { Location.RIGHT, Location.LEFT };
	    for (Location loc : getLocationsInDirections(dirs)){
		Actor a = getGrid().get(loc);
		if (a != null){
		    actors.add(a);
		    nonull=true;
		}else{
		    nonull=false;
		    break;
		}
	    }
	}

	if(nonull==false){
	    for(int i = 0; i<actors.size(); i++){
		actors.remove(i);//Purge if not valid
	    }
	}else{
	    return actors;//Break out
	}

	//Second try, other pair tested
	nonull = false;
	if(dirfirst==1){
	    int[] dirs = { Location.AHEAD, Location.HALF_CIRCLE };
	    for (Location loc : getLocationsInDirections(dirs)){
		Actor a = getGrid().get(loc);
		if (a != null){
		    actors.add(a);
		    nonull=true;
		}else{
		    nonull=false;
		    break;
		}
	    }
	}else{
	    int[] dirs = { Location.RIGHT, Location.LEFT };
	    for (Location loc : getLocationsInDirections(dirs)){
		Actor a = getGrid().get(loc);
		if (a != null){
		    actors.add(a);
		    nonull=true;
		}else{
		    nonull=false;
		    break;
		}
	    }
	}


	if(nonull==false){//All four directions have none.
	    for(int i = 0; i<actors.size(); i++){
		actors.remove(i);//Purge if not valid
	    }
	}else{
	    return actors;//Break out
	}

	return actors;//Will check for size in processActors
    }

    public ArrayList<Location> getLocationsInDirections(int[] directions){
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
	if (n < 2){
	    return;//Failure
	}

	for (Actor a : actors){
	    if(a!=null){a.setColor(Color.PINK);}
	}

	//It's impossible to swap positions, so... workarounds.
	Grid g = new BoundedGrid(1,1);

	if(Math.random()<0.5){
	    Location temploc = actors.get(0).getLocation();
	    Location currloc = this.getLocation();
	    actors.get(0).putSelfInGrid(g, new Location(0,0));
	    this.moveTo(temploc);
	    actors.get(0).putSelfInGrid(this.getGrid(), currloc);
	}else{
	    Location temploc = actors.get(1).getLocation();
	    Location currloc = this.getLocation();
	    actors.get(1).putSelfInGrid(g, new Location(0,0));
	    this.moveTo(temploc);
	    actors.get(1).putSelfInGrid(this.getGrid(), currloc);
	}
    }

}
