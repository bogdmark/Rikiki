
package rikiki;

/**
 *
 * @author Márk
 */
public class Robot extends Player{
    
    public Robot(String name){
        this.name = name;
    }
    public Card pick(){
       return new Card("A", "1", 10);
    }
    

    
    public void setEstimate(){
        this.estimate = 1;
    }
}
