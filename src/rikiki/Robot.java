
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
    
    public void setEstimate(){
        this.estimate = 0;
        if(type == -1){ //minimalista becslő beállítása
            for (Card card : cards) { /*Ha a kapott lapok között szerepel Ász, vagy adu Ász 
                    vagy adu Király akkor 1-gyel növeljük a becslést*/
                if ("A".equals(card.getValue()) || card.getRoundRank() == 32 || card.getRoundRank() == 31) {
                    this.estimate++;
                }
                if (card.getRoundRank() == 32) // Ha nálunk van az adu Ász és az adu Király is, növeljük a becslést
                    for (int i = 0; i < cards.size(); i++)
                        if (card.getRoundRank() == 31)
                            this.estimate++;
                if (card.getRoundRank() == 32) //Ha nálunk van az adu Ász és az adu Király és az adu Dáma is, növeljük a becslést
                    for (int i = 0; i < cards.size(); i++)
                        if (card.getRoundRank() == 31)
                           for (int j = 0; j < cards.size(); j++)
                               if (card.getRoundRank() == 30)
                                   this.estimate++;
            }
        }
        
        if(type == 0);
        if(type == 1);
    }
}
