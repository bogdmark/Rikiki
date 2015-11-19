
package rikiki;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Közös ősosztály a játékosnak és a robotoknak
 * @author Márk
 */
public class Player {
    
    public String name;
    public ArrayList<Card> cards;
    public Integer estimate;
    public int score;
    public int hits;
    
    public Player(String name){
        this.cards = new ArrayList<>();
        this.score = 0;
        this.name = name;
    }
    
    public Player(){
        this.cards = new ArrayList<>();
        this.score = 0;
        this.estimate = 0;
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
    
    public Integer getEstimate(){
      
       return this.estimate;
    }
    
    public void setEstimate(){
        this.estimate = 0;
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
