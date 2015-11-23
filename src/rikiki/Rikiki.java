
package rikiki;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author Márk
 */
public class Rikiki{

    PlayerOne player1 = new PlayerOne();
    ArrayList<DrawPlayer> drawplayers = new ArrayList<DrawPlayer>();
    Master master = new Master();
    RikikiJFrame frame = new RikikiJFrame();
    boolean halftime;
    boolean player1Turn;
    boolean click;
    
    public Rikiki(){
        frame.setVisible(true);
        this.halftime = false;
        this.player1Turn = false;
        this.click = false;
    }
    
    /*
    * Pár alap beállítás, miután a játékos megadta a robotok számát
    */
    public void init(){
        
        // Játkos beállítása
        master.setPlayers(player1);
        
        // Robotok beállítása a felhasználó választása alapján
        for(Integer i = 0; i < Integer.parseInt(this.frame.choice); i++){
            this.master.setPlayers(new Robot("Robot " + i, -1));
        }
        
        // Játékosok száma alapján a menetek számának meghatározása
        master.setRoundNumber((52/this.master.getPlayers().size())*2-2);
        
        // Pakli generálása
        master.initDeck();
    }
    
    /*
    * Pár alap dolog, pl. táblázat kirajzolása
    */
    public void drawBase(){
        
        frame.PlayerPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        frame.ScorePanel.setLayout(new BoxLayout(frame.ScorePanel, BoxLayout.PAGE_AXIS));
        frame.TablePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        frame.TopPanel.setVisible(true);
        
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
    
    /*
    * Körök elejének kirajzolása: Adu + Player1 kártyái az eseménykezelőkkel.
    */
    public void drawRoundBegin(){
        
        frame.TrumpPic.setIcon(new ImageIcon(master.getTrump() + "_trump.jpg"));
        frame.RoundNumber.setText(this.master.round_index-1 + "/" + this.master.round_number);
        frame.PlayerPanel.removeAll();
        for(int c = 0; c < master.getPlayer(0).getCards().size(); c++){
            DrawCard card = new DrawCard();
            card.TypeLabel.setIcon(new ImageIcon(master.getPlayer(0).getCard(c).getType() + ".jpg"));
            card.type = master.getPlayer(0).getCard(c).getType();
            card.ValueLabel.setText(master.getPlayer(0).getCard(c).getValue());
            MouseListener mouselistener = new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getClickCount()==2 && player1Turn == true){
                        player1Turn = false;
                        click = true;
                        DrawCard temp_card = (DrawCard)e.getSource();;
                        
                        for(int c = 0; c < master.players.get(0).cards.size(); c++){  
                            Card card = master.players.get(0).getCard(c);
                            //meg kell találni a kijelölt kártyát
                            if(temp_card.type.equals(card.getType()) && temp_card.ValueLabel.getText().equals(card.getValue())){
                                master.cardsInPlay.add(master.players.get(0).cards.remove(c));
                            }
                            
                        }
                        frame.TablePanel.add(temp_card);
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
    
    public void round(int i){
        // TODO!!! helyes sorrend
        // TODO!!! ellenőrző metódus, hogy a játékosok jót dobtak-e
        frame.TablePanel.removeAll();
        for (Player player : this.master.players){
            
            //lekéri a kártyát, amit dob a játékos
            if(player instanceof PlayerOne){
                this.player1Turn = true; 
                  while(!this.click){ 
                      try{
                        //Kell, mert különben nem működik  
                        Thread.sleep(500);
                      } catch(Exception e){
                          System.out.println("Hiba a szálkezelésben (PlayerOne)");
                      }
                  }               
            }
            else {
                this.click = false;
                Card c = player.pick();
                this.master.cardsInPlay.add(c);
            
                 //kirajzolás
                DrawCard card2 = new DrawCard();
                card2.TypeLabel.setIcon(new ImageIcon(c.getType() + ".jpg"));
                card2.ValueLabel.setText(c.getValue());
                frame.TablePanel.add(card2);
            }
            frame.revalidate();
            frame.repaint();
                 
            try{
                Thread.sleep(1000);
            } catch(Exception e){
                System.out.println("Hiba az időzítéssel!");
            }
        }    
    }
    
    public void game(){
        //for ciklus -> k=1 - körök számáig
        for(int k = 1; k <= this.master.round_number; k++){
            this.master.shuffleDeck();
            System.out.println(this.master.getTrump());
            //round változóba belerakja, hogy hány kör lesz, ez alapján a kártyák kiosztás
            this.master.dealCards();
            //kirajzol
            this.drawRoundBegin();
            for(int i = 0; i < this.master.getPlayers().size(); i++){
               this.drawplayers.get(i).hitsLabel.setText("0");
            }
            //becslések begyűjtése
            this.master.setEstimates();
            
           for(int i = 0; i < this.master.getPlayers().size(); i++){
               this.drawplayers.get(i).EstimateLabel.setText(this.master.getPlayer(i).getEstimate().toString());
            }
            //for ciklussal végigmenni a meneteken
            for(int i = 1; i<=this.master.round_index; i++){
               
                this.round(i);
                int winner = this.master.getWinner();
                this.drawplayers.get(winner).hitsLabel.setText(this.master.getPlayer(winner).getHits().toString());
                
                this.master.cardsOnTable.addAll(this.master.cardsInPlay);
                this.master.cardsInPlay.removeAll(this.master.cardsInPlay); //TODO -> ezt ellenörizni!!!
            }
            
           // if(k >= this.master.round_number/2)
            this.master.sum();
            for(int i = 0; i < this.master.getPlayers().size(); i++){
               this.drawplayers.get(i).ScoreLabel.setText(this.master.getPlayer(i).getScore().toString());
            }
            this.master.round_index++;
            
        }
        // for ciklus után -> nyertes kihirdetése
        this.master.getFinalWinner();
    }
    
    
    
    public static void main(String[] args) {
        
        Rikiki rikiki = new Rikiki();
        int cntr = 0;
        while(!Thread.interrupted() && !rikiki.frame.exit){
            if(rikiki.frame.start && cntr < 1){
                if(rikiki.frame.choice == null){
                    cntr = 0;
                    rikiki.frame.start = false;
                }
                else{
                    rikiki.init();
                    rikiki.drawBase();
                    rikiki.game();
                    cntr++;
                } 
            }
      
        }
        
    }   

}
