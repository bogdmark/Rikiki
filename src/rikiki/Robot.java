
package rikiki;

import java.util.ArrayList;

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
    public void setEstimate(RikikiJFrame frame){
        this.estimate = 0;
        if(type == -1){ //minimalista becslő beállítása
            for (Card card : cards) {
                if(cards.size() < 9){ 
                /*Ha a kapott lapok között szerepel Ász, vagy adu Ász 
                    vagy adu Király akkor 1-gyel növeljük a becslést*/
                if (card.getRoundRank() == 12){
                    card.toWin = true;
                    this.estimate++;
                }
                if(card.getRoundRank() == 32 || card.getRoundRank() == 31){
                    card.toWin = true;
                    this.estimate++;
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
                
                if (card.getRoundRank() == 32) 
                    for (int i = 0; i < cards.size(); i++)
                        if (card.getRoundRank() == 31)
                           for (int j = 0; j < cards.size(); j++)
                               if (card.getRoundRank() == 30) 
                                   for (int k = 0; k < cards.size(); k++)
                                       if (card.getRoundRank() == 29){
                                           this.estimate++;
                                           card.toWin = true;
                                           }
                
                if (card.getRoundRank() == 32) 
                    for (int i = 0; i < cards.size(); i++)
                        if (card.getRoundRank() == 31)
                           for (int j = 0; j < cards.size(); j++)
                               if (card.getRoundRank() == 30) 
                                   for (int k = 0; k < cards.size(); k++)
                                       if (card.getRoundRank() == 29)
                                           for (int l = 0; l < cards.size(); l++)
                                               if (card.getRoundRank() == 29){
                                                   this.estimate++;
                                                   card.toWin = true;
                                                   }
                }
                if(cards.size() > 8 && round_starter){
                    if (card.getRoundRank() >= 10){
                    card.toWin = true;
                    this.estimate++;
                    }
                    if (card.getRoundRank() >= 28){
                    card.toWin = true;
                    this.estimate++;
                    }
                }
                else if(cards.size() > 8 && !round_starter){
                    if (card.getRoundRank() >= 11 && card.getRoundRank() < 20){
                        card.toWin = true;
                        this.estimate++;
                    }
                    if (card.getRoundRank() >= 29){
                        card.toWin = true;
                        this.estimate++;
                    }
                }              
            }
            if(cards.size() > 5)
                if(this.estimate == 0)
                    if(getMaxValue(cards) >= 11){
                        getMaxCard(cards).toWin = true;
                        this.estimate++;
                    }
            if(cards.size() > 8)
                if(this.estimate == 0)
                    if(getMaxValue(cards) >= 8){
                        getMaxCard(cards).toWin = true;
                        this.estimate++;
                    }
        }
        
        if(type == 0){
            for (Card card : cards) { 
                /*Ha a kapott lapok között szerepel Ász, Király, vagy adu Ász 
                    vagy adu Király akkor 1-gyel növeljük a becslést*/
                if(cards.size() < 7){
                if (card.getRoundRank() >= 11 && card.getRoundRank() < 20){
                    this.estimate++;
                    card.toWin = true;
                    }
                if(card.getRoundRank() >= 29){
                    this.estimate++;
                    card.toWin = true;
                    }               
                }
                if(cards.size() > 6){
                    if (card.getRoundRank() >= 10 && card.getRoundRank() < 20){
                    card.toWin = true;
                    this.estimate++;
                    }
                    if (card.getRoundRank() >=28){
                    card.toWin = true;
                    this.estimate++;
                    }
                }
                                   
            }
            if(cards.size() < 4)
                if(this.estimate == 0)
                    if(getMaxValue(cards) >= 6){
                        getMaxCard(cards).toWin = true;
                        this.estimate++;
                    }
            if(cards.size() > 3 && cards.size() < 7)
                if(this.estimate == 0)
                    if(getMaxValue(cards) >= 7){
                        getMaxCard(cards).toWin = true;
                        this.estimate++;
                    }
            if(cards.size() == 7 || cards.size() == 8)
                if(this.estimate == 0)
                    if(getMaxValue(cards) >= 8){
                        getMaxCard(cards).toWin = true;
                        this.estimate++;
                    }
            if(cards.size() > 8)
                if(this.estimate == 0)
                    if(getMaxValue(cards) >= 9){
                        getMaxCard(cards).toWin = true;
                        this.estimate++;
                    }
            //túl extrém becslés csökkentése
            if(this.estimate > cards.size()/2) this.estimate = cards.size()/2;
        }
        if(type == 1){
            for (Card card : cards) {
                if(cards.size() < 4){
                    if(card.getRoundRank() >= 26){
                        this.estimate++;
                        card.toWin = true;
                    }
                    if (card.getRoundRank() >= 10 && card.getRoundRank() < 20){
                        this.estimate++;
                        card.toWin = true;
                    }                    
                }
                if(cards.size() == 4 || cards.size() == 5){
                /*Ha a kapott lapok között szerepel Ász, Király, Dáma, vagy adu Ász 
                    vagy adu Király akkor 1-gyel növeljük a becslést*/
                    if (card.getRoundRank() >= 10 && card.getRoundRank() < 20){
                        this.estimate++;
                        card.toWin = true;
                    }
                    if(card.getRoundRank() >= 29){
                        this.estimate++;
                        card.toWin = true;
                    }
                }
                if(cards.size() > 5){
                    if (card.getRoundRank() >= 10 && card.getRoundRank() < 20){
                    card.toWin = true;
                    this.estimate++;
                    }
                    if (card.getRoundRank() >=28){
                    card.toWin = true;
                    this.estimate++;
                    }
                }                
            }
            if(cards.size() < 4)
                if(this.estimate == 0)
                    if(getMaxValue(cards) >= 6){
                        getMaxCard(cards).toWin = true;
                        this.estimate++;
                    }
            if(cards.size() == 4 || cards.size() == 5)
                if(this.estimate == 0)
                    if(getMaxValue(cards) >= 8){
                        getMaxCard(cards).toWin = true;
                        this.estimate++;
                    }
            if(cards.size() > 5)
                if(this.estimate == 0)
                    if(getMaxValue(cards) >= 6){
                        getMaxCard(cards).toWin = true;
                        this.estimate++;
                    }
        }
        
        if(type == 2){
            this.estimate = 0;
        }
    }
}
    

    
    

