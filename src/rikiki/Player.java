
package rikiki;

import java.util.ArrayList;

/**
 * Közös ősosztály a játékosnak és a robotoknak
 * @author Márk
 */
public class Player {
    
    public String name;
    public ArrayList<Card> cards;
    public Integer estimate;
    public Integer score;
    public Integer hits;
    public int index;
    public boolean round_starter;
    public TB tb;
    public int nrofplayers;
    
    public Player(String name){
        this.cards = new ArrayList<>();
        this.tb = new TB();
        this.score = 0;
        this.estimate = 0;
        this.hits = 0;
        this.name = name;
        this.index = 0;
    }
    
    public Player(){
        this.cards = new ArrayList<>();
        this.score = 0;
        this.estimate = 0;
        this.hits = 0;
        this.name = "player";
        this.tb = new TB();
        
    }
    
     public Card pick(ArrayList<Card> cardsInPlay, String trump, int players_cntr){
         
         ArrayList<Card> playableCards = new ArrayList<Card>();
         Card pickedCard;
         pickedCard = cards.get(0);
         
         //kell még nyerni?
         boolean shouldIwin = estimate > hits; 
         
        //Ha ő az első a körben, az összes kártyáját használhatja
        //TODO! ennek a fv-ét megírni!
         if(cardsInPlay.isEmpty()){
             playableCards = this.cards;
             pickedCard = this.firstToPick(playableCards, shouldIwin);
         }
         //Ha nem ő az első a körben, csak a megengedett lapokat használhatja
         else{
             //A kijátszható kárták összegyűjtése
             playableCards = this.getPlayableCards(cardsInPlay, trump);
             
             //Tudok nyerni ebben a körben?
             boolean CanIwin = this.canIwin(cardsInPlay, playableCards, trump);
             
             if(!CanIwin){
                 pickedCard = this.cantWin(shouldIwin, playableCards);
             } else {
                 ArrayList<Card> winnerCards = this.winnerCards(cardsInPlay, playableCards, trump);
                 pickedCard = this.canWin(shouldIwin, playableCards, winnerCards, cardsInPlay, trump, players_cntr);
             }
         }
         
        cards.remove(pickedCard);
        return pickedCard;
    }
    
     /*
     * A kijátszható kártyák összegyűjtése
     */
    public ArrayList<Card> getPlayableCards(ArrayList<Card> cardsInPlay, String trump){
        
        ArrayList<Card> playableCards = new ArrayList<Card>();
        
        //Megnézi, hogy van-e a kért szín
        for(Card card: this.cards){
            if(card.getType().equals(cardsInPlay.get(0).getType())){
                playableCards.add(card);
                this.tb.IhaveColor = true;
            }
        }
        //Ha nincs szín, megnézi, hogy van-e adu
        if(playableCards.isEmpty()){
            this.tb.IhaveColor = false;
            for(Card card: this.cards){
                if(card.getType().equals(trump)){
                    playableCards.add(card);
                    this.tb.IhaveTrump = true;
                }
            }
        }
        //Ha adu sincs
        if(playableCards.isEmpty()){
            this.tb.IhaveTrump = false;
            this.tb.IhaveShit = true;
            playableCards = this.cards;
        }
        return playableCards;
    }
    
    /*
    * Tud-e nyerni ebben a körben.
    */
    public boolean canIwin (ArrayList<Card> cardsInPlay, ArrayList<Card> playableCards, String trump){
        
        boolean canIwin = false;
        boolean isTrump = false;
        
        if(this.tb.IhaveColor){
            for(Card mycard : playableCards){
                for(Card card : cardsInPlay){
                    if(card.getType().equals(trump)){
                        isTrump = true;
                    }
                    if(!isTrump && card.getType().equals(cardsInPlay.get(0).getType()) && mycard.getAllTimeRank() > card.getAllTimeRank()){
                        canIwin = true;
                    }
                }
            }
        }
        else if(this.tb.IhaveTrump){
            canIwin = true;
            for(Card mycard : playableCards){
                for(Card card : cardsInPlay){
                    if(card.getType().equals(trump) && mycard.getAllTimeRank() < card.getAllTimeRank()){
                        canIwin = false;
                    }
                }
            }
        }
        return canIwin;
    }
    
