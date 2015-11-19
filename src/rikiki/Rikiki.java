
package rikiki;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

/**
 * Példányosítások + GUI
 * @author Márk
 */
public class Rikiki{

    /**
     * @param args the command line arguments
     */
    PlayerOne player1 = new PlayerOne();
    ArrayList<DrawPlayer> drawplayers = new ArrayList<DrawPlayer>();
    Master master = new Master();
    RikikiJFrame frame = new RikikiJFrame();
    
    public Rikiki(){
        frame.setVisible(true);
    }
        
    public void init(){
        
        master.setPlayers(player1);
        for(Integer i = 0; i < Integer.parseInt(this.frame.choice); i++){
            this.master.setPlayers(new Robot("Robot " + i));
        }  
        master.setRoundNumber(52/this.master.getPlayers().size());
        master.initDeck();
    }
    
    public void drawBase(){
        
        frame.PlayerPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        frame.ScorePanel.setLayout(new BoxLayout(frame.ScorePanel, BoxLayout.PAGE_AXIS));
        frame.TablePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        DrawHeader header = new DrawHeader();
        frame.ScorePanel.add(header);
        DrawPlayer playerone = new DrawPlayer();
        this.drawplayers.add(playerone);
        playerone.PlyarPic.setIcon(new ImageIcon("PlayerOne" + ".jpg"));
        playerone.PlayerLabel.setText(master.getPlayer(0).getName() + ":");
        playerone.EstimateLabel.setText(this.master.getPlayer(0).getEstimate().toString());
            
        frame.ScorePanel.add(playerone);
        
        for(int p = 1; p < master.getPlayers().size(); p++){
            DrawPlayer player = new DrawPlayer();
            this.drawplayers.add(player);
            player.PlyarPic.setIcon(new ImageIcon("Robot" + ".jpg"));
            player.PlayerLabel.setText(master.getPlayer(p).getName() + ":");
            playerone.EstimateLabel.setText(this.master.getPlayer(p).getEstimate().toString());
            frame.ScorePanel.add(player);
        }
        
    }
    
    public void draw(){
        
        frame.TrumpPic.setIcon(new ImageIcon(master.getTrump() + "_trump.jpg"));
                
        for(int c = 0; c < master.getPlayer(0).getCards().size(); c++){
            DrawCard card = new DrawCard();
            card.TypeLabel.setIcon(new ImageIcon(master.getPlayer(0).getCard(c).getType() + ".jpg"));
            card.ValueLabel.setText(master.getPlayer(0).getCard(c).getValue());
            MouseListener mouselistener = new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getClickCount()==2){
                        frame.TablePanel.add((DrawCard)e.getSource());
                        frame.revalidate();
                        frame.repaint();
                    }
                }
                @Override
                public void mousePressed(MouseEvent e) {
                  
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                   
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                   
                }

                @Override
                public void mouseExited(MouseEvent e) {
                  
                }
            };
            card.addMouseListener(mouselistener);
            frame.PlayerPanel.add(card);
        }
        frame.revalidate();
       
    }
    
    public void game(){
        //for ciklus -> k=1 - körök számáig
        for(int k = 1; k <= this.master.round_number; k++)
            this.master.shuffleDeck();
            //round változóba belerakja, hogy hány kör lesz, ez alapján a kártyák kiosztás
            this.master.dealCards();
            //kirajzol
            this.draw();
            //becslések begyűjtése
            this.master.setEstimates();
            
           for(int i = 0; i < this.master.getPlayers().size(); i++){
               this.drawplayers.get(i).EstimateLabel.setText(this.master.getPlayer(i).getEstimate().toString());
            }
            //for ciklussal végigmenni a meneteken
            for(int i = 1; i<=this.master.round_index; i++){
                this.master.round(i);
                this.master.getWinner();    
            }
            this.master.sum();
            
        // for ciklus után -> nyertes kihirdetése
        this.master.getFinalWinner();
    }
    
    
    
    public static void main(String[] args) {
        
        Rikiki rikiki = new Rikiki();
        int cntr = 0;
        while(!Thread.interrupted() && !rikiki.frame.exit){
            if(rikiki.frame.start && cntr < 1){
                rikiki.init();
                rikiki.drawBase();
                rikiki.game();
                cntr++;
            }
      
        }
        
    }   

}
