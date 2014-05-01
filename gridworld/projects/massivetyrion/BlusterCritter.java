import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;

//Code written by Andrew Fan

/**
 * A BlusterCritter looks at all neighbors within 2 spaces and counts the number of critters. If there are less than c critters (max 24), color brightens else darkens where c is courage
 */
public class BlusterCritter extends Critter{

    private int courage;

    public BlusterCritter(int c){
	super();
	courage = c;
    }

    public ArrayList<Actor> getActors(){
	ArrayList<Actor> actors = new ArrayList<Actor>();
	Location loc = this.getLocation();
	for(int i = loc.getRow()-2; i<=loc.getRow()+2; i++){
	    for(int j = loc.getCol()-2; j<=loc.getCol()+2; j++){
		Location temp = new Location(i, j);
		if(this.getGrid().isValid(temp)){
		    Actor acttoadd = this.getGrid().get(temp);
		    if(acttoadd!=null&&acttoadd instanceof Critter){
			actors.add(acttoadd);
		    }
		}
	    }
	}
	actors.remove(this);//Remove self    
	return actors;
    }

    public void processActors(ArrayList<Actor> actors){
        if(actors.size()>=this.courage){
	    this.setColor(this.getColor().darker());
	}else{
	    this.setColor(this.getColor().brighter());
	}
    }
}
