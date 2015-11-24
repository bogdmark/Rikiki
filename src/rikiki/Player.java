
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
    public Integer score;
    public Integer hits;
    public int index;
    
    public Player(String name){
        this.cards = new ArrayList<>();
        this.score = 0;
        this.estimate = 0;
        this.hits = 0;
        this.name = name;
        this.index = 0;
    }
    
    public Player(){
        this.cards = new ArrayList<>();
        this.score = 0;
        this.estimate = 0;
        this.hits = 0;
        this.name = "player";
    }
    
    public String getName(){
        return this.name;
    }
    
     public Card pick(){
       Card c = cards.remove(cards.size()-1);
       return c;
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
    
    public Integer getScore(){
        return this.score;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public Integer getHits(){
        return this.hits;
    }
    
    public void setHtis(int hits){
        this.hits = hits;
    }
    
    
}
