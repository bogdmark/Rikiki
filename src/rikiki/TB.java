/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rikiki;

import java.util.ArrayList;

/**
 *
 * @author Márk
 */
public class TB {
    
    public boolean IhaveTrump;
    public boolean IhaveColor;
    public boolean IhaveShit;
    public ArrayList<TBauxiliary> lackOfCards;

    public TB(){
        this.IhaveTrump = false;
        this.IhaveColor = false;
        this.IhaveShit = false;
        this.lackOfCards = new ArrayList();     
    }
    
    public void clearTB(){
        for(TBauxiliary TBA : this.lackOfCards){
            TBA.lack = false; 
        }
    }
    
    public void setNews(int playerNumber, String type){
        for(TBauxiliary TBA : this.lackOfCards){
            if(TBA.playerNumber == playerNumber && TBA.type.equals(type)){
                TBA.lack = true;
            }
        }
    }
    
    public boolean checkLackOfCards(String type){
        boolean lack = false;
        for(TBauxiliary TBA : this.lackOfCards){
            if(TBA.type.equals(type) && TBA.lack){
                lack = true;
            }
        }
        return lack;
    }
}
