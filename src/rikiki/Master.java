
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
    public ArrayList<Card> cardsOnTable = new ArrayList<Card>(); //már játékon kívüli lapok
    private ArrayList<Card> cardsInPlay = new ArrayList<Card>(); //épp játékban lévő lapok
    public int round_index;
    public int round_number;
    private String trump;
    
    public Master(){
        round_index = 2;
        
    }
    
    public void setDeck(ArrayList<Card> deck){
        this.deck = deck;
    }
    
    public ArrayList<Card> getDeck(){
        return this.deck;
    }
    
    public void setRoundNumber(int round){
        this.round_number = round;
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
        this.trump = types[rnd.nextInt(4)]; //adu beállítása
        for (int i = 0; i < deck.size(); i++){ //adu alapján a RoundRank beállítása a becsléshez
            if(this.getTrump() == (deck.get(i).getType()))
                deck.get(i).setRoundRank(10);
        }
    }
    
    public void dealCards(){
        
        int nextCard = 0;
        for(int i = 1; i<=this.round_index; i++){
            for(int j=0; j<this.players.size(); j++){
                this.players.get(j).setCard(this.deck.get(nextCard));
                nextCard++;
            }
        }
    }
    public void setEstimates(){
        //for ciklussal begyűjteni a játkosoktól
        for(int i = 0; i < this.players.size(); i++){
            players.get(i).setEstimate();
        }
    }
    
    public boolean checkCard(Card c){
        return true;
    }
    
    public void round(int i){
        //for ciklussal végigmenni a játékosokon, figyelni kell, hogy ki a kezdő játékos + a sorrendre
            // meghívja a játékosok pick metódusát
            // ellenőrző metódus, hogy a játékosok jót dobtak-e
             for (Player player : this.players){
                 this.cardsInPlay.add(player.pick());
                 try{
                 Thread.sleep(500);
                 }catch(Exception e){
                     System.out.println("Hiba1");
                 }
             }
           // boolean check = this.checkCard(c);
            
    }
    
    public void getWinner(){
        //az asztalon lévő kártyák közül kiválasztja a nyertest, és beazonosítja a hozzá tartozó játékost
        //majd növeli annak a nyeréseinek számát -> játékosok hits változója
    }
    
    public void sum(){
        // round-ok végén összeveti a becsléseket és a nyeréseket, és ezalapján kiszámolja a kapott pontszámot
    }
    
    public void getFinalWinner(){
        
    }
    
   
}
