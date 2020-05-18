package SearchStrategies;

import java.util.ArrayList;
import java.util.Iterator;

import Map.Territory;

public class PassiveAgent implements PlayingStrategy {

	@Override
	public void addTroops(int numberOfTroops,ArrayList<Territory> ownedTerritories) {
		Iterator<Territory> iterator=ownedTerritories.iterator();
		int minTroops=Integer.MAX_VALUE;
		int minId=-1;
		int i=0;
		
		while(iterator.hasNext()){
			Territory tempTerr=iterator.next();
			int troopsNumber=tempTerr.getTroopsNumber();
			if(minTroops>troopsNumber){
				minId=i;	
			}
			i++;
		}
		ownedTerritories.get(minId).setTroopsNumber(ownedTerritories.get(minId).getTroopsNumber()+numberOfTroops);
	
		
	}

	@Override
	public void attack(ArrayList<Territory> ownedTerritories) {
		
	}

}
