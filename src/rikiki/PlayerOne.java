
package rikiki;

import javax.swing.JOptionPane;

/**
 *
 * @author Márk
 */
public class PlayerOne extends Player{
 
    public Card pick(){
       Card c = cards.remove(cards.size()-1);
       return c;
    }
    
    public Integer getEstimate(){
      
        return this.estimate;
   
    }
    
    public void setEstimate(){
             
        this.estimate = Integer.parseInt(JOptionPane.showInputDialog("What's your estimate?" ));
    }
}
