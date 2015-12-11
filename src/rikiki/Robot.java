
package rikiki;


/**
 *
 * @author Márk
 */
public class Robot extends Player{
    
    private int type; //-1 minimalista, 0 realista, 1 optimista
    
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
    
    public void correctEstimate(){
        if(this.estimate > (cards.size() / 2)){
            Card temp = cards.get(0);
            for(Card card : cards){
                if(card.toWin){
                    temp = card;
                }
            }
            for(Card card : cards){
                if(card.toWin && card.getRoundRank() < temp.getRoundRank()){
                    temp = card;
                }
            }
            temp.toWin = false;
            this.estimate--;
            this.correctEstimate();
        }
        
    }
   
    @Override
    public void setEstimate(RikikiJFrame frame, int player_size){
        
        this.estimate = 0;
        if(type == -1){ //minimalista becslő beállítása
            for (Card card : cards) {
                if(cards.size()*player_size > 18){ 
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
                            if (cards.get(i).getRoundRank() == 31)
                               for (int j = 0; j < cards.size(); j++)
                                   if (cards.get(j).getRoundRank() == 30){
                                       this.estimate++;
                                       cards.get(j).toWin = true;
                                   }

                    if (card.getRoundRank() == 32) 
                        for (int i = 0; i < cards.size(); i++)
                            if (cards.get(i).getRoundRank() == 31)
                               for (int j = 0; j < cards.size(); j++)
                                   if (cards.get(j).getRoundRank() == 30) 
                                       for (int k = 0; k < cards.size(); k++)
                                           if (cards.get(k).getRoundRank() == 29){
                                               this.estimate++;
                                               cards.get(k).toWin = true;
                                               }

                    if (card.getRoundRank() == 32) 
                        for (int i = 0; i < cards.size(); i++)
                            if (cards.get(i).getRoundRank() == 31)
                               for (int j = 0; j < cards.size(); j++)
                                   if (cards.get(j).getRoundRank() == 30) 
                                       for (int k = 0; k < cards.size(); k++)
                                           if (cards.get(k).getRoundRank() == 29)
                                               for (int l = 0; l < cards.size(); l++)
                                                   if (cards.get(l).getRoundRank() == 28){
                                                       this.estimate++;
                                                       cards.get(l).toWin = true;
                                                       }
                }
                else {
                    if ((card.getRoundRank() >= 10 && card.getRoundRank() < 20 || card.getRoundRank() >= 30) && !card.toWin){
                    card.toWin = true;
                    this.estimate++;
                }
                if(cards.size() < 5 && round_starter && !card.toWin){
                    if (card.getRoundRank() >= 10 && card.getRoundRank() < 20){
                    card.toWin = true;
                    this.estimate++;
                    }
                    if (card.getRoundRank() >= 28){
                    card.toWin = true;
                    this.estimate++;
                    }
                }
                else if(cards.size() < 7 && !round_starter && !card.toWin){
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
            
                
            }
            
            if(this.estimate == 0 && cards.size() > 5)
                if((getMaxValue(cards) >= 10 && getMaxValue(cards) < 20) || getMaxValue(cards) > 29){
                    getMaxCard(cards).toWin = true;
                    this.estimate++;
                }

        }
        
        if(type == 0){
            for (Card card : cards) { 
                if(cards.size() > 6){
                    if (card.getRoundRank() >= 11 && card.getRoundRank() < 20){
                        this.estimate++;
                        card.toWin = true;
                        }
                    if(card.getRoundRank() >= 29){
                        this.estimate++;
                        card.toWin = true;
                    }               
                }
                if(cards.size() < 7){
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
            if(player_size * cards.size() < 19){
                if(cards.size() <= 4)
                    if(this.estimate == 0)
                        if(getMaxValue(cards) >= 8){
                            getMaxCard(cards).toWin = true;
                            this.estimate++;
                        }
                if(cards.size() > 4 && cards.size() < 7)
                    if(this.estimate == 0)
                        if(getMaxValue(cards) >= 9){
                            getMaxCard(cards).toWin = true;
                            this.estimate++;
                        }
            }            
        }
        
        if(type == 1){
            for (Card card : cards) {
                if(cards.size() > 5){
                    if(card.getRoundRank() >= 27){
                        this.estimate++;
                        card.toWin = true;
                    }
                    if (card.getRoundRank() >= 9 && card.getRoundRank() < 20){
                        this.estimate++;
                        card.toWin = true;
                    }                    
                }
                if(cards.size() == 4 || cards.size() == 5){
                    if (card.getRoundRank() >= 8 && card.getRoundRank() < 20){
                        this.estimate++;
                        card.toWin = true;
                    }
                    if(card.getRoundRank() >= 27){
                        this.estimate++;
                        card.toWin = true;
                    }
                }
                if(cards.size() < 4){
                    if (card.getRoundRank() >= 8 && card.getRoundRank() < 20){
                    card.toWin = true;
                    this.estimate++;
                    }
                    if (card.getRoundRank() >=25){
                    card.toWin = true;
                    this.estimate++;
                    }
                }                
            }            
        }
        this.correctEstimate();
    }
}
    

    
    

