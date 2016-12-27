import java.awt.*;
import java.util.*;
/******************************************************
The Ant class extends GVcritter.  This gives us access
to all public or protected members in GVcriter.

@author Scott Grissom
@version August 2016
 ******************************************************/
public class Bird extends GVcritter{

    // start direction for this Ant: south or north
    private Direction dir;    

    /*****************************************************
    Create starting values for this Ant.
    @param loc given location for this critter
     *****************************************************/  
    public Bird(Location loc){
        super(loc); 
        setColor(Color.BLUE);
        setSpecies(Species.BIRD);

        // pick a random start direction (north or south)
        Random ran = new Random();
        steps = ran.nextInt(14);
    }

    /*****************************************************
    Ants always SCRATCH their opponents

    @param opponent who is the critter fighting?
    @return attack strategy
     *****************************************************/     
    public Attack getAttack(GVcritter opponent) {
        return Attack.ROAR;
    }

    /*****************************************************
    Ants move in a southeast or norteast direction

    @return desired direction of next step
     *****************************************************/     
    public Direction getMoveDirection(){

        // increment the steps for this Ant
        steps++;

        // alternate directions every other step
        if(steps % 14 < 3){
            dir = Direction.NORTH;
        }
        else if(steps % 14 < 7){
            dir = Direction.EAST;
        }
        else if(steps % 14 < 10){
            dir = Direction.SOUTH;
        }
        else if(steps % 14 < 14){
            dir = Direction.WEST;
        }

        return dir;
    }
}