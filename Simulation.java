import java.util.*;import javax.swing.*;import java.awt.*;/**************************************************** * Simulates a 2D world of critters that move around * and fight if they inhabit the same location. *  * @author Scott Grissom * @author Noah Golay * @version  12/1/16 ***************************************************/public class Simulation extends JPanel{    /** a 2D world of critters */    private GVcritter[][] theWorld;    /** a collection of all live critters */    private ArrayList <GVcritter> allCritters;    /** control size of the world */    private final int ROWS=50, COLUMNS=70, SIZE=10;    /** number of each critter alive in the simulation */    private int numAnts, numBirds, numHippos, numVultures, numLions;    /** total number of steps taken by all critters */    private int stepCount;    /****************************************************    Constructor instantiates and initializes all     instance members.     ****************************************************/    public Simulation(){        theWorld = new GVcritter[ROWS][COLUMNS];        allCritters = new ArrayList<GVcritter>();           numAnts = 0;           numBirds = 0;        numHippos = 0;        numVultures = 0;        numLions = 0;        stepCount = 0;        // set the appropriate size of the invisibile drawing area        setPreferredSize(new Dimension(COLUMNS*SIZE, ROWS*SIZE));    }    /****************************************************    Add the requested number of Ants into the simulation.    Repeatedly ask for a random location that is free.    Increment the number of Ants in the simulation.    @param num number of ants     ****************************************************/     public void addAnts(int num){        numAnts += num;        for(int i=1;i<=num;i++){            // create a new Ant at an open location            Location loc = getOpenLocation();            Ant c = new Ant(loc);            placeCritter(c);        }    }        /****************************************************    Add the requested number of Birds into the simulation.    Repeatedly ask for a random location that is free.    Increment the number of Birds in the simulation.    @param num number of birds     ****************************************************/     public void addBirds(int num){        numBirds += num;        for(int i=1;i<=num;i++){            // create a new Bird at an open location            Location loc = getOpenLocation();            Bird c = new Bird(loc);            placeCritter(c);        }    }        /****************************************************    Add the requested number of Hippos into the simulation.    Repeatedly ask for a random location that is free.    Increment the number of Hippos in the simulation.    @param num number of hippos     ****************************************************/     public void addHippos(int num){        numHippos += num;        for(int i=1;i<=num;i++){            // create a new Hippo at an open location            Location loc = getOpenLocation();            Hippo c = new Hippo(loc);            placeCritter(c);        }    }        /****************************************************    Add the requested number of Vultures into the simulation.    Repeatedly ask for a random location that is free.    Increment the number of Vultures in the simulation.    @param num number of vultures     ****************************************************/     public void addVultures(int num){        numVultures += num;        for(int i=1;i<=num;i++){            // create a new Vulture at an open location            Location loc = getOpenLocation();            Vulture c = new Vulture(loc);            placeCritter(c);        }    }        /****************************************************    Add the requested number of Lions into the simulation.    Repeatedly ask for a random location that is free.    Increment the number of Lions in the simulation.    @param num number of lions     ****************************************************/     public void addLions(int num){        numLions += num;        for(int i=1;i<=num;i++){            // create a new Lion at an open location            Location loc = getOpenLocation();            Lion c = new Lion(loc);            placeCritter(c);        }    }         /***************************************************     * display number of remaining critters and step count     **************************************************/    public String getStats(){        return " Steps:\t" + stepCount + "\n Ants:\t" + numAnts + "\n Birds:\t" + numBirds + "\n Hippos:\t" + numHippos + "\n Vultures:\t" + numVultures + "\n Lions:\t" + numLions;    }    /***************************************************     * finds a location in the world where there is     * currently no critter     **************************************************/    private Location getOpenLocation(){        Random ran = new Random();        Location open = new Location(ran.nextInt(ROWS),ran.nextInt(COLUMNS));        while(theWorld[open.getRow()][open.getCol()] != null){            open.setRow(ran.nextInt(ROWS));            open.setCol(ran.nextInt(COLUMNS));        }        return open;    }    /***************************************************     * adds a critter to the world     **************************************************/    private void placeCritter(GVcritter c){        allCritters.add(c);        Location loc = c.getLocation();        theWorld[loc.getRow()][loc.getCol()] = c;    }    /***************************************************     * gets a new location relative to the provided location     * and direction     *      @param loc starting location     @param d direction critter is going in     **************************************************/    private Location getRelativeLocation(Location loc, GVcritter.Direction d){        Location rel = new Location(loc.getRow(),loc.getCol());        if (d == GVcritter.Direction.NORTH){            if (rel.getRow() == 0){                rel.setRow(ROWS - 1);            }            else{                rel.setRow(rel.getRow() - 1);            }        }        if (d == GVcritter.Direction.SOUTH){            if (rel.getRow() == ROWS - 1){                rel.setRow(0);            }            else{                rel.setRow(rel.getRow() + 1);            }        }        if (d == GVcritter.Direction.WEST){            if (rel.getCol() == 0){                rel.setCol(COLUMNS - 1);            }            else{                rel.setCol(rel.getCol() - 1);            }        }        if (d == GVcritter.Direction.EAST){            if (rel.getCol() == COLUMNS - 1){                rel.setCol(0);            }            else{                rel.setCol(rel.getCol() + 1);            }        }        return rel;    }        /***************************************************     * removes a critter from the world     *      @param c critter that is dying     **************************************************/    public void critterDies(GVcritter c){        if(c.getSpecies() == GVcritter.Species.ANT){            numAnts--;        }        if(c.getSpecies() == GVcritter.Species.BIRD){            numBirds--;        }        if(c.getSpecies() == GVcritter.Species.HIPPO){            numHippos--;        }        if(c.getSpecies() == GVcritter.Species.VULTURE){            numVultures--;        }        if(c.getSpecies() == GVcritter.Species.LION){            numLions--;        }                allCritters.remove(c);    }    /***************************************************     * determines who wins when two critter meet up     *      @param attacker one critter who is fighting     @param defender the other critter who is fighting     **************************************************/    public void fight(GVcritter attacker, GVcritter defender){        boolean attackerWins;        //Get attack strategy for both critters        GVcritter.Attack a = attacker.getAttack(defender);        GVcritter.Attack d = defender.getAttack(attacker);        //Regardless who wins, set 2d World location of attacker to null        Location loc1 = attacker.getLocation();        Location loc2 = defender.getLocation();                //Check if defender wins due to attacker forfeit        if (a == GVcritter.Attack.FORFEIT){            attackerWins = false;        }                //Check if attacker wins due to defender forfeit        if (d == GVcritter.Attack.FORFEIT){            attackerWins = true;        }                //Select random winner if critters use same attack strategy        if (a == d){            if(Math.random() < 0.5){                attackerWins = true;            }            else{                attackerWins = false;            }        }                //Check if attacker wins        if (a == GVcritter.Attack.SCRATCH && d == GVcritter.Attack.POUNCE){            attackerWins = true;        }        else if (a == GVcritter.Attack.ROAR && d == GVcritter.Attack.SCRATCH){            attackerWins = true;        }        else if (a == GVcritter.Attack.POUNCE && d == GVcritter.Attack.ROAR){            attackerWins = true;        }        //Only remaining option is that defender wins        else{            attackerWins = false;        }                //Winner takes over spot of defender        if (attackerWins == true){            theWorld[loc2.getRow()][loc2.getCol()] = null;            theWorld[loc1.getRow()][loc1.getCol()] = attacker;            critterDies(defender);        }        else{            theWorld[loc1.getRow()][loc1.getCol()] = null;            theWorld[loc2.getRow()][loc2.getCol()] = defender;            critterDies(attacker);        }            }    /***************************************************     * resets step count and removes all critters     **************************************************/    public void reset(){        theWorld = new GVcritter[ROWS][COLUMNS];        allCritters = new ArrayList<GVcritter>();           numAnts = 0;           numBirds = 0;        numHippos = 0;        numVultures = 0;        numLions = 0;        stepCount = 0;    }        /******************************************************    Move forward one step of the simulation     *****************************************************/      public void oneStep(){        // shuffle the arraylist of critters for better performance        Collections.shuffle(allCritters);        stepCount++;        // step throgh all critters using traditional for loop        for(int i=0; i<allCritters.size(); i++){            GVcritter attacker = allCritters.get(i);            // what location does critter want to move to?            GVcritter.Direction dir = attacker.getMoveDirection();            Location previousLoc = attacker.getLocation();            Location nextLoc = getRelativeLocation(previousLoc, dir);              // who is at the next location?            GVcritter defender = theWorld[nextLoc.getRow()][nextLoc.getCol()];            // no critters here so OK for critter 1 to move            if(defender == null){                theWorld[nextLoc.getRow()][nextLoc.getCol()] = attacker;                attacker.setLocation(nextLoc);                theWorld[previousLoc.getRow()][previousLoc.getCol()] = null;                // both critters the same species so peacefully bypass             }else if(attacker.getSpecies() == defender.getSpecies()){                // update critter locations                attacker.setLocation(nextLoc);                defender.setLocation(previousLoc);                // update positions in the world                theWorld[nextLoc.getRow()][nextLoc.getCol()] = attacker;                theWorld[previousLoc.getRow()][previousLoc.getCol()] = defender;                //different species so they fight at location of critter 2            }else if(attacker.getSpecies() != defender.getSpecies()){                fight(attacker, defender);            }        }        // update drawing of the world        repaint();    }    /******************************************************    Step through the 2D world and paint each location white    (for no critter) or the critter's color.  The SIZE of     each location is constant.    @param g graphics element used for display     *****************************************************/          public void paintComponent(Graphics g){        for(int row=0; row<ROWS; row++){            for(int col=0; col<COLUMNS; col++){                GVcritter c = theWorld[row][col];                // set color to white or critter color                if(c == null){                    g.setColor(Color.WHITE);                }else{                        g.setColor(c.getColor());                }                // paint the location                g.fillRect(col*SIZE, row*SIZE, SIZE, SIZE);            }        }    }}  