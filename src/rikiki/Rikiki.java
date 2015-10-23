
package rikiki;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    Robot player2 = new Robot();
    Robot player3 = new Robot();
    Robot player4 = new Robot();
    Master master = new Master();
    RikikiJFrame frame = new RikikiJFrame();
        
    public void init(){
        
        this.player2.setName("Robot Béla");
        player3.setName("Ultron");
        player4.setName("The Machine");
        frame.setVisible(true);
        master.setPlayers(player1);
        master.setPlayers(player2);
        master.setPlayers(player3);
        master.setPlayers(player4);
        master.setRound(10);
        master.initDeck();
        master.shuffleDeck();
        master.dealCards();
    }
    
    public void draw(){
        System.out.println(master.getTrump());
        frame.TrumpPic.setIcon(new ImageIcon(master.getTrump() + "_trump.jpg"));
        
        
        frame.PlayerPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        frame.ScorePanel.setLayout(new BoxLayout(frame.ScorePanel, BoxLayout.PAGE_AXIS));
        frame.TablePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        DrawPlayer playerone = new DrawPlayer();
            playerone.PlyarPic.setIcon(new ImageIcon("PlayerOne" + ".jpg"));
            playerone.PlayerLabel.setText(master.getPlayer(0).getName() + ":");
            
            frame.ScorePanel.add(playerone);
        
        
        for(int p = 1; p < master.getPlayers().size(); p++){
            DrawPlayer player = new DrawPlayer();
            player.PlyarPic.setIcon(new ImageIcon("Robot" + ".jpg"));
            player.PlayerLabel.setText(master.getPlayer(p).getName() + ":");
            frame.ScorePanel.add(player);
        }
        
        
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
        
        //Csak próba!!!
        for(int c = 0; c < 3; c++){
            DrawCard card = new DrawCard();
            card.TypeLabel.setIcon(new ImageIcon(master.getPlayer(1).getCard(c).getType() + ".jpg"));
            card.ValueLabel.setText(master.getPlayer(1).getCard(c).getValue());
            frame.TablePanel.add(card);
        }
        
        frame.revalidate();
        
        for(int i = 0; i<4; i++){
            System.out.println(i + ". Player:");
            for(int j = 0; j < master.getPlayer(i).getCards().size(); j++){
                System.out.println(master.getPlayer(i).getCard(j).getType() + master.getPlayer(i).getCard(j).getValue() + " " 
                        + master.getPlayer(i).getCard(j).geAllTimeRank());
            }
        }
    }
    
    
    
    public static void main(String[] args) {
        
        Rikiki rikiki = new Rikiki();
        rikiki.init();
        rikiki.draw();
        
    }   

}
