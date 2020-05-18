/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchStrategies;

import Map.Territory;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import GameBoard.Dice;

/**
 *
 * @author Arwa
 */
public class GreedyAgent implements PlayingStrategy {
	Comparator<Territory> heursticComparator = new Comparator<Territory>() {
        @Override
        public int compare(Territory n1, Territory n2) {
            float heurstic1 = n1.calculateHeuristic();
            float heurstic2 = n2.calculateHeuristic();

            return Math.round(heurstic2 - heurstic1);
        }
    };
    public void buildTree(ArrayList<Territory> ownedTerritories) {

        for (int j = 0; j < ownedTerritories.size(); j++) {

            Territory currentNode = ownedTerritories.get(j);
            ArrayList<Territory> neighboursOfRoot = currentNode.getNeighbours();
            int currentOwnerID = currentNode.getOwner().getId();

            if (currentNode.isSafeState()) {
                continue;
            } else {
                for (int i = 0; i < neighboursOfRoot.size(); i++) {

                    Territory currentNeighbour = neighboursOfRoot.get(i);
                    int currentNeighbourID = currentNeighbour.getOwner().getId();

                    if (currentOwnerID != currentNeighbourID) {

                        currentNode.addChild(currentNode, currentNeighbour);

                    }

                }
            }
        }

    }

/*
    public void greedySearch(Territory node) {
        //flag:
        int isFrontier = 0;

        PriorityQueue<Territory> frontier = new PriorityQueue<Territory>();
        //List<Territory> explored = new ArrayList<Territory>();

        isFrontier = 1;
        node.setHeuristic(node.calculateHeuristic());

        frontier.add(node);

        //boolean fron = true, exp = true;
        while (!frontier.isEmpty()) {

            Territory currentNode = (Territory) frontier.remove();

            isFrontier = 0;
            for (int i = 0; i < currentNode.getChildren().size(); i++) {

                if (isFrontier == 0) {
                    frontier.add(currentNode.getChildren().get(i));
                    isFrontier = 1;
                }

            }

        }

    }
*/
    public Territory Search(Territory attacker) {
  //      ArrayList<Territory> ret=new ArrayList<>();
        PriorityQueue<Territory> frontier  = new PriorityQueue<Territory>(heursticComparator);
       // Territory lastNode;
        ArrayList<Territory> neighbours=attacker.getNeighbours();
        //to add the first value
        int i=0;
        for (Territory territory : neighbours) {
        	
        	 if (territory.getOwner() != attacker.getOwner()) {
        		 territory.calculateHeuristic();
                 frontier.add(territory);
                 i++;
                 break;
     		}
        	i++;
		}
        Territory defender=null;
//        i=0;
        while(!frontier.isEmpty()){
        	defender=frontier.remove();
        	if (i<neighbours.size()&&neighbours.get(i).getOwner()!=attacker.getOwner()) {
        		neighbours.get(i).calculateHeuristic();
                frontier.add( neighbours.get(i));
                i++;
    		}
        	
        }
      return defender;
    }

    
    @Override
    public void addTroops(int numberOfTroops, ArrayList<Territory> ownedTerritories) {
       PriorityQueue<Territory> queue = new PriorityQueue<>();
       
    	for (Territory territory : ownedTerritories) {
		if (!territory.isSafeState()) {
			territory.calculateHeuristic();
			queue.add(territory);
		}	
		}
    	Territory chosenTerritory=null;
    	while(!queue.isEmpty()){
        	chosenTerritory=queue.poll();
    	}
        
    	chosenTerritory.setTroopsNumber(chosenTerritory.getTroopsNumber()+numberOfTroops);
    }
    
    @Override
    public void attack(ArrayList<Territory> ownedTerritories) {
    	Dice dice=new Dice();
    	PriorityQueue<Territory> queue = new PriorityQueue<Territory>();
    	
        for (Territory territory : ownedTerritories) {
        	if(!territory.isSafeState()){
        		queue.add(Search(territory));
        	}
		}
        Territory defender = null;
        while (!queue.isEmpty()) {
			defender=queue.poll();
			
		}
        for (Territory territory : defender.getNeighbours()) {
			if (territory.getOwner()!=defender.getOwner()&&territory.getOwner()!=null) {
				dice.rollDices(territory, defender);
			}
		}
       

    }

}
