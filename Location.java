
/**
 * Write a description of class Location here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Location
{
    int row;
    int col;
    
    public Location(int r, int c){
        row = r;
        col = c;
    }
    
    public void setRow(int r){
        row = r;
    }
    
    public void setCol(int c){
        col = c;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
}
