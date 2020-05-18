package Map;

import java.util.ArrayList;

public class EgyptBuilder implements Builder {

    public static int NO_OF_TERRITORIES=27;
	

	//we used +1 because we didn't use the first place 
	
    //Territory[] map ;
    
    // initializing 

	
	
	@Override
	public void BuildMap(Territory[] map) {
		//Node 1
		map[1].addNeighbour(map[2]);
		map[1].addNeighbour(map[3]);
		map[1].addNeighbour(map[14]);
		map[1].addNeighbour(map[21]);

		
		
		// Node 2
		map[2].addNeighbour(map[1]);
		map[2].addNeighbour(map[3]);
		
		//Node 3
		map[3].addNeighbour(map[2]);
		map[3].addNeighbour(map[4]);
		map[3].addNeighbour(map[9]);
		map[3].addNeighbour(map[10]);
		map[3].addNeighbour(map[14]);
		
		//Node 4
		map[4].addNeighbour(map[5]);
		map[4].addNeighbour(map[3]);
		map[4].addNeighbour(map[9]);
		
		//Node 5
		map[5].addNeighbour(map[4]);
		map[5].addNeighbour(map[6]);
		map[5].addNeighbour(map[9]);
		map[5].addNeighbour(map[12]);
		map[5].addNeighbour(map[10]);
		map[5].addNeighbour(map[11]);
		
		//Node 6
		map[6].addNeighbour(map[5]);
		
		//Node 7
		map[7].addNeighbour(map[8]);
		map[7].addNeighbour(map[13]);
		
		
		//Node 8
		map[8].addNeighbour(map[13]);
		map[8].addNeighbour(map[17]);
		map[8].addNeighbour(map[18]);
		
		//Node 9
		map[9].addNeighbour(map[5]);
		map[9].addNeighbour(map[3]);
		map[9].addNeighbour(map[4]);
		map[9].addNeighbour(map[10]);
		map[9].addNeighbour(map[12]);
		
		//Node 10
		map[10].addNeighbour(map[9]);
		map[10].addNeighbour(map[3]);
		map[10].addNeighbour(map[11]);
		map[10].addNeighbour(map[14]);
		map[10].addNeighbour(map[12]);
		map[10].addNeighbour(map[5]);
		
		//Node 11
		map[11].addNeighbour(map[5]);
		map[11].addNeighbour(map[12]);
		map[11].addNeighbour(map[16]);
		map[11].addNeighbour(map[14]);
		map[11].addNeighbour(map[10]);
		
		//Node 12
		map[12].addNeighbour(map[5]);
		map[12].addNeighbour(map[11]);
		map[12].addNeighbour(map[13]);
		map[12].addNeighbour(map[16]);
		map[12].addNeighbour(map[17]);
		
		//Node 13
		map[13].addNeighbour(map[8]);
		map[13].addNeighbour(map[12]);
		map[13].addNeighbour(map[17]);

		
		//Node 14
		map[14].addNeighbour(map[1]);
		map[14].addNeighbour(map[3]);
		map[14].addNeighbour(map[10]);
		map[14].addNeighbour(map[11]);
		map[14].addNeighbour(map[16]);
		map[14].addNeighbour(map[15]);
		map[14].addNeighbour(map[19]);
		map[14].addNeighbour(map[20]);
		map[14].addNeighbour(map[21]);
		
		
		//Node 15
		map[15].addNeighbour(map[14]);
		map[15].addNeighbour(map[19]);
		
		//Node 16
		map[16].addNeighbour(map[12]);
		map[16].addNeighbour(map[14]);
		map[16].addNeighbour(map[11]);
		map[16].addNeighbour(map[17]);
		map[16].addNeighbour(map[19]);
		map[16].addNeighbour(map[23]);
		
		
		//Node 17
		map[17].addNeighbour(map[13]);
		map[17].addNeighbour(map[8]);
		map[17].addNeighbour(map[18]);
		map[17].addNeighbour(map[16]);
		map[17].addNeighbour(map[23]);
		
		
		//Node 18
		map[18].addNeighbour(map[8]);
		
		//Node 19
		map[19].addNeighbour(map[15]);
		map[19].addNeighbour(map[16]);
		map[19].addNeighbour(map[23]);
		map[19].addNeighbour(map[20]);
		
		//Node 20
		
		map[20].addNeighbour(map[23]);
		map[20].addNeighbour(map[19]);
		map[20].addNeighbour(map[22]);
		map[20].addNeighbour(map[14]);
		map[20].addNeighbour(map[21]);
		
		
		//Node 21
		map[21].addNeighbour(map[14]);
		map[21].addNeighbour(map[19]);
		map[21].addNeighbour(map[20]);
		map[21].addNeighbour(map[22]);
		map[21].addNeighbour(map[24]);
		map[21].addNeighbour(map[25]);
		map[21].addNeighbour(map[26]);
		map[21].addNeighbour(map[27]);
		
		//Node 22
		map[22].addNeighbour(map[20]);
		map[22].addNeighbour(map[21]);
		map[22].addNeighbour(map[23]);
		map[22].addNeighbour(map[24]);
		
		
		
		
		//Node 23
		map[23].addNeighbour(map[16]);
		map[23].addNeighbour(map[17]);
		map[23].addNeighbour(map[18]);
		map[23].addNeighbour(map[19]);
		map[23].addNeighbour(map[20]);
		map[23].addNeighbour(map[22]);
		map[23].addNeighbour(map[24]);
		map[23].addNeighbour(map[25]);
		map[23].addNeighbour(map[26]);
		map[23].addNeighbour(map[27]);
		
		//Node 24
		map[24].addNeighbour(map[21]);
		map[24].addNeighbour(map[22]);
		map[24].addNeighbour(map[23]);
		map[24].addNeighbour(map[25]);
		
		
		
		//Node 25
		map[25].addNeighbour(map[21]);
		map[25].addNeighbour(map[23]);
		map[25].addNeighbour(map[24]);
		map[25].addNeighbour(map[26]);
		
		
		
		
		//Node 26
		map[26].addNeighbour(map[25]);
		
		
		//Node 27
		map[27].addNeighbour(map[26]);
		map[27].addNeighbour(map[21]);
		map[27].addNeighbour(map[23]);

		/*
			for (Territory territory : map) {
				ArrayList<Territory>n=territory.getNeighbours();
				System.out.print(territory.getID()+"---->  ");
				for (Territory territory2 : n) {
					System.out.print(territory2.getID()+"  ");
				}
			System.out.println();
			}
			
		
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
