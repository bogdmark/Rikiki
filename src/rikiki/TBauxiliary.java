/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rikiki;

/**
 *
 * @author Márk
 */
public class TBauxiliary {
    public int playerNumber;
    public String type;
    public boolean lack;

    public TBauxiliary(){
        this.playerNumber = 0;
        this.lack = false;
        this.type = "";
    }
    
    public TBauxiliary(int playerNumber, String type){
        this.playerNumber = playerNumber;
        this.lack = false;
        this.type = type;
    }
}
