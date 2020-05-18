package SearchStrategies;

import java.util.ArrayList;
import java.util.Iterator;

import GameBoard.Dice;
import GameBoard.Player;
import Map.Territory;

public class AgressiveAgent implements PlayingStrategy{

	@Override
	public void  addTroops(int numberOfTroops,ArrayList<Territory> ownedTerritories) {
		Iterator<Territory> iterator=ownedTerritories.iterator();
		int maxTroops=Integer.MIN_VALUE;
		int maxId=-1;
		int i=0;
		
		while(iterator.hasNext()){
			Territory tempTerr=iterator.next();
			int troopsNumber=tempTerr.getTroopsNumber();
			if(maxTroops<troopsNumber){
				maxTroops=troopsNumber;
				maxId=i;
			}
			i++;

			
		}
		
	 ownedTerritories.get(maxId).setTroopsNumber(ownedTerritories.get(maxId).getTroopsNumber()+numberOfTroops);
	 }
	//not complete
	@Override
    public void attack(ArrayList<Territory> ownedTerritories) {


        Dice dice = new Dice();
        int i = 0, j = 0;
        int defenderID = 0, attackerID = 0;
        int maxAttackTroopNo = Integer.MIN_VALUE;

        while (i < ownedTerritories.size()) {
            Player currentOwner = ownedTerritories.get(i).getOwner();

        	System.out.println(ownedTerritories.size());
        
            Territory currentTerritory = ownedTerritories.get(i);
            if (currentTerritory.isSafeState()) {
            	i++;
                continue;
            } else {
                ArrayList<Territory> neighboursTerritoryList = new ArrayList<>();
                neighboursTerritoryList = currentTerritory.getNeighbours();
                
                j=0;
                while (j < neighboursTerritoryList.size()) {
                    Territory currentNeighbour = neighboursTerritoryList.get(j);
                    Player currentNeighbourOwner = currentNeighbour.getOwner();

                    if (currentNeighbourOwner != currentOwner) {
                    	dice.rollDices(currentTerritory, currentNeighbour);;
                        /*if (maxAttackTroopNo < currentNeighbour.getTroopsNumber()) {
                            maxAttackTroopNo = currentNeighbour.getTroopsNumber();
                            defenderID=j;
                            attackerID=i;
                        }*/
                    }

                    j++;
                }
                i++;
            }
            
        }

     //   dice.rollDices(ownedTerritories.get(attackerID), ownedTerritories.get(attackerID).getNeighbours().get(defenderID));
        
    }
		
			
		
		
		
	}


