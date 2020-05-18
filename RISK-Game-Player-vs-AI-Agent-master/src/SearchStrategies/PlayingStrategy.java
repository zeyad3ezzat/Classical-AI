package SearchStrategies;

import java.util.ArrayList;

import Map.Territory;

public interface PlayingStrategy {
	
	public void addTroops(int numberOfTroops,ArrayList<Territory> ownedTerritories);
	public void attack(ArrayList<Territory> ownedTerritories);
	

}
