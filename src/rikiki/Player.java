
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
    
     public Card pick(ArrayList<Card> cardsInPlay, String trump){
         
         ArrayList<Card> playableCards = new ArrayList<Card>();
         
        //Ha ő az első a körben, az összes kártyáját használhatja
         if(cardsInPlay.isEmpty()){
             playableCards = this.cards;
         }
         //Ha nem ő az első a körben, csak a megengedett lapokat használhatja
         else{
             playableCards = this.getPlayableCards(cardsInPlay, trump);
         }
         
        
        Card c = playableCards.get(0);
        cards.remove(c);
        return c;
    }
    
    public ArrayList<Card> getPlayableCards(ArrayList<Card> cardsInPlay, String trump){
        
        ArrayList<Card> playableCards = new ArrayList<Card>();
        
        //Megnézi, hogy van-e a kért szín
        for(Card card: this.cards){
            if(card.getType().equals(cardsInPlay.get(0).getType())){
                playableCards.add(card);
            }
        }
        //Ha nincs szín, megnézi, hogy van-e adu
        if(playableCards.isEmpty()){
            for(Card card: this.cards){
                if(card.getType().equals(trump)){
                    playableCards.add(card);
                }
            }
        }
        //Ha adu sincs
        if(playableCards.isEmpty()){
            playableCards = this.cards;
        }
        return playableCards;
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
}
