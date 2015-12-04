
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
    public boolean round_starter;
    
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
    
     public Card pick(ArrayList<Card> cardsInPlay){
       Card c = cards.remove(cards.size()-1);
       return c;
    }
     
    private boolean canIwin(){
        return false;     
    }
    
    public Card getCard(int i){
        return this.cards.get(i);
    }
    
    public void setCard(Card card){
        this.cards.add(card);
    }
   
    public void setEstimate(){
        this.estimate = 0;
    }

    public void setRoundStarter(boolean a){
        this.round_starter = a;
    }
    
    public boolean getRoundStarter(){
        return this.round_starter;
    }
    
}
