
package rikiki;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Márk
 */
public class PlayerOne extends Player{
    
    
    public Integer getEstimate(){
      
        return this.estimate;
   
    }
    
    @Override
    public void setEstimate(RikikiJFrame frame, int player_size){
        boolean ok = false;
        while(!ok){
            try{
                this.estimate = Integer.parseInt(JOptionPane.showInputDialog(frame, "What's your estimate?" ));
                if(this.estimate < 0 || this.estimate > this.cards.size()){
                    JOptionPane.showMessageDialog(frame, "Hibás becslés!");
                }
                else{
                    ok = true;
                }
            } catch (Exception e){
                JOptionPane.showMessageDialog(frame, "Nem becsültél!");
            }
        }
    }
}
