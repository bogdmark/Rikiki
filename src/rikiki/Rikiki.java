
package rikiki;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
    int winner_index;
    
    public Rikiki(){
        frame.setVisible(true);
        this.halftime = false;
        this.player1Turn = false;
        this.click = false;
        this.winner_index = 0;
    }
    
    /*
    * Pár alap beállítás, miután a játékos megadta a robotok számát
    */
    public void init(){
        
        // Játkos beállítása
        master.players.add(player1);
        
        // Robotok beállítása a felhasználó választása alapján
        // Robotok beállítása, mind a 3 típust kipróbáljuk
        for(int i = 0; i < Integer.parseInt(this.frame.choice); i++){
            if( i == 0){
                this.master.players.add(new Robot("Robot " + i, 0));
            }
            else if(i == 1){
                this.master.players.add(new Robot("Robot " + i, 1));
            }
            else{
                this.master.players.add(new Robot("Robot " + i, -1));
            }
        }
        
        // Játékosok indexének beállítása
        int i = 0;
        for(Player player: this.master.players){
            player.index = i;
            i++;
        }
        
        // Játékosok száma alapján a menetek számának meghatározása
        master.round_number = (52/this.master.players.size())*2-2;
        
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
        playerone.PlayerLabel.setText(master.players.get(0).name + ":");
        playerone.EstimateLabel.setText(this.master.players.get(0).estimate.toString());
            
        frame.ScorePanel.add(playerone);
        
        for(int p = 1; p < master.players.size(); p++){
            DrawPlayer player = new DrawPlayer();
            this.drawplayers.add(player);
            player.PlyarPic.setIcon(new ImageIcon("Robot" + ".jpg"));
            player.PlayerLabel.setText(master.players.get(p).name + ":");
            playerone.EstimateLabel.setText(this.master.players.get(p).estimate.toString());
            frame.ScorePanel.add(player);
        }      
    }
    /*
    * A játékos által kiválasztott kártya felrakása az azstalra
    */
    public void putOnTable(int c, DrawCard temp_card){
        master.cardsInPlay.add(master.players.get(0).cards.remove(c));
        frame.TablePanel.add(temp_card);
        frame.revalidate();
        frame.repaint();
        player1Turn = false;
        click = true;
    }
    
    /*
    * Körök elejének kirajzolása: Adu + Player1 kártyái az eseménykezelőkkel.
    */
    public void drawRoundBegin(){
        
        frame.TrumpPic.setIcon(new ImageIcon(master.trump + "_trump.jpg"));
        frame.RoundNumber.setText(this.master.round_index-1 + "/" + this.master.round_number);
        frame.PlayerPanel.removeAll();
        frame.revalidate();
        frame.repaint();
        
        for(int c = 0; c < master.players.get(0).cards.size(); c++){
            DrawCard card = new DrawCard();
            card.TypeLabel.setIcon(new ImageIcon(master.players.get(0).getCard(c).getType() + ".jpg"));
            card.type = master.players.get(0).getCard(c).getType();
            card.ValueLabel.setText(master.players.get(0).getCard(c).getValue());
            
            MouseListener mouselistener = new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    
                    if(e.getClickCount()== 2 && player1Turn == true){
                        
                        DrawCard temp_card = (DrawCard)e.getSource();                        
                        for(int c = 0; c < master.players.get(0).cards.size(); c++){  
                            Card card = master.players.get(0).getCard(c);
                            //meg kell találni a kijelölt kártyát
                            //kattintott kártya típusának lekérése
                            if(temp_card.type.equals(card.getType()) && temp_card.ValueLabel.getText().equals(card.getValue())){
                                //ha üres az asztal, rakhatunk bármit 
                                if(master.cardsInPlay.isEmpty()){
                                    putOnTable(c,temp_card);
                                    System.out.println("kezdő");}
                                // hívott lappal egyenlő amit rakni akarunk, rakhatjuk
                                else if(temp_card.type.equals(master.cardsInPlay.get(0).getType())){ 
                                    putOnTable(c,temp_card);
                                    System.out.println("a hívottat raktuk");}
                                // ha amire kattintunk nem a hívott lap, de adu, rakhatjuk, ha nincs a kezünkben a hívott lapból egy sem
                                else if(!temp_card.type.equals(master.cardsInPlay.get(0).getType()) && master.players.get(0).cards.get(c).getRoundRank() > 19){
                                    boolean ok = true;
                                    for(int i = 0; i < master.players.get(0).cards.size(); i++ ){
                                        if(master.cardsInPlay.get(0).getType().equals(master.players.get(0).cards.get(i).getType()))
                                            ok = false;
                                    }
                                    if(ok){
                                        putOnTable(c,temp_card);
                                        System.out.println("hívott lap helyett adu");
                                        ok = false;}
                                    else{
                                        System.out.println("Hibás lapválasztás, van a kezedben hívott lap(adura kattintottál)");
                                    }
                                }
                                // ha amire kattintunk nem a hívott lap és nem is adu, akkor rakhatjuk ha nincs a kezünkben a hívott lapból, vagy aduból
                                else if(!temp_card.type.equals(master.cardsInPlay.get(0).getType()) && master.players.get(0).cards.get(c).getRoundRank() < 19){
                                    boolean ok = true;
                                    boolean trump = false;
                                    for(int i = 0; i < master.players.get(0).cards.size(); i++ ){
                                        if(master.cardsInPlay.get(0).getType().equals(master.players.get(0).cards.get(i).getType()))
                                            ok = false;
                                        if(master.players.get(0).cards.get(i).getRoundRank() > 19)
                                            trump = true;
                                    }
                                    if(ok && trump){
                                        System.out.println("Hibás lapválasztás, hívott lap nincs, adu van");
                                        ok = false;
                                        trump = false;}
                                    if(ok){
                                        putOnTable(c,temp_card);
                                        System.out.println("hívott lap nincs, adu sincs");
                                        ok = false;}
                                    else{
                                        System.out.println("Hibás lapválasztás, van a kezedben hívott lap/adu");
                                   }
                                }                                            
                            }                            
                        }                        
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
    }
    
    /*
    * Játékosok indexének beállítása a helyes sorrendhez
    */
    public void setIndex(){
        this.winner_index = this.master.round_index-2%this.master.players.size();
    }
    
    public void round(int i){
  
        frame.TablePanel.removeAll();
        
        //Robot döntéséhez kell, átadjuk a pick fv-nek. 
        int players_cntr = this.master.players.size();
        
        for (int index = this.winner_index; index < this.winner_index+this.master.players.size(); index++){
            
            players_cntr--;
            
            Player player = this.master.players.get(index%this.master.players.size());
            this.drawplayers.get(index%this.master.players.size()).arrowLabel.setIcon(new ImageIcon("arrow.jpg"));
            
            //lekéri a kártyát, amit dob a játékos
            if(player instanceof PlayerOne){
                frame.revalidate();
                frame.repaint();
                this.player1Turn = true; 
                while(!this.click && !Thread.interrupted()){ }               
            }
            else {
                Card c = player.pick(this.master.cardsInPlay, this.master.trump, players_cntr);
                this.master.cardsInPlay.add(c);
            
                 //kirajzolás
                DrawCard card2 = new DrawCard();
                card2.TypeLabel.setIcon(new ImageIcon(c.getType() + ".jpg"));
                card2.ValueLabel.setText(c.getValue());
                frame.TablePanel.add(card2);
            }

            this.click = false;
            frame.revalidate();
            frame.repaint();
                 
            try{
                Thread.sleep(500);
            } catch(Exception e){
                System.out.println("Hiba az időzítéssel!");
            }
            
            this.drawplayers.get(index%this.master.players.size()).arrowLabel.setIcon(new ImageIcon("empty.jpg"));
        }    
    }
    
    public void game(){
        //for ciklus -> k=1 - körök számáig
        for(int k = 1; k <= this.master.round_number; k++){
            this.master.shuffleDeck();
            System.out.println(this.master.trump);
            this.setIndex();
           
            if(this.master.round_index > this.master.round_number/2+2){
                this.master.backward_index += 2;
            }
            
            if(this.master.round_index == this.master.round_number/2+2){
                this.master.backward_index = 1;
            }
            
            //round változóba belerakja, hogy hány kör lesz, ez alapján a kártyák kiosztás
            this.master.dealCards();
            
            //kirajzol
            this.drawRoundBegin();
            
            for(int i = 0; i < this.master.players.size(); i++){
               this.drawplayers.get(i).hitsLabel.setText("0");
            }
            
            //becslések begyűjtése
            this.master.setEstimates();
            
            for(int i = 0; i < this.master.players.size(); i++){
                this.drawplayers.get(i).EstimateLabel.setText(this.master.players.get(i).estimate.toString());
            }
            
            //for ciklussal végigmegyünk a meneteken
            for(int i = 1; i<=this.master.round_index-(this.master.backward_index); i++){
               
                this.round(i);
                this.winner_index = this.master.getWinner();
                
                //for ciklusban beállítjuk a kezdőjátékoshoz tartozó roundstarter változót
                for(int j = 0; j < this.master.players.size(); j ++){
                    if(this.winner_index == this.master.players.get(j).index)
                        this.master.players.get(j).setRoundStarter(true);
                    else
                        this.master.players.get(j).setRoundStarter(false);
                }
                
                this.drawplayers.get(this.winner_index).hitsLabel.setText(this.master.players.get(this.winner_index).hits.toString());
                this.master.cardsOnTable.addAll(this.master.cardsInPlay);
                this.master.cardsInPlay.removeAll(this.master.cardsInPlay);
                try{
                    Thread.sleep(1000);
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
                
                frame.TablePanel.removeAll();
            }
            
            this.master.sum();
            
            for(int i = 0; i < this.master.players.size(); i++){
               this.drawplayers.get(i).ScoreLabel.setText(this.master.players.get(i).score.toString());
            }
            
            this.master.cardsOnTable.removeAll(this.master.cardsOnTable);
            this.master.round_index++;
        }
        
        // for ciklus után -> nyertes kihirdetése
        JOptionPane.showMessageDialog(null, this.master.getFinalWinner());
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
