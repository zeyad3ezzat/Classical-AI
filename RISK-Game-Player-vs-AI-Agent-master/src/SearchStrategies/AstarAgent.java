/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchStrategies;

import Map.Territory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import GameBoard.Dice;

/**
 *
 * @author Arwa
 */
public class AstarAgent implements PlayingStrategy {

    // heuristics will use same method, law el node el gdeeda msh hat5aly el adeema safe han2asem el troops 3la el 2
    //copy paste eli fl greedy after checking it..
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
        PriorityQueue<Territory> queue = new PriorityQueue<Territory>();

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
            if (territory.getOwner() != defender.getOwner() && territory.getOwner() != null) {
                dice.rollDices(territory, defender);

            }
        }
    }
}
