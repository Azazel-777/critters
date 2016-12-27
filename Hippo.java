import java.awt.*;
import java.util.*;
/******************************************************
The Ant class extends GVcritter.  This gives us access
to all public or protected members in GVcriter.

@author Scott Grissom
@version August 2016
 ******************************************************/
public class Hippo extends GVcritter{

    // start direction for this Ant: south or north
    private Direction dir;
    private int oldAge;
    private Random ran = new Random();

    /*****************************************************
    Create starting values for this Hippo.
    @param loc given location for this critter
     *****************************************************/  
    public Hippo(Location loc){
        super(loc); 
        setColor(Color.GRAY);
        setSpecies(Species.HIPPO);

        oldAge = ran.nextInt(200) + 301;

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
    Hippos always POUNCE on their opponents

    @param opponent who is the critter fighting?
    @return attack strategy
     *****************************************************/     
    public Attack getAttack(GVcritter opponent) {
        if (steps >= oldAge){
            return Attack.FORFEIT;
        }
        return Attack.POUNCE;
    }

    /*****************************************************
    Ants move in a random direction every 5 steps

    @return desired direction of next step
     *****************************************************/     
    public Direction getMoveDirection(){

        // increment the steps for this Hippo
        steps++;

        // choose new random direction every 5 steps
        int next = ran.nextInt(4);
        if (steps % 5 == 0){
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
        
        return dir;
    }
}