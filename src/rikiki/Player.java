
package rikiki;

import java.util.ArrayList;

/**
 * Közös ősosztály a játékosnak és a robotoknak
 * @author Márk
 */
public class Player {
    
    private String name;
    private ArrayList<Card> cards;
    private int estimate;
    private int score;
    private int hits;
    
    public Player(){
        this.cards = new ArrayList<>();
        this.score = 0;
        this.name = "player";
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public Card getCard(int i){
        return this.cards.get(i);
    }
    
    public void setCard(Card card){
        this.cards.add(card);
    }
    
    public ArrayList<Card> getCards(){
        return this.cards;
    }
    
    public int getEstimate(){
        return this.estimate;
    }
    
    public void setEstimate(int est){
        this.estimate = est;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public int getHits(){
        return this.hits;
    }
    
    public void setHtis(int hits){
        this.hits = hits;
    }
    
}
