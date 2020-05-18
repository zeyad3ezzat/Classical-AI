package GameBoard;

import java.util.ArrayList;
import java.util.Random;

import Map.Builder;
import Map.EgyptBuilder;
import Map.Territory;
import Map.USABuilder;
import SearchStrategies.PlayingStrategy;

public class GameController implements Runnable{
	/* notes
	 * we must initialize the players in the main and add it 
	 * to the game controller and the UI 
	 * and i think the map as well but i'm not sure now 
	 * so we could control the players from the different classes
	 */
	private Player firstPlayer;

	private Player secPlayer;

	private int turn =1;
	public static int NUMBER_OF_TERRITORIES_IN_THE_MAP;
	public static int FIRST_PLAYER_ID=1;
	public static int SEC_PLAYER_ID=2;
	public static int EGYPT=1;
	public static int USA=2;
	private Territory[]map;
	static int NUMBER_OF_TROOPS_AT_BEGINING=20;
	static int NUMBER_OF_TERRITORIES_AT_BEGINING = 10;
	private Builder egyptBuilder;
	private Builder usaBuilder;
	
	Random rand;
	public GameController(){
		firstPlayer = new Player();
		secPlayer = new Player();
		rand=new Random();
		egyptBuilder=new EgyptBuilder();
		usaBuilder=new USABuilder();
		
	}
	public Player getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public Player getSecPlayer() {
		return secPlayer;
	}
	public void setSecPlayer(Player secPlayer) {
		this.secPlayer = secPlayer;
	}
	public void setFirstPlayerStrategy(PlayingStrategy s){
		//this.firstPlayer=new Player();
		this.firstPlayer.setStrategy(s);
		this.firstPlayer.setId(FIRST_PLAYER_ID);

		 int n = rand.nextInt(2) + 1;
		this.firstPlayer.setTurn(n);
	/*	System.out.println(firstPlayer.getNumberOfOwnedTerritories());
		System.out.println(firstPlayer.getPlayerState());
		System.out.println(firstPlayer.getTurn());*/
	}
	public void setSecondPlayerStrategy(PlayingStrategy s){
		//this.secPlayer=new Player();
		this.secPlayer.setStrategy(s);
		this.secPlayer.setId(SEC_PLAYER_ID);
		
		if (firstPlayer.getTurn()==1) {
			secPlayer.setTurn(2);
		}else{
			secPlayer.setTurn(1);
		}
	/*	System.out.println(secPlayer.getNumberOfOwnedTerritories());
		System.out.println(secPlayer.getPlayerState());
		System.out.println(secPlayer.getTurn());
		*/
	}
	public Territory[] setMap(int mapNumber){
		if(mapNumber==EGYPT){
			NUMBER_OF_TERRITORIES_IN_THE_MAP=EgyptBuilder.NO_OF_TERRITORIES;

			map=new Territory[NUMBER_OF_TERRITORIES_IN_THE_MAP+1];
			for(int i=0;i<NUMBER_OF_TERRITORIES_IN_THE_MAP+1;i++){
				map[i]=new Territory();
				 map[i].setID(i);

			}
			egyptBuilder.BuildMap(map);
			
		}else if(mapNumber==USA){
			NUMBER_OF_TERRITORIES_IN_THE_MAP=USABuilder.NO_OF_TERRITORIES;

			map=new Territory[NUMBER_OF_TERRITORIES_IN_THE_MAP+1];
			for(int i=0;i<NUMBER_OF_TERRITORIES_IN_THE_MAP+1;i++){
				map[i]=new Territory();
				 map[i].setID(i);

			}

			usaBuilder.BuildMap(map);
			
		}
		int i=0;
		
		Player.setMap(map);
		return map;
	}
	public void initializeTheGameBoard(int mapType){
		if(firstPlayer.getStrategy()==null||secPlayer.getStrategy()==null||map.length==0){
			System.out.println("no first/second player strategy OR no map ");
			return ;
		}
		int i=0;
		int maxTroops=NUMBER_OF_TROOPS_AT_BEGINING;
		//setting the initial state randomly for the first player
		while(i<NUMBER_OF_TERRITORIES_AT_BEGINING && maxTroops>0){
			
		int randIndexOfTerritory = mapType == 2? (rand.nextInt(50) + 1) : (rand.nextInt(27) + 1);
		
		firstPlayer.addTerrritory(map[randIndexOfTerritory]);

		int randNumberOfTroops = rand.nextInt(maxTroops) + 1;

		map[randIndexOfTerritory].setTroopsNumber(map[randIndexOfTerritory].getTroopsNumber()+randNumberOfTroops);
		maxTroops -= randNumberOfTroops;
		i++;
		}
		i=0;
		//sumOfTroops=0;
		maxTroops=NUMBER_OF_TROOPS_AT_BEGINING;
		//setting the initial state randomly for the second player
		while(i<NUMBER_OF_TERRITORIES_AT_BEGINING && maxTroops>0){
			
			int randIndexOfTerritory = mapType == 2? (rand.nextInt(50) + 1) : (rand.nextInt(27) + 1);
			
			if(map[randIndexOfTerritory].getOwner()!=null)
				continue;
			boolean isTerritoryAdded =	secPlayer.addTerrritory(map[randIndexOfTerritory]);
			int randNumberOfTroops = rand.nextInt(maxTroops) + 1;
			map[randIndexOfTerritory].setTroopsNumber(map[randIndexOfTerritory].getTroopsNumber()+randNumberOfTroops);
			maxTroops-=randNumberOfTroops;
			i++;
		}

		int numberOfExtraTroops=0;
		
	}
	/* i don't know how to decide how we should add the troops into the territory 
	 * one by one or all in the same territory 
	 * to be discussed later 3shan na mkasel 
	 * Start turn function needs more work to be done later 
	*/
	
	public void AddTroops(Player currentPlayer) throws InterruptedException{
//		int territoriesCount=currentPlayer.getNumberOfOwnedTerritories();
//		int extraTroops=territoriesCount/3;
//		if(extraTroops<3){
//			extraTroops=3;
//		}
		int extra = currentPlayer.calculateExtraTroops();
		currentPlayer.addTroops(extra);
		
	
	}
	public void Attack(Player currentPlayer){
		currentPlayer.Attack();
	
	}
	private int getNextTurn(Player currentplayer) {
		int playerState=currentplayer.getPlayerState();
		if(playerState==Player.ATTACKING){
			return turn==1?1:2;
		}else if (playerState==Player.NOT_PLAYING){
			return turn==1?2:1;
		}
		System.out.println("error from check next turn function");
		return 0;
		
	}
	public int RollDice(){
		return 1;
		
	}
	@Override
	public void run() {
		//Change turns
		
		
	}
	
	

}
