
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
    }
    
    @Override
    public void setEstimate(){
        this.estimate = 0;
        if(type == -1){ //minimalista becslő beállítása
            for (Card card : cards) {
                if(cards.size() < 9){ 
                /*Ha a kapott lapok között szerepel Ász, vagy adu Ász 
                    vagy adu Király akkor 1-gyel növeljük a becslést*/
                if (card.getRoundRank() == 0 && card.getAllTimeRank() == 12){
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
                if(cards.size() > 8 && round_starter){
                    if (card.getRoundRank() == 0 && (card.getAllTimeRank() >= 10)){
                    card.toWin = true;
                    this.estimate++;
                    }
                    if (card.getRoundRank() >= 28){
                    card.toWin = true;
                    this.estimate++;
                    }
                }
                else if(cards.size() > 8 && !round_starter){
                    if (card.getRoundRank() == 0 && card.getAllTimeRank() >= 11){
                        card.toWin = true;
                        this.estimate++;
                    }
                    if (card.getRoundRank() >= 29){
                        card.toWin = true;
                        this.estimate++;
                    }
                }              
            }
        }
        
        if(type == 0){
            for (Card card : cards) { 
                /*Ha a kapott lapok között szerepel Ász, Király, vagy adu Ász 
                    vagy adu Király akkor 1-gyel növeljük a becslést*/
                if(cards.size() < 7){
                if (card.getRoundRank() == 0 && card.getAllTimeRank() >= 11){
                    this.estimate++;
                    card.toWin = true;
                }
                if(card.getRoundRank() >= 29){
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
                if(cards.size() > 6){
                    if (card.getRoundRank() == 0 && card.getAllTimeRank() >= 10){
                    card.toWin = true;
                    this.estimate++;
                    }
                    if (card.getRoundRank() >=28){
                    card.toWin = true;
                    this.estimate++;
                    }
                }
                                   
            }
            //túl extrém becslés csökkentése
            if(this.estimate > cards.size()/2) this.estimate = cards.size()/2;
        }
        if(type == 1){
            for (Card card : cards) {
                if(cards.size() < 6){
                /*Ha a kapott lapok között szerepel Ász, Király, Dáma, vagy adu Ász 
                    vagy adu Király akkor 1-gyel növeljük a becslést*/
                if (card.getRoundRank() == 0 && card.getAllTimeRank() >= 10){
                    this.estimate++;
                    card.toWin = true;
                }
                if(card.getRoundRank() >= 29){
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
                if(cards.size() > 5){
                    if (card.getRoundRank() == 0 && card.getAllTimeRank() >= 10){
                    card.toWin = true;
                    this.estimate++;
                    }
                    if (card.getRoundRank() >=28){
                    card.toWin = true;
                    this.estimate++;
                    }
                    if (card.getRoundRank() == 32) 
                    for (int i = 0; i < cards.size(); i++)
                        if (card.getRoundRank() == 31)
                           for (int j = 0; j < cards.size(); j++)
                               if (card.getRoundRank() == 30)
                                   for (int k = 0; k < cards.size(); k++)
                                       if(card.getRoundRank() == 29){
                                           this.estimate++;
                                           card.toWin = true;
                                        }
                }
            }
        }
        
        if(type == 2){
            this.estimate = 0;
        }
    }
}
    

    
    

