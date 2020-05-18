/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchStrategies;

import GameBoard.Dice;
import Map.Territory;
import gui.GamePlay;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Arwa
 */
public class MinimaxAgent implements PlayingStrategy {

    Comparator<Territory> heursticComparator = new Comparator<Territory>() {
        @Override
        public int compare(Territory n1, Territory n2) {
            float heurstic1 = n1.calculateHeuristic();
            float heurstic2 = n2.calculateHeuristic();

            return Math.round(heurstic2 - heurstic1);
        }
    };

    public Territory Search(Territory attacker, int level, int alpha, int beta) {

        Territory defender = null;
        Territory currentNeighbour = null;
        System.out.println("level: " + level);

        if (level == 0 || GamePlay.getPlay1Phase().get().equalsIgnoreCase("GameOver")) {
            return attacker;
        }

        //maximizer's turn
        System.out.println("attacker owner: " + attacker.getOwner());
        if (attacker.getOwner() != null && attacker.getOwner().getPlayerType().equals("minimax")) {
            System.out.println("player is maximizer");
            ArrayList<Territory> attackerNeighbours = attacker.getNeighbours();

            for (int i = 0; i < attackerNeighbours.size(); i++) {
                currentNeighbour = attackerNeighbours.get(i);
                System.out.println("attacker terr id: " + attacker.getID());

                defender = Search(currentNeighbour, level - 1, alpha, beta);

                System.out.println("defender: " + defender.getID());
                if (defender.calculateHeuristic() > alpha) {

                    alpha = Math.round( defender.calculateHeuristic());
                    System.out.println("alpha is: " + alpha);

                }
                if (alpha >= beta) {
                    //pruning
                    System.out.println("pruning");
                    continue;
                }

            }
            return defender;
        } //player is minimzer
        else {
            System.out.println("player is minimzer");
            ArrayList<Territory> attackerNeighbours = attacker.getNeighbours();

            for (int i = 0; i < attackerNeighbours.size(); i++) {

                currentNeighbour = attackerNeighbours.get(i);

                defender = Search(currentNeighbour, level - 1, alpha, beta);
                if (defender.calculateHeuristic() < beta) {
                    beta = Math.round(defender.calculateHeuristic());
                }
                if (alpha >= beta) {
                    continue;
                }

            }
            return defender;
        }

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
                System.out.println("hereee");
                queue.add(Search(territory, 2, Integer.MIN_VALUE, Integer.MAX_VALUE));
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