    public ArrayList<Card> winnerCards(ArrayList<Card> cardsInPlay, ArrayList<Card> playableCards, String trump){
        
        ArrayList<Card> winnerCards = new ArrayList();
        
        //Legjobb letett kártya megtalálása
        Card bestCard = this.getBestCardInPlay(cardsInPlay, trump);
        
        //Ennél jobbak megkeresése
        for(Card card: playableCards){
            if(card.getRoundRank()> bestCard.getRoundRank()){
                winnerCards.add(card);
            }
        }
        return winnerCards;
    }
    
    public Card getBestCardInPlay(ArrayList<Card> cardsInPlay, String trump){
        
        Card bestCard = cardsInPlay.get(0);
        for(Card card: cardsInPlay){
            if((bestCard.getType().equals(card.getType()) && card.getAllTimeRank() > bestCard.getAllTimeRank()) ||
              (!bestCard.getType().equals(trump) && card.getType().equals(trump))){
                bestCard = card;
            }
        }
        return bestCard;
    }
    
    /*
    * Nem tud nyerni ebben a körben, melyik lapot dobja?
    */
    public Card cantWin(boolean shouldIwin, ArrayList<Card> playableCards){
        
        Card pickedCard = playableCards.get(0);
        if(shouldIwin){
            for(Card card: playableCards){
                if(card.getRoundRank() > pickedCard.getRoundRank()){
                    pickedCard = card;
                }
            }
        } else{
            for(Card card: playableCards){
                if(card.getRoundRank() < pickedCard.getRoundRank()){
                    pickedCard = card;
                }
            }
        }
        return pickedCard;
    }
    
    /*
    * Tud nyerni a körben, de nem feltétlenül kell is nyernie, ez még ki fog derülni. 
    */
    public Card canWin(
            boolean shouldIwin, 
            ArrayList<Card> playableCards, 
            ArrayList<Card> winnerCards, 
            ArrayList<Card> cardsInPlay, 
            String trump,
            int players_cntr){
        
        Card pickedCard = playableCards.get(0);
        
        //Tud nyerni, és kell is, de most?
        if(shouldIwin){
            
            // 1-et, vagy többet?
            boolean moreTowin = this.estimate - this.hits > 1;
            
            //Van olyan lapja, amivel kéne nyernie? 
            boolean toWin = false;
                for(Card card: winnerCards){
                    if(card.toWin){
                        toWin = true;
                    }
                }
            
            // Ha többet kell nyernie 
            if(moreTowin){
                // És a lapok közt van olyan, amivel kéne
                if(toWin){
                    pickedCard = finalWin(winnerCards, moreTowin);
                } else {
                    pickedCard = this.canILoose(playableCards, cardsInPlay, trump, players_cntr);
                }
            } else {
                
                if(toWin){
                    pickedCard = finalWin(winnerCards, moreTowin);
                } else {
                    pickedCard = this.canILoose(playableCards, cardsInPlay, trump, players_cntr);
                }
            }
        } else {
            pickedCard = this.canILoose(playableCards, cardsInPlay, trump, players_cntr);
        }
        return pickedCard;
    }
    
    public Card finalWin(ArrayList<Card> winnerCards, boolean moreTowin){
        
        Card pickedCard = winnerCards.get(0);
        for(Card card: winnerCards){
            if(card.toWin){
                if(!moreTowin){
                    if(pickedCard.getRoundRank() < card.getRoundRank()){
                        pickedCard = card;
                    } 
                }
                else {
                    if(pickedCard.getRoundRank() > card.getRoundRank()){
                        pickedCard = card;
                    }
                }   
            }
        }     
        return pickedCard;
    }
    
