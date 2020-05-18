package GameBoard;

import java.util.ArrayList;

import Map.Territory;
import SearchStrategies.PlayingStrategy;

 public class Player {
	private int id;
	private int turn;
	public static int NOT_PLAYING=0;
	public static int ADDING_TROOPS=1;
	public static int ATTACKING=2;

	private int playerState;
	private int extraTroops = 0 ;

	private PlayingStrategy strategy;
	private String playerType = "";
	
	private static Territory map[];

	private ArrayList<Territory> ownedTerritories;
	
	private int numberOfOwnedTerritories;
	 public Player() {
		 ownedTerritories=new ArrayList<>();
		 numberOfOwnedTerritories=0;
		 setPlayerState(NOT_PLAYING);
	}
	public ArrayList<Territory> getOwnedTerritories() {
			return ownedTerritories;
		}
	public void setOwnedTerritories(ArrayList<Territory> ownedTerritories) {
			this.ownedTerritories = ownedTerritories;
		}
	public static Territory[] getMap() {
		return map;
	}
	public static void setMap(Territory[] map) {
		Player.map = map;
	}
	public int getTurn() {
		return turn;
	}
	public int getNumberOfOwnedTerritories() {
		return numberOfOwnedTerritories;
	}
	public void setNumberOfOwnedTerritories(int numberOfOwnedTerritories) {
		this.numberOfOwnedTerritories = numberOfOwnedTerritories;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public PlayingStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(PlayingStrategy strategy) {
		this.strategy = strategy;
	}

	public void addTroops(int ExtraTroops){
		 this.strategy.addTroops(ExtraTroops, ownedTerritories);
	}
	public int calculateExtraTroops() {
		int territoriesCount=this.getNumberOfOwnedTerritories();
		int extraTroops=territoriesCount/3;
		if(extraTroops<3){
			extraTroops=3;
		}
		return extraTroops;
	}
	public void Attack(){
		this.strategy.attack( ownedTerritories);
	}

	public boolean addTerrritory(Territory newTerritory){
		//System.out.println(new);
		if(!ownedTerritories.contains(newTerritory)){
			ownedTerritories.add(newTerritory);
			numberOfOwnedTerritories++;
			newTerritory.setOwner(this);
			return true;
		}else{
			System.out.println("territory "+newTerritory.getID()+" is already owned by "+this.id);
			return false;
		}
		
	}
	public boolean removeTerritory(Territory territory){
		//System.out.println(new);
		if(ownedTerritories.contains(territory)){
			if(ownedTerritories.remove(territory)) {
				System.out.println("Territory removed.");
			}
			else System.out.println("Error removing territory");
			numberOfOwnedTerritories--;
			//territory.setOwner(null);
			return true;
		}else{
			System.out.println("territory :"+territory.getID()+" is not owned by this player :  "+this.id);
			return false;
		}
		
	}
	

	public int getExtraTroops() {
		return extraTroops;
	}
	public void setExtraTroops(int extraTroops) {
		this.extraTroops = extraTroops;
	}
	
	public int getId() {
	return id;
	}

	public void setId(int id) {
	this.id = id;
	}
	
	public String getPlayerType() {
		return playerType;
	}
	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}
	public int getPlayerState() {
		return playerState;
	}
	public void setPlayerState(int playerState) {
		this.playerState = playerState;
	}
	
	
}
