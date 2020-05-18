/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchStrategies;

import GameBoard.Dice;
import GameBoard.Player;
import Map.Territory;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Arwa
 */
public class PacifistAgent implements PlayingStrategy {

    @Override
    public void addTroops(int numberOfTroops, ArrayList<Territory> ownedTerritories) {
        Iterator<Territory> iterator = ownedTerritories.iterator();
        int minTroops = Integer.MAX_VALUE;
        int minId = -1;
        int i = 0;
        

        while (iterator.hasNext()) {
            Territory tempTerr = iterator.next();
            int troopsNumber = tempTerr.getTroopsNumber();
            if (minTroops > troopsNumber) {
                minId = i;

            }
            i++;

        }
        ownedTerritories.get(minId).setTroopsNumber(ownedTerritories.get(minId).getTroopsNumber() + numberOfTroops);
    }

    @Override
    public void attack(ArrayList<Territory> ownedTerritories) {
        Dice dice = new Dice();
        int i = 0, j = 0;
        int defenderID = -1, attackerID = -1;
        // ArrayList<Territory> updatedTerritoriesList = new ArrayList<>();
        Player currentOwner = ownedTerritories.get(i).getOwner();
        int minAttackTroopNo = Integer.MAX_VALUE;
        
        while (i < ownedTerritories.size()) {
        	
            Territory currentTerritory = ownedTerritories.get(i);
//        	System.out.println("i "+i);
           
            if (currentTerritory.isSafeState()) {
            	
            	i++;
                continue;
            } else {
                ArrayList<Territory> neighboursTerritoryList = new ArrayList<>();
                neighboursTerritoryList = currentTerritory.getNeighbours();
                //attackerID++;
               // defenderID=0;
                j=0;
               
                while (j < neighboursTerritoryList.size()) {
//                    System.out.println("j "+j );

                    Territory currentNeighbour = neighboursTerritoryList.get(j);
                    Player currentNeighbourOwner = currentNeighbour.getOwner();
                    if (currentNeighbourOwner != currentOwner) {
//                    	System.out.println("current territ"+currentTerritory.getID());
//                    	System.out.println("neighbout "+currentNeighbour.getID());
                    	
                        if (minAttackTroopNo > currentNeighbour.getTroopsNumber()) {
                            minAttackTroopNo = currentNeighbour.getTroopsNumber();
                            attackerID=i;
                            defenderID=j;
                  /*          System.out.println("attacked id "+attackerID);
                            System.out.println("defender id  "+defenderID);
                            System.out.println("atttttt"+ownedTerritories.get(attackerID).getID());
                            System.out.println("defenedddddd"+ownedTerritories.get(attackerID).getNeighbours().get(defenderID).getID());
                 */       }
                    }else{
                    
                    }

                    j++;
                }
                i++;
            }
           
        }
     //   System.out.println();
//        System.out.println("attacker id from out loop"+ownedTerritories.get(attackerID).getID());
//        System.out.println("defender id from out loop"+ownedTerritories.get(attackerID).getNeighbours().get(defenderID).getID());
        dice.rollDices(ownedTerritories.get(attackerID), ownedTerritories.get(attackerID).getNeighbours().get(defenderID));

    }

}
