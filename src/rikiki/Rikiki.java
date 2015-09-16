
package rikiki;

/**
 * Példányosítások + GUI
 * @author Márk
 */
public class Rikiki {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        
        PlayerOne player1 = new PlayerOne();
        Robot player2 = new Robot();
        Robot player3 = new Robot();
        Robot player4 = new Robot();
        Master master = new Master();
        master.setPlayers(player1);
        master.setPlayers(player2);
        master.setPlayers(player3);
        master.setPlayers(player4);
        master.setRound(10);
        master.initDeck();
        master.shuffleDeck();
        /*for(int i=0; i<master.getDeck().size(); i++){
            System.out.println(master.getCard(i).getType() + master.getCard(i).getValue());
        }*/
        System.out.println(master.getTrump());
        master.dealCards();
        for(int i = 0; i<4; i++){
            System.out.println(i + ". Player:");
            for(int j = 0; j < master.getPlayer(i).getCards().size(); j++){
                System.out.println(master.getPlayer(i).getCard(j).getType() + master.getPlayer(i).getCard(j).getValue() + " " 
                        + master.getPlayer(i).getCard(j).geAllTimeRank());
            }
        }
    }   
}