    public Card canILoose(ArrayList<Card> playableCards, ArrayList<Card> cardsInPlay, String trump, int players_cntr){
        
        Card pickedCard = playableCards.get(0);
        
        ArrayList<Card> looserCards = this.getLooserCards(playableCards, cardsInPlay, trump);
        
        if(looserCards.isEmpty()){
            if(players_cntr > 0){
                pickedCard = this.getOptimalCard(playableCards, false);
            } else {
                pickedCard = this.getOptimalCard(playableCards, true);
            }
        } else {
            //TODO!! finomítani!, Ezt a laza döntést még később megbánhatja!!
            // Függenie kéne attól, hogy nyerésre szánt lapjainknak mennyi az esélyük...vagy nem, lehet, hogy így is jó!
            pickedCard = this.getBestOfLooserCards(looserCards);
        }
        
        return pickedCard;
    }
    
    public ArrayList<Card> getLooserCards(ArrayList<Card> playableCards, ArrayList<Card> cardsInPlay, String trump){
        
        ArrayList<Card> looserCards = new ArrayList();
        Card bestCard = this.getBestCardInPlay(cardsInPlay, trump);
        
        for(Card card: playableCards){
            if(card.getRoundRank() < bestCard.getRoundRank()){
                looserCards.add(card);
            }
        } 
        return looserCards;
    }
    
    public Card getBestOfLooserCards(ArrayList<Card> looserCards){
        
        Card pickedCard = looserCards.get(0);
        for(Card card: looserCards){
            if(card.getRoundRank() > pickedCard.getRoundRank()){
                pickedCard = card;
            }
        }
        return pickedCard;
    }
    
    public Card getOptimalCard(ArrayList<Card> playableCards, boolean lastPlayer){
        
        Card pickedCard = playableCards.get(0);
        
        for(Card card: playableCards){
            if(lastPlayer){
                if(card.getRoundRank() > pickedCard.getRoundRank()){
                    pickedCard = card;
                }
            } else {
                if(card.getRoundRank() < pickedCard.getRoundRank()){
                    pickedCard = card;
                }
            }
        }
        return pickedCard;
    }
    
    public Card firstToPick(ArrayList<Card> playableCards, boolean shouldIwin){
        
        Card pickedCard = playableCards.get(0);
        
        if(!shouldIwin){
            for(Card card: playableCards){
                if(card.getRoundRank() < pickedCard.getRoundRank()){
                    pickedCard = card;
                }
            }
        } else {
            //TODO!! na ezt lehetne még hangolni a TB alapján
            for(Card card: playableCards){
                if(card.getRoundRank() > pickedCard.getRoundRank()){
                    pickedCard = card;
                }
            }
        }
        return pickedCard;
    }
    
    public void updateTB(){
        
    }
    
    public void recalculate(){
        
        ArrayList<Card> toWinCards = this.getToWinCards();
        if(toWinCards.size() > this.estimate - this.hits){
            
        } else if (toWinCards.size() < this.estimate - this.hits){
            
        }
    }
    
    public ArrayList<Card> getToWinCards(){
        
        ArrayList<Card> toWinCards = new ArrayList();
        for(Card card: this.cards){
            if(card.toWin == true){
                toWinCards.add(card);
            }
        }
        return toWinCards;
    }
    
    public Card getCard(int i){
        return this.cards.get(i);
    }
    
    public void setCard(Card card){
        this.cards.add(card);
    }
   
    public void setEstimate(){
        this.estimate = 0;
    }

    public void setRoundStarter(boolean a){
        this.round_starter = a;
    }
    
    public boolean getRoundStarter(){
        return this.round_starter;
    }
    
    public void setCurrentRankForCardsInHand(){
        this.cards.stream().forEach((_item) -> {
            _item.setCurrentRank(0);
                    });
    }    
}
