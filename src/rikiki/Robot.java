
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
        if(type == 0){
           for (int i = 0; i < cards.size(); i++){
               if (cards.get(i).getValue() == "A" || cards.get(i).getRoundRank() == 13 || cards.get(i).getRoundRank() == 12)
                   this.estimate++;
           }
        }
    }
}
