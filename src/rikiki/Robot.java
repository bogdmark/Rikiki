
package rikiki;

/**
 *
 * @author Márk
 */
public class Robot extends Player{
    
    private int type; //-1 minimalista, 0 realista, 1 optimista, 2 abszolút nulla
    
    public Robot(String name, int type){
        this.name = name;
        this.type = type;
    }
    public Card pick(){
       
       Card c = cards.remove(cards.size()-1);
       return c;
    }
    
    @Override
    public void setCurrentRankForCardsInHand(){
        int cardsinplay;
        int spades, diamonds, hearts, clubs;
        spades = diamonds = hearts = clubs = 0;
        cardsinplay = cards.size() * this.nrofplayers;
        for (Card card : cards) { // Beállítjuk melyik típusból mennyi van a kezünkben
            for(Card card_type : cards){
                if("spades".equals(card_type)) spades++;
                if("diamonds".equals(card_type)) diamonds++;
                if("hearts".equals(card_type)) hearts++;
                if("clubs".equals(card_type)) clubs++;
            }
        /*Ha a kapott lapok között szerepel Ász, vagy adu Ász 
        vagy adu Király akkor növeljük a CurrentRank értékét*/
            if (card.getRoundRank() == 12)
                card.setCurrentRank(0.9);
            if (card.getRoundRank() == 32 || card.getRoundRank() == 31)
                card.setCurrentRank(1.0);                
            if(card.getRoundRank() >= 20) card.setCurrentRank(0.5);
            
            for(int i = 0; i < cards.size(); i++){
        /* Az alapján, hogy melyik típusból mennyi van a kezünkben,
           növeljük a CurrentRank értékét arányosan. Ha mi kezdünk, akkor nagyobb mértékkel.*/
                if(round_starter){
                    if(spades == i){
                        for(Card card_type : cards)
                            if(card_type.getType().equals("spades")) card_type.setCurrentRank(i*0.1);
                    }
                    if(diamonds == i){
                        for(Card card_type : cards)
                            if(card_type.getType().equals("diamonds")) card_type.setCurrentRank(i*0.1);
                    }
                    if(hearts == i){
                        for(Card card_type : cards)
                            if(card_type.getType().equals("hearts")) card_type.setCurrentRank(i*0.1);
                    }
                    if(clubs == i){
                        for(Card card_type : cards)
                            if(card_type.getType().equals("clubs")) card_type.setCurrentRank(i*0.1);
                    }
                }               
                else {
                    if(spades == i && i > 2){
                        for(Card card_type : cards)
                            if(card_type.getType().equals("spades")) card_type.setCurrentRank(i*0.08);
                    }
                    if(diamonds == i && i > 2){
                        for(Card card_type : cards)
                            if(card_type.getType().equals("diamonds")) card_type.setCurrentRank(i*0.08);
                    }
                    if(hearts == i && i > 2){
                        for(Card card_type : cards)
                            if(card_type.getType().equals("hearts")) card_type.setCurrentRank(i*0.08);
                    }
                    if(clubs == i && i > 2){
                        for(Card card_type : cards)
                            if(card_type.getType().equals("clubs")) card_type.setCurrentRank(i*0.08);
                    }
                }
            }
        }
    }
    
    @Override
    public void setEstimate(){
        this.estimate = 0;
        if(type == -1){ //minimalista becslő beállítása
            for (Card card : cards) { 
                /*Ha a kapott lapok között szerepel Ász, vagy adu Ász 
                    vagy adu Király akkor 1-gyel növeljük a becslést*/
                if ("A".equals(card.getValue())){
                    card.toWin = true;
                    this.estimate++;
                }
                if(card.getRoundRank() == 32 || card.getRoundRank() == 31){
                    card.toWin = true;
                    this.estimate++;
                }
                // Ha nálunk van az adu Ász és az adu Király is, növeljük a becslést
                if (card.getRoundRank() == 32) 
                    for (int i = 0; i < cards.size(); i++)
                        if (card.getRoundRank() == 31){
                            this.estimate++;
                            card.toWin = true;
                        }
                //Ha nálunk van az adu Ász és az adu Király és az adu Dáma is, növeljük a becslést
                if (card.getRoundRank() == 32) 
                    for (int i = 0; i < cards.size(); i++)
                        if (card.getRoundRank() == 31)
                           for (int j = 0; j < cards.size(); j++)
                               if (card.getRoundRank() == 30){
                                   this.estimate++;
                                   card.toWin = true;
                               }
                                   
            }
        }
        
        if(type == 0){
            for (Card card : cards) { 
                /*Ha a kapott lapok között szerepel Ász, Király, vagy adu Ász 
                    vagy adu Király akkor 1-gyel növeljük a becslést*/
                if ("A".equals(card.getValue())){
                    this.estimate++;
                    card.toWin = true;
                }
                if ("K".equals(card.getValue())){
                    this.estimate++;
                    card.toWin = true;
                }
                if(card.getRoundRank() == 32 || card.getRoundRank() == 31 || card.getRoundRank() == 30 || card.getRoundRank() == 29){
                    this.estimate++;
                    card.toWin = true;
                }
                // Ha nálunk van az adu Ász és az adu Király is, növeljük a becslést
                if (card.getRoundRank() == 32) 
                    for (int i = 0; i < cards.size(); i++)
                        if (card.getRoundRank() == 31){
                            this.estimate++;
                            card.toWin = true;
                        }
                            
                //Ha nálunk van az adu Ász és az adu Király és az adu Dáma is, növeljük a becslést
                if (card.getRoundRank() == 32) 
                    for (int i = 0; i < cards.size(); i++)
                        if (card.getRoundRank() == 31)
                           for (int j = 0; j < cards.size(); j++)
                               if (card.getRoundRank() == 30){
                                   this.estimate++;
                                   card.toWin = true;
                               }
                                   
            }
            if(this.getRoundStarter()) this.estimate++;
            //túl extrém becslés csökkentése
            if(this.estimate > 5) this.estimate = 5;
        }
        if(type == 1){
            for (Card card : cards) { 
                /*Ha a kapott lapok között szerepel Ász, Király, Dáma, vagy adu Ász 
                    vagy adu Király akkor 1-gyel növeljük a becslést*/
                if ("A".equals(card.getValue())){
                    this.estimate++;
                    card.toWin = true;
                }
                if ("K".equals(card.getValue())){
                    this.estimate++;
                    card.toWin = true;
                }
                if ("Q".equals(card.getValue())){
                    this.estimate++;
                    card.toWin = true;
                }
                if(card.getRoundRank() == 32 || card.getRoundRank() == 31 || card.getRoundRank() == 30 || card.getRoundRank() == 29){
                    this.estimate++;
                    card.toWin = true;
                }
                // Ha nálunk van az adu Ász és az adu Király is, növeljük a becslést
                if (card.getRoundRank() == 32) 
                    for (int i = 0; i < cards.size(); i++)
                        if (card.getRoundRank() == 31){
                            this.estimate++;
                            card.toWin = true;
                        }
                //Ha nálunk van az adu Ász és az adu Király és az adu Dáma is, növeljük a becslést
                if (card.getRoundRank() == 32) 
                    for (int i = 0; i < cards.size(); i++)
                        if (card.getRoundRank() == 31)
                           for (int j = 0; j < cards.size(); j++)
                               if (card.getRoundRank() == 30){
                                   this.estimate++;
                                   card.toWin = true;
                               }
                                   
            }
            if(this.getRoundStarter()) this.estimate++;
        }
        if(type == 2){
            this.estimate = 0;
        }
    }
    

    
    
}
