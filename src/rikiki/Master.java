
package rikiki;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Márk
 * Az osztály a játékmestert / az asztalt reprezentálja. 
 * Felelős a kártyapakli kezeléséért, keveréséért, az osztásért, valamint a játékmenet felügyeletéért.
 */
public class Master {
    
    private ArrayList<Card> deck;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Card> cardsOnTable; //már játékon kívüli lapok
    private ArrayList<Card> cardsInPlay; //épp játékban lévő lapok
    private int round; //ki kezd -> round % players.size
    private int maxRoundNumber; // -> deck.size/players.size
    private String trump;
    
    public void setDeck(ArrayList<Card> deck){
        this.deck = deck;
    }
    
    public ArrayList<Card> getDeck(){
        return this.deck;
    }
    
    public void setRound(int round){
        this.round = round;
    }
    
    public Card getCard(int i){
        return this.deck.get(i);
    }
    
    public String getTrump(){
        return this.trump;
    }
    
    public void setPlayers(Player player){
        this.players.add(player);
    }
    
    public Player getPlayer(int i){
        return this.players.get(i);
    }
    
    public ArrayList<Player> getPlayers(){
        return this.players;
    }
    /*
    Feltölti a kártyák értékeit
    */
    public void initDeck(){
        
        String[] types = {"spades", "hearts", "clubs", "diamonds"};
        String[] values = {"J", "Q", "K", "A"};
        this.deck = new ArrayList<Card>();
        int rank;
        
        for(int i = 0; i<types.length; i++){
            rank = 0;
            for(Integer j=2; j<11; j++){
                Card card = new Card(types[i], j.toString(), rank);
                rank++;
                this.deck.add(card);
            }
            for(int k=0; k<4; k++){
                Card card = new Card(types[i], values[k], rank);
                rank++;
                this.deck.add(card);
            }
        }
    }
    
    /*
    Megkeveri a kártyapaklit + kiválasztja az adut!
    */
    public void shuffleDeck(){
     
        Random rnd = new Random();
        
        for(int i=0; i<100; i++){
            int card0 = rnd.nextInt(deck.size());
            int card1 = rnd.nextInt(deck.size());
            if(card0 != card1){
               Card TempCard = deck.get(card0);
               deck.set(card0, deck.get(card1));
               deck.set(card1, TempCard);
            }
        }
        
        String[] types = {"spades", "hearts", "clubs", "diamonds"};
        this.trump = types[rnd.nextInt(4)];    
    }
    
    public void dealCards(){
        
        int nextCard = 0;
        for(int i = 1; i<=this.round; i++){
            for(int j=0; j<this.players.size(); j++){
                this.players.get(j).setCard(this.deck.get(nextCard));
                nextCard++;
            }
        }
    }
}
