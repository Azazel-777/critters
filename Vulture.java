import java.awt.*;
import java.util.*;
/******************************************************
The Vulture class extends GVcritter.  This gives us access
to all public or protected members in GVcriter.

@author Scott Grissom
@version August 2016
 ******************************************************/
public class Vulture extends GVcritter{
    
    private Direction dir;    

    /*****************************************************
    Create starting values for this Vulture.
    @param loc given location for this critter
     *****************************************************/  
    public Vulture(Location loc){
        super(loc); 
        setColor(Color.BLACK);
        setSpecies(Species.VULTURE);

        // pick a random start direction (north or south)
        Random ran = new Random();
        steps = ran.nextInt(13);
    }

    /*****************************************************
    Vultures SCRATCH hippos, and roar at anything else

    @param opponent who is the critter fighting?
    @return attack strategy
     *****************************************************/     
    public Attack getAttack(GVcritter opponent) {
        if (opponent.getSpecies() == Species.HIPPO){
            return Attack.SCRATCH;
        }
        return Attack.ROAR;
    }

    /*****************************************************
    Vultures fly counterclockwise

    @return desired direction of next step
     *****************************************************/     
    public Direction getMoveDirection(){

        // increment the steps for this Vulture
        steps++;

        // move in counterclockwise pattern
        if(steps % 14 < 3){
            dir = Direction.NORTH;
        }
        else if(steps % 14 < 7){
            dir = Direction.WEST;
        }
        else if(steps % 14 < 10){
            dir = Direction.SOUTH;
        }
        else if(steps % 14 < 14){
            dir = Direction.EAST;
        }
        
        return dir;
    }
}