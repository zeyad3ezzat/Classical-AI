package main;

import java.util.ArrayList;

import javax.xml.soap.Node;

import GameBoard.Dice;
import GameBoard.GameController;
import GameBoard.Player;
import Map.Builder;
import Map.EgyptBuilder;
import Map.Territory;
import Map.USABuilder;
import SearchStrategies.AgressiveAgent;
import SearchStrategies.AstarAgent;
import SearchStrategies.AstarRealTimeAgent;
import SearchStrategies.GreedyAgent;
import SearchStrategies.HumanType;
import SearchStrategies.MinimaxAgent;
import SearchStrategies.PacifistAgent;
import SearchStrategies.PassiveAgent;
import SearchStrategies.PlayingStrategy;
import gui.*;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TestGame extends Application{
 private  static GameController controller  = new GameController();
 private static Player p1,p2,winner;
 private static double gameSpeed = 1;
 private static Stage stage = new Stage();
 private static boolean GameIsOn = true;
 public static Button[] buttons;
 public static Territory[] map;
 public static ArrayList<Territory> coloredTerri = new ArrayList<Territory>();
 private static int i = 1;
	public static void main(String[] args) throws Exception {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		GameBuild GB = new GameBuild();
		GB.start(stage);
		
	}
	public static void StartGame(int mapType ,String player1_Type , String player2_Type) throws InterruptedException {
		initializePlayers(player1_Type, player2_Type);
		controller.setFirstPlayer(p1);
		controller.setSecPlayer(p2);
		controller.setFirstPlayerStrategy(p1.getStrategy());
		controller.setSecondPlayerStrategy(p2.getStrategy());
		map = controller.setMap(mapType);	
		controller.initializeTheGameBoard(mapType);
	
		UPDATE_UI_MAP(map, buttons);
		
		if(GameIsOn) {
			//GamePlay.nextPhase();
//		System.out.println("after passive adding troops");
		if(p1.getPlayerType().equalsIgnoreCase("human"))
		{	
			int extraTroops  = p1.calculateExtraTroops();
			p1.setExtraTroops(extraTroops);
			GamePlay.setPlay1Troops(extraTroops);
			GamePlay.setPlay1Phase("Idle");
			
			GamePlay.setPlay1Terri(p1.getNumberOfOwnedTerritories());
			GamePlay.setPlay2Terri(p2.getNumberOfOwnedTerritories());
			GamePlay.getPlay1Phase().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable) {
					if(GamePlay.getPlay1Phase().get().equalsIgnoreCase("Placing")) {
						HumanPlacing();
					}
					if(GamePlay.getPlay1Phase().get().equalsIgnoreCase("Attacking")) {
						HumanAttack();
					}
					if(GamePlay.getPlay1Phase().get().equalsIgnoreCase("Idle")) {
						resetButtonActions();
					}
					if(GamePlay.getPlay1Phase().get().equalsIgnoreCase("GameOver")) {
						resetButtonActions();
						GamePlay.close();
					}
					
				}
			});
			GamePlay.nextPhase();
			
			//Bot 
			GamePlay.setPlay1Terri(p1.getNumberOfOwnedTerritories());
			GamePlay.setPlay2Terri(p2.getNumberOfOwnedTerritories());
			GamePlay.getPlay2Phase().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable) {
					//BOT MOVES
					PauseTransition ps = new PauseTransition();
					ps.setDuration(new Duration(2000));
					ps.playFromStart();
					ps.setOnFinished(e->{
						GamePlay.setPlay1Terri(p1.getNumberOfOwnedTerritories());
						GamePlay.setPlay2Terri(p2.getNumberOfOwnedTerritories());
						if(GamePlay.turn == 2 && !CheckWinState())
						{
							AI_Move(map, buttons, 2,GamePlay.getPlay2Phase().get(), 2);
						}
						
					});
					
				ps.play();
				}
			});
			
			
		}
		else { //2 Bots against each other , they take actions alternately
			PauseTransition ps = new PauseTransition();
			ps.setDuration(new Duration(gameSpeed * 1000));
			
			ps.playFromStart();
			ps.setOnFinished(e->{
				GamePlay.setPlay1Terri(p1.getNumberOfOwnedTerritories());
				GamePlay.setPlay2Terri(p2.getNumberOfOwnedTerritories());
				if(GamePlay.turn == 1) {
					AI_Move(map, buttons, 1,GamePlay.getPlay1Phase().get(), 1);
				}
				else if(GamePlay.turn == 2)
				{
					//System.out.println("Turn = "+GamePlay.turn+"  PHAAASE: "+GamePlay.getPlay2Phase().get());
					AI_Move(map, buttons, 2,GamePlay.getPlay2Phase().get(), 2);
				}
				//UPDATE_UI_MAP(map, buttons);
				//i++;
				if(!CheckWinState()) ps.playFromStart();
			});
			
		ps.play();
		}
		}
		
	}
	public static void UPDATE_UI_MAP(Territory[] map ,Button[] buttons) {
		for (Territory territory :map) {
			if (territory.getTroopsNumber()!=0 ) {
			
				if(territory.getOwner().getId() == 1 )//GREEN PLAYER
				
				{
					//buttons[territory.getID()-1].getStylesheets().clear();
					buttons[territory.getID()-1].getStylesheets().add("css/Green_Buttons.css");
					buttons[territory.getID()-1].setText(""+territory.getTroopsNumber());
					buttons[territory.getID()-1].setWrapText(true);
				}
			else if(territory.getOwner().getId() == 2 )//RED PLAYER
			{
				//buttons[territory.getID()-1].getStylesheets().clear();
				buttons[territory.getID()-1].getStylesheets().add("css/Red_Buttons.css");
				buttons[territory.getID()-1].setText(""+territory.getTroopsNumber());
				buttons[territory.getID()-1].setOnAction(e->{ 
				System.out.println("You don't Own me, go away !!!!");
			});
			}
				
			else {
				//buttons[territory.getID()-1].getStylesheets().clear();
				buttons[territory.getID()-1].getStylesheets().add("css/ShinyOrange.css");
				buttons[territory.getID()-1].setText(""+territory.getTroopsNumber());
				buttons[territory.getID()-1].setOnAction(e->{ 
					System.out.println("You don't Own me, go away !!!!");
				});
			}
				
//				System.out.print("territory ID->"+territory.getID());
//				System.out.print("  troops Number "+territory.getTroopsNumber());
//				if(territory.getOwner()!=null)
//				System.out.print("  owner id"+territory.getOwner().getId());
//				System.out.println("");
			}
	}
	}
	public static void AI_Move(Territory[] map ,Button[] buttons ,  int player  ,String PlayerPhase, int turn) {
		Player p = player == 1 ? controller.getFirstPlayer():controller.getSecPlayer();
		//String PlayerPhase = turn ==1?GamePlay.getPlay1Phase().get():GamePlay.getPlay2Phase().get();

//					String PlayerPhase = turn ==1 ? GamePlay.getPlay1Phase().get():GamePlay.getPlay2Phase().get();
					System.out.println("Turn:"+turn+" , PHAAAAAAAASE :  "+PlayerPhase);

					if(PlayerPhase.equalsIgnoreCase("Placing"))
					{
						try {
							controller.AddTroops(p);
							if(player == 1) GamePlay.setPlay1Troops(0);
							else 			GamePlay.setPlay2Troops(0);

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if(PlayerPhase.equalsIgnoreCase("Attacking")) {
						p.Attack();
						System.out.println("HOGOOOOOOOOOOOOOOOM");
						Player pl = player == 1 ? controller.getSecPlayer():controller.getFirstPlayer();
						int extra = pl.calculateExtraTroops();
						p.setExtraTroops(extra);
						if(player == 1)	GamePlay.setPlay2Troops(extra);
						else 			GamePlay.setPlay1Troops(extra);
						UPDATE_UI_MAP(map, buttons);
						GamePlay.setPlay1Terri(p1.getNumberOfOwnedTerritories());
						GamePlay.setPlay2Terri(p2.getNumberOfOwnedTerritories());
					}
					else if(PlayerPhase.equalsIgnoreCase("Idle")) {
						
						GamePlay.changeTurns();
					}
				UPDATE_UI_MAP(map, buttons);
				GamePlay.nextPhase();
	}
	
	private static void HumanPlacing() {
		System.out.println("Human's placing.");
		resetButtonActions();
			int extraTroops  = p1.calculateExtraTroops();
			p1.setExtraTroops(extraTroops);
			GamePlay.setPlay1Troops(extraTroops);
			//Set actions on button
			System.out.println("OwnedTerr while Placing: "+p1.getOwnedTerritories().size()+ " NUM:"+p1.getNumberOfOwnedTerritories());
			for(Territory territory :p1.getOwnedTerritories()) {
					buttons[territory.getID()-1].setDisable(false);
					buttons[territory.getID()-1].setOnAction(e->{
						PlaceTroops pt = new PlaceTroops(p1.getExtraTroops());//p1.getExtraTroops());
		    			Stage st = new Stage();
		    			try {
							pt.start(st);
//							if(!st.isShowing())
							int troops = pt.getNoTroops();
							IntegerProperty trps = new SimpleIntegerProperty(troops);trps.bind(pt.getTrs());
							trps.addListener(new InvalidationListener() {
								@Override
								public void invalidated(Observable observable) {
									int newTroops = Integer.parseInt(buttons[territory.getID()-1].getText())+pt.getTrs().get();
									buttons[territory.getID()-1].setText(""+newTroops);
									int TroopsLeft = p1.getExtraTroops() - pt.getTrs().get();
									p1.setExtraTroops(TroopsLeft);
									GamePlay.setPlay1Troops(TroopsLeft);
									territory.setTroopsNumber(newTroops);
								}
							});
							//System.out.println(trps.get());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					});
				
			}
	}
	private static void HumanAttack() { 
		//System.out.println("Before Attacking :"+p1.getOwnedTerritories().size()+ " NUM:"+p1.getNumberOfOwnedTerritories());
		System.out.println("Human's attacking");
		resetButtonActions();
		//2-Add new Actions
		for(Territory territory :p1.getOwnedTerritories()) {
				buttons[territory.getID()-1].setDisable(false);
				buttons[territory.getID()-1].setOnAction(e->{
					//Reset previous colored nodes
					for(int j=0 ;j<coloredTerri.size();j++) {
						if(coloredTerri.get(j).getOwner() == null)
						buttons[coloredTerri.get(j).getID()-1].getStylesheets().add("css/ShinyOrange.css");
						else if (coloredTerri.get(j).getOwner().getId() != 1)
							buttons[coloredTerri.get(j).getID()-1].getStylesheets().add("css/Red_Buttons.css");
					}
					coloredTerri.clear();
					//My neighbours
					if(map[territory.getID()].getTroopsNumber() > 1) // Contains enough troops to attack new region
					for(Territory neighbour : territory.getNeighbours()) {
						if(neighbour.getOwner() == null  || neighbour.getOwner().getId() != 1)
						{
							buttons[neighbour.getID()-1].getStylesheets().clear();
							buttons[neighbour.getID()-1].getStylesheets().add("css/Attacked.css");
							coloredTerri.add(neighbour);
							buttons[neighbour.getID()-1].setOnAction(e3->{
								//Chosen territory to attack on
								//Empty territory
								if(neighbour.getOwner() == null) {
									
									int extraTroops  = map[territory.getID()].getTroopsNumber()-1;
									PlaceTroops pt = new PlaceTroops(extraTroops);//p1.getExtraTroops());
					    			Stage st = new Stage();
					    			try {
										pt.start(st);
										int troops = pt.getNoTroops();
										IntegerProperty trps = new SimpleIntegerProperty(troops);trps.bind(pt.getTrs());
										trps.addListener(new InvalidationListener() {
											@Override
											public void invalidated(Observable observable) {
												int newTroops = pt.getTrs().get();
												if(newTroops > 0) {
												//map[neighbour.getID()].setOwner(p1);
												p1.addTerrritory(map[neighbour.getID()]);
												GamePlay.setPlay1Terri(p1.getNumberOfOwnedTerritories());
												neighbour.setOwner(p1);
												buttons[neighbour.getID()-1].setText(""+newTroops);
												neighbour.setTroopsNumber(newTroops);
												buttons[neighbour.getID()-1].getStylesheets().clear();
												buttons[neighbour.getID()-1].getStylesheets().add("css/Green_Buttons.css");
												int extraTroopsLeft = extraTroops - pt.getTrs().get();
												territory.setTroopsNumber(extraTroopsLeft+1);
												buttons[territory.getID()-1].setText(""+(extraTroopsLeft+1));
												buttons[neighbour.getID()-1].setOnAction(ee->{
													HumanAttack();
												});
												
												System.out.println("After Attacking and placing: "+p1.getOwnedTerritories().size()+ " NUM: "+p1.getNumberOfOwnedTerritories());
												//GamePlay.setPlay1Phase("Idle");GamePlay.setPlay1Phase("Attacking");
											}
												}
										});
										//System.out.println(trps.get());
									} catch (Exception e1) {
										e1.printStackTrace();
									}
								}
								else if(neighbour.getOwner().getId() == 2) { // Owned by other enemy so we roll the dice then ....
									Player p2 = controller.getSecPlayer();
									Dice dice = new Dice();
//									System.out.println("Attacker ID:"+territory.getOwner().getId() +" ,Defender ID:"+neighbour.getOwner().getId());
//									System.out.println("Attacker's territories : "+p1.getNumberOfOwnedTerritories());
//									System.out.println("Defender's territories : "+p2.getNumberOfOwnedTerritories());
									dice.rollDices(territory, neighbour);
//									System.out.println("After Rolling:");
//									System.out.println("Attacker ID:"+territory.getOwner().getId() +" ,Defender ID:"+neighbour.getOwner().getId());
//									System.out.println("Attacker's territories : "+p1.getNumberOfOwnedTerritories());
//									System.out.println("Defender's territories : "+p2.getNumberOfOwnedTerritories());
									//Attacker won
									if(territory.getOwner() == neighbour.getOwner()) {
										p2.getOwnedTerritories().remove(neighbour);
										territory.setTroopsNumber(territory.getTroopsNumber());// - neighbour.getTroopsNumber());
										buttons[territory.getID()-1].setText(""+(territory.getTroopsNumber()));// - neighbour.getTroopsNumber()));
										buttons[neighbour.getID()-1].setText(""+neighbour.getTroopsNumber());
										buttons[neighbour.getID()-1].getStylesheets().clear();
										buttons[neighbour.getID()-1].getStylesheets().add("css/Green_Buttons.css");
										buttons[neighbour.getID()-1].setOnAction(ee->{
											HumanAttack();
										});
										
									}
									else { //Defender Won
										buttons[territory.getID()-1].setText(""+territory.getTroopsNumber());
										buttons[neighbour.getID()-1].setText(""+neighbour.getTroopsNumber());
										buttons[neighbour.getID()-1].getStylesheets().clear();
										buttons[neighbour.getID()-1].getStylesheets().add("css/Red_Buttons.css");
									}
								}
//								resetButtonActions(buttons);
//								HumanAttack(buttons);
							});
					}
					}
					
				});
			
		}
	}
	private static void resetButtonActions() {
		Player p1 = controller.getFirstPlayer();
		//1-Reset button actions 
				for(int j =0;j<buttons.length;j++) {
						buttons[j].setOnAction(e->{});
				}
				UPDATE_UI_MAP(map, buttons);
				//Reset previous colored nodes
				for(int j=0 ;j<coloredTerri.size();j++) {
					if(coloredTerri.get(j).getOwner() == null)
					buttons[coloredTerri.get(j).getID()-1].getStylesheets().add("css/ShinyOrange.css");
					else if (coloredTerri.get(j).getOwner().getId() != 1)
						buttons[coloredTerri.get(j).getID()-1].getStylesheets().add("css/Red_Buttons.css");
				}
				coloredTerri.clear();
				//System.out.println("Still colored: "+coloredTerri.size());
	}
	public static void initializePlayers(String player1_Type , String player2_Type) {
		p1 = new Player();
		p2 = new Player();
		switch(player1_Type){
		case "Human":
			p1.setStrategy(new HumanType());
			p1.setPlayerType("human");
			break;
		case "Passive bot":
			p1.setStrategy(new PassiveAgent());
			p1.setPlayerType("passive");
					break;
		case "Medium bot":
			p1.setStrategy(new PacifistAgent());
			p1.setPlayerType("pacifist");
			break;
		case "Aggressive bot":
			p1.setStrategy(new AgressiveAgent());
			p1.setPlayerType("aggressive");
			break;				
		}
		switch(player2_Type){
		case "Greedy AI bot":
			p2.setStrategy(new GreedyAgent());
			p2.setPlayerType("greedy");
			break;
		case "A* AI bot":
			p2.setStrategy(new AstarAgent());
			p2.setPlayerType("astar");
					break;
		case "Real-time A* bot":
			p2.setStrategy(new AstarRealTimeAgent());
			p2.setPlayerType("RealtimeAstar");
			break;
		case "Minimax AI bot": // Lessa
			p2.setStrategy(new MinimaxAgent());
			p2.setPlayerType("minimax");
			break;				
		}

	}
	public static boolean CheckWinState() {
		if(p1.getNumberOfOwnedTerritories() == 50) return true;
		if (p2.getNumberOfOwnedTerritories() == 50) return true;
		else return false;
	}
	public static Player getWinner() {
		if(p1.getNumberOfOwnedTerritories() == 50) {
			winner = p1;
			return p1;
		}
		if (p2.getNumberOfOwnedTerritories() == 50) {
			winner = p2;
			return p2;
		}
		else return null;
	}
}
