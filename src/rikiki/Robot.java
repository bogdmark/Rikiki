
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
       return new Card("A", "1", 10);
    }
    
    public void setEstimate(){
        this.estimate = 0;
        if(type == -1){ //minimalista becslő beállítása
            for (Card card : cards) { /*Ha a kapott lapok között szerepel Ász, vagy adu Ász 
                    vagy adu Király akkor 1-gyel növeljük a becslést*/
                if (card.getValue() == "A" || card.getRoundRank() == 22 || card.getRoundRank() == 21) {
                    this.estimate++;
                }
                if (card.getRoundRank() == 22) // Ha nálunk van az adu Ász és az adu Király is, növeljük a becslést
                    for (int i = 0; i < cards.size(); i++)
                        if (card.getRoundRank() == 21)
                            this.estimate++;
                if (card.getRoundRank() == 22) //Ha nálunk van az adu Ász és az adu Király és az adu Dáma is, növeljük a becslést
                    for (int i = 0; i < cards.size(); i++)
                        if (card.getRoundRank() == 21)
                           for (int j = 0; j < cards.size(); j++)
                               if (card.getRoundRank() == 20)
                                   this.estimate++;
            }
        }
        
        if(type == 0);
        if(type == 1);
    }
}
