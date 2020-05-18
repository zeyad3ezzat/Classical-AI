/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchStrategies;

import GameBoard.Dice;
import Map.Territory;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Arwa
 */
public class AstarRealTimeAgent implements PlayingStrategy {

    //same as Astar but we will call the resetTree method each time
    private int maxHeuristic = 0;
    private int attackNo = 0;
    ArrayList<Territory> explored;
    PriorityQueue<Territory> frontier;
    Comparator<Territory> heursticPlusCostComparator = new Comparator<Territory>() {
        @Override
        public int compare(Territory n1, Territory n2) {
            float heurstic1 = n1.calculateHeuristic();
            float heurstic2 = n2.calculateHeuristic();

            return Math.round(heurstic2 - heurstic1);
        }
    };

    public Territory Search(Territory attacker) {

        //      ArrayList<Territory> ret=new ArrayList<>();
        PriorityQueue<Territory> frontier
                = new PriorityQueue<Territory>(heursticPlusCostComparator);
        // Territory lastNode;
        ArrayList<Territory> neighbours = attacker.getNeighbours();
        //to add the first value
        int i = 0;
        for (Territory territory : neighbours) {

            if (territory.getOwner() != attacker.getOwner()) {
                territory.calculateHeuristic();
                frontier.add(territory);
                i++;
                break;
            }

            i++;
        }
        Territory defender = null;
        i = 0;
        while (!frontier.isEmpty()) {

            defender = frontier.remove();
            if (i < neighbours.size() && neighbours.get(i).getOwner() != attacker.getOwner()) {
                neighbours.get(i).calculateHeuristic();
                frontier.add(neighbours.get(i));
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
        Territory chosenTerritory = null;
        while (!queue.isEmpty()) {
            chosenTerritory = queue.poll();

        }

        chosenTerritory.setTroopsNumber(chosenTerritory.getTroopsNumber() + numberOfTroops);

    }

    @Override
    public void attack(ArrayList<Territory> ownedTerritories) {
        Dice dice = new Dice();
        int flag = 0;

        PriorityQueue<Territory> queue = new PriorityQueue<Territory>();
        if (attackNo == 0) {

            for (Territory territory : ownedTerritories) {
                if (!territory.isSafeState()) {
                    queue.add(Search(territory));
                }

            }
            Territory defender = null;
            while (!queue.isEmpty()) {
                defender = queue.poll();

            }
            for (Territory territory : defender.getNeighbours()) {

                if (maxHeuristic == 0) {
                    maxHeuristic = territory.getG();

                } else if (maxHeuristic < territory.getG()) {
                    maxHeuristic = territory.getG();
                }

                if (territory.getOwner() != defender.getOwner() && territory.getOwner() != null) {
                    dice.rollDices(territory, defender);

                }
            }
            attackNo++;
        } else {
            do {

                for (Territory territory : ownedTerritories.get(0).getOwner().getOwnedTerritories()) {
                    queue.clear();
                    if (!territory.isSafeState() && territory.getG() >= maxHeuristic) {
                        queue.add(Search(territory));
                        territory.getOwner().getOwnedTerritories();
                    }
                    Territory defender = null;

                    while (!queue.isEmpty()) {
                        defender = queue.poll();

                    }
                    
                
                    for (Territory currTerritory : defender.getNeighbours()) {

                        if (maxHeuristic == 0) {
                            maxHeuristic = currTerritory.getG();

                        } else if (maxHeuristic < currTerritory.getG()) {
                            maxHeuristic = currTerritory.getG();
                        }

                        if (currTerritory.getOwner() != defender.getOwner() && currTerritory.getOwner() != null) {
                            dice.rollDices(currTerritory, defender);

                        }
                    }

                }
            } while (flag == 1);
        }
    }
}
