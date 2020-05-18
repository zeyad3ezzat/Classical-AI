package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import javax.xml.soap.Node;

import GameBoard.GameController;
import GameBoard.Player;
import Map.Builder;
import Map.EgyptBuilder;
import Map.Territory;
import Map.USABuilder;
import SearchStrategies.AgressiveAgent;
import SearchStrategies.GreedyAgent;
import SearchStrategies.PacifistAgent;
import SearchStrategies.PassiveAgent;
import SearchStrategies.PlayingStrategy;

public class main {
	

	public static void main(String[] args) {
		GameController controller=new GameController();
		PlayingStrategy gready=new GreedyAgent();
		PlayingStrategy Agressive=new AgressiveAgent();
		PlayingStrategy passive=new PassiveAgent();
		controller.setFirstPlayerStrategy(passive);
		controller.setSecondPlayerStrategy(gready);
		Territory[] map;
		map=controller.setMap(controller.USA);

		for (int i = 0; i < map.length; i++) {
			map[i].setTroopsNumber(10);
		}
		
		
		
		
		for (int i = 1; i < 6; i++) {
			map[i].setOwner(controller.getSecPlayer());
		}
		for (int i = 6; i < 49; i++) {
			map[i].setOwner(controller.getFirstPlayer());
		}
		for (int i = 0; i < map.length; i++) {
			map[i].isSafeState();
		}
		map[50].setOwner(controller.getSecPlayer());
		map[49].setOwner(controller.getSecPlayer());
		map[8].setTroopsNumber(5);
		map[9].setTroopsNumber(3);
		
		
	
		
	//	printTheMap(map);
		
		System.out.println("After attack");
		//controller.AddTroops(controller.getFirstPlayer());;
		
		
		//controller.initializeTheGameBoard();
		
		//controller.AddTroops(controller.getSecPlayer());
	//	System.out.println("after passive adding troops");

	//	printTheMap(map);
		//controller.AddTroops(controller.getFirstPlayer());
	//	System.out.println("after pacifist adding troops");
	//	printTheMap(map);
		
	//	controller.Attack(controller.getFirstPlayer());
	//	printTheMap(map);
		
	
	
	}
	static public void printTheMap(Territory[] map){
		System.out.println();
		System.out.println();
		System.out.println();
		
		for (Territory territory :map) {
			
			if (territory.getTroopsNumber()!=0) {
				System.out.print("territory ID->"+territory.getID());
				System.out.print("  troops Number "+territory.getTroopsNumber());
				if(territory.getOwner()!=null){
					System.out.print("  owner id"+territory.getOwner().getId());
					System.out.print("   is safe state "+territory.isSafeState());
				}
				System.out.println("");

			}
			
	}
	}

}
