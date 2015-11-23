
package rikiki;

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
    public void setEstimate(){
        boolean ok = false;
        while(!ok){
            try{
                this.estimate = Integer.parseInt(JOptionPane.showInputDialog("What's your estimate?" ));
                if(this.estimate < 0 || this.estimate > this.cards.size()){
                    JOptionPane.showMessageDialog(null, "Hibás becslés!");
                }
                else{
                    ok = true;
                }
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Nem becsültél!");
            }
        }
    }
}
