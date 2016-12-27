import java.awt.*;
import java.util.*;
/******************************************************
The Lion class extends GVcritter.  This gives us access
to all public or protected members in GVcriter.

@author Scott Grissom
@version August 2016
 ******************************************************/
public class Lion extends GVcritter{

    // start direction for this Ant: south or north
    private Direction dir;    
    private Random ran = new Random();
    

    /*****************************************************
    Create starting values for this Ant.
    @param loc given location for this critter
     *****************************************************/  
    public Lion(Location loc){
        super(loc); 
        setColor(new Color(255, 155, 0));
        setSpecies(Species.LION);

        // pick a random start direction
        int next = ran.nextInt(4);
        if (next == 0){
            dir = Direction.NORTH;
        }

        if (next == 1){
            dir = Direction.SOUTH;
        }

        if (next == 2){
            dir = Direction.EAST;
        }

        if (next == 3){
            dir = Direction.WEST;
        }

    }

    /*****************************************************
    Ants always SCRATCH their opponents

    @param opponent who is the critter fighting?
    @return attack strategy
     *****************************************************/     
    public Attack getAttack(GVcritter opponent) {
        if(Math.random() < 0.5){
            return Attack.ROAR;
        }
        else{
            return Attack.POUNCE;
        }
    }

    /*****************************************************
    Ants move in a southeast or norteast direction

    @return desired direction of next step
     *****************************************************/     
    public Direction getMoveDirection(){

        // increment the steps for this Ant
        steps++;

        // 
        int next = ran.nextInt(3);
        if (steps % 4 == 0){
            if (next == 0){
                dir = Direction.WEST;
            }

            if (next == 1){
                dir = Direction.SOUTH;
            }

            if (next == 2){
                dir = Direction.EAST;
            }
        }

        return dir;
    }
}