
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
    
    public ArrayList<Card> deck;
    public ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Card> cardsOnTable = new ArrayList<Card>(); //már játékon kívüli lapok
    public ArrayList<Card> cardsInPlay = new ArrayList<Card>(); //épp játékban lévő lapok
    public int round_index;
    public int round_number;
    public String trump;
    public int backward_index;
    
    public Master(){
        round_index = 2;
        backward_index = 0;
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
        
        this.initDeck();
     
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
        this.trump = types[rnd.nextInt(4)]; //adu beállítása
        
        for (int i = 0; i < deck.size(); i++){ //adu alapján a RoundRank beállítása a becsléshez
            if(this.trump.equals(deck.get(i).getType()))
                deck.get(i).setRoundRank(20);
        }
    }
    
    public void dealCards(){
        
        int nextCard = 0;

        for(int i = 1; i<=this.round_index-this.backward_index; i++){
            for(int j=0; j<this.players.size(); j++){
                this.deck.get(nextCard).ownerID = j;
                this.players.get(j).setCard(this.deck.get(nextCard));
                nextCard++;
            }
        }
    }
    public void setEstimates(RikikiJFrame frame, int player_size){
        //for ciklussal begyűjteni a játkosoktól
        for(int i = 0; i < this.players.size(); i++){
            players.get(i).setEstimate(frame, player_size);
        }
    }
    
    public int getWinner(){
        //az asztalon lévő kártyák közül kiválasztja a nyertest, és beazonosítja a hozzá tartozó játékost
        //majd növeli annak a nyeréseinek számát -> játékosok hits változója
        Card temp = this.cardsInPlay.get(0);
        int winner_index = 0;
        for(int i = 1; i < this.cardsInPlay.size(); i++){
            if((temp.getType().equals(this.cardsInPlay.get(i).getType()) && this.cardsInPlay.get(i).getAllTimeRank() > temp.getAllTimeRank()) ||
              (!temp.getType().equals(this.trump) && this.cardsInPlay.get(i).getType().equals(this.trump))){
                temp = this.cardsInPlay.get(i);
                winner_index = i;
            }
        }
        this.players.get(this.cardsInPlay.get(winner_index).ownerID).hits++;
        return this.cardsInPlay.get(winner_index).ownerID;
    }
    
    public int getWinnerCard(){
        //az asztalon lévő kártyák közül kiválasztja a nyertest, és beazonosítja a hozzá tartozó játékost
        //majd növeli annak a nyeréseinek számát -> játékosok hits változója
        Card temp = this.cardsInPlay.get(0);
        int winner_index = 0;
        for(int i = 1; i < this.cardsInPlay.size(); i++){
            if((temp.getType().equals(this.cardsInPlay.get(i).getType()) && this.cardsInPlay.get(i).getAllTimeRank() > temp.getAllTimeRank()) ||
              (!temp.getType().equals(this.trump) && this.cardsInPlay.get(i).getType().equals(this.trump))){
                temp = this.cardsInPlay.get(i);
                winner_index = i;
            }
        }
        return winner_index;
    }
    
    public void sum(){
        // round-ok végén összeveti a becsléseket és a nyeréseket, és ezalapján kiszámolja a kapott pontszámot
        for(Player player: this.players){
            if(player.hits.equals(player.estimate)){
                player.score += 10+2*(player.estimate);
            }
            else{
                player.score -= 2*Math.abs(player.hits-player.estimate);
            }
            player.hits = 0;
        }
    }
    
    
    public String getFinalWinner(){
        
        Player temp;
        
        //bubble sort
        for(int i=0; i < players.size()-1; i++){
            for(int j=0; j < players.size()-i-1; j++){
                if(players.get(j).score < players.get(j+1).score){
                    temp = players.get(j);
                    players.set(j, players.get(j+1));
                    players.set(j+1, temp);
                }
            }
        }
        
        String s = "<html>";
        int i = 1;
        
        for(Player player: this.players){
             s = s.concat(i + "." + player.name + " (" + player.score + ")<br>");
             i++;
         }
        
         s = s.concat("</html>");
         return s;
    }
    
}
