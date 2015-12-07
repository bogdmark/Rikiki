
package rikiki;

/**
 *
 * @author Márk
 */
public class Card {
    
    private final String type;
    private final String value;
    private final int allTimeRank; //mindenkori érték a pakliban -> hogy pl. a király értéke könnyen összehasonlítható legyen egy számmal.
    private int roundRank; //amikor van már adu
    private double currentRank = 0; // aktuális érték
    public int ownerID;
    public boolean toWin;
    
    public Card(String type, String value, int allTimeRank){
        this.type = type;
        this.value = value;
        this.allTimeRank = allTimeRank;
        this.ownerID = -1;
        this.toWin = false;
        this.roundRank = 0;
    }
    
    public String getType(){
        return this.type;
    }
    
    public String getValue(){
        return this.value;
    }
    
    public int getAllTimeRank(){
        return this.allTimeRank;
    }
    
    public int getRoundRank(){
        return this.roundRank;
    }
    
    public double getCurrentRank(){
        return this.currentRank;
    }
    
    public void setRoundRank(int rank){
        this.roundRank = getAllTimeRank() + rank;
    }
    
    public void setCurrentRank(double rank){
        this.currentRank += rank;
    }
}
