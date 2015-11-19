
package rikiki;

import javax.swing.JOptionPane;

/**
 *
 * @author Márk
 */
public class PlayerOne extends Player{
 
    public Card pick(){
        return new Card("A", "1", 10);
    }
    
    public Integer getEstimate(){
      
        return this.estimate;
   
    }
    
    public void setEstimate(){
             
        this.estimate = Integer.parseInt(JOptionPane.showInputDialog("What's your estimate?" ));
    }
}
