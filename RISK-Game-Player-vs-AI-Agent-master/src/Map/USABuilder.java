package Map;

import java.util.ArrayList;

public class USABuilder implements Builder {

    public static int NO_OF_TERRITORIES = 50;

    public USABuilder() {


	}


	@Override
	public void BuildMap(Territory[] map) {
		//Node 1
		map[1].addNeighbour(map[2]);
		map[1].addNeighbour(map[5]);
		map[1].addNeighbour(map[50]);

		// Node 2
		map[2].addNeighbour(map[1]);
		map[2].addNeighbour(map[3]);
		map[2].addNeighbour(map[4]);
		map[2].addNeighbour(map[5]);

		//Node 3
		map[3].addNeighbour(map[2]);
		map[3].addNeighbour(map[4]);
		map[3].addNeighbour(map[9]);
		map[3].addNeighbour(map[49]);


		//Node 4
		map[4].addNeighbour(map[2]);
		map[4].addNeighbour(map[3]);
		map[4].addNeighbour(map[5]);
		map[4].addNeighbour(map[8]);
		map[4].addNeighbour(map[9]);

		//Node 5
		map[5].addNeighbour(map[1]);
		map[5].addNeighbour(map[2]);
		map[5].addNeighbour(map[4]);
		map[5].addNeighbour(map[8]);
		map[5].addNeighbour(map[6]);
		map[5].addNeighbour(map[7]);

		//Node 6
		map[6].addNeighbour(map[5]);
		map[6].addNeighbour(map[7]);
		map[6].addNeighbour(map[16]);
		map[6].addNeighbour(map[17]);

		//Node 7
		map[7].addNeighbour(map[5]);
		map[7].addNeighbour(map[6]);
		map[7].addNeighbour(map[8]);
		map[7].addNeighbour(map[10]);
		map[7].addNeighbour(map[15]);
		map[7].addNeighbour(map[16]);
		map[7].addNeighbour(map[17]);

		//Node 8
		map[8].addNeighbour(map[5]);
		map[8].addNeighbour(map[4]);
		map[8].addNeighbour(map[7]);
		map[8].addNeighbour(map[9]);
		map[8].addNeighbour(map[10]);
		map[8].addNeighbour(map[11]);


		//Node 9
		map[9].addNeighbour(map[3]);
		map[9].addNeighbour(map[4]);
		map[9].addNeighbour(map[8]);
		map[9].addNeighbour(map[10]);
		map[9].addNeighbour(map[11]);
		map[9].addNeighbour(map[49]);

		//Node 10
		map[10].addNeighbour(map[7]);
		map[10].addNeighbour(map[8]);
		map[10].addNeighbour(map[9]);
		map[10].addNeighbour(map[11]);
		map[10].addNeighbour(map[13]);
		map[10].addNeighbour(map[14]);
		map[10].addNeighbour(map[15]);

		//Node 11
		map[11].addNeighbour(map[8]);
		map[11].addNeighbour(map[9]);
		map[11].addNeighbour(map[10]);
		map[11].addNeighbour(map[13]);
		map[11].addNeighbour(map[12]);

		//Node 12
		map[12].addNeighbour(map[11]);
		map[12].addNeighbour(map[13]);
		map[12].addNeighbour(map[21]);
		map[12].addNeighbour(map[22]);

		//Node 13
		map[13].addNeighbour(map[10]);
		map[13].addNeighbour(map[11]);
		map[13].addNeighbour(map[12]);
		map[13].addNeighbour(map[14]);
		map[13].addNeighbour(map[20]);
		map[13].addNeighbour(map[21]);

		//Node 14
		map[14].addNeighbour(map[10]);
		map[14].addNeighbour(map[13]);
		map[14].addNeighbour(map[15]);
		map[14].addNeighbour(map[20]);

		//Node 15
		map[15].addNeighbour(map[7]);
		map[15].addNeighbour(map[10]);
		map[15].addNeighbour(map[14]);
		map[15].addNeighbour(map[16]);
		map[15].addNeighbour(map[19]);
		map[15].addNeighbour(map[20]);


		//Node 16

		map[16].addNeighbour(map[6]);
		map[16].addNeighbour(map[7]);
		map[16].addNeighbour(map[15]);
		map[16].addNeighbour(map[17]);
		map[16].addNeighbour(map[18]);
		map[16].addNeighbour(map[19]);


		//Node 17
		map[17].addNeighbour(map[6]);
		map[17].addNeighbour(map[16]);
		map[17].addNeighbour(map[18]);



		//Node 18
		map[18].addNeighbour(map[17]);
		map[18].addNeighbour(map[16]);
		map[18].addNeighbour(map[19]);
		map[18].addNeighbour(map[34]);
		map[18].addNeighbour(map[36]);

		//Node 19
		map[19].addNeighbour(map[15]);
		map[19].addNeighbour(map[16]);
		map[19].addNeighbour(map[18]);
		map[19].addNeighbour(map[20]);
		map[19].addNeighbour(map[33]);
		map[19].addNeighbour(map[34]);

		//Node 20
		map[20].addNeighbour(map[13]);
		map[20].addNeighbour(map[14]);
		map[20].addNeighbour(map[15]);
		map[20].addNeighbour(map[19]);
		map[20].addNeighbour(map[21]);
		map[20].addNeighbour(map[31]);
		map[20].addNeighbour(map[32]);
		map[20].addNeighbour(map[33]);

		//Node 21
		map[21].addNeighbour(map[12]);
		map[21].addNeighbour(map[13]);
		map[21].addNeighbour(map[20]);
		map[21].addNeighbour(map[22]);
		map[21].addNeighbour(map[23]);
		map[21].addNeighbour(map[31]);

		//Node 22
		map[22].addNeighbour(map[12]);
		map[22].addNeighbour(map[21]);
		map[22].addNeighbour(map[23]);

		//Node 23
		map[23].addNeighbour(map[21]);
		map[23].addNeighbour(map[22]);
		map[23].addNeighbour(map[24]);
		map[23].addNeighbour(map[31]);

		//Node 24
		map[24].addNeighbour(map[23]);
		map[24].addNeighbour(map[25]);
		map[24].addNeighbour(map[26]);
		map[24].addNeighbour(map[31]);

		//Node 25
		map[25].addNeighbour(map[24]);
		map[25].addNeighbour(map[26]);

		//Node 26
		map[26].addNeighbour(map[25]);
		map[26].addNeighbour(map[24]);
		map[26].addNeighbour(map[31]);
		map[26].addNeighbour(map[27]);
		map[26].addNeighbour(map[28]);

		//Node 27
		map[27].addNeighbour(map[26]);
		map[27].addNeighbour(map[28]);

		//Node 28
		map[28].addNeighbour(map[27]);
		map[28].addNeighbour(map[29]);
		map[28].addNeighbour(map[31]);

		//Node 29
		map[29].addNeighbour(map[28]);
		map[29].addNeighbour(map[31]);
		map[29].addNeighbour(map[32]);
		map[29].addNeighbour(map[30]);
		map[29].addNeighbour(map[48]);

		//Node 30
		map[30].addNeighbour(map[29]);
		map[30].addNeighbour(map[32]);
		map[30].addNeighbour(map[37]);
		map[30].addNeighbour(map[38]);
		map[30].addNeighbour(map[48]);

		//Node 31
		map[31].addNeighbour(map[20]);
		map[31].addNeighbour(map[21]);
		map[31].addNeighbour(map[23]);
		map[31].addNeighbour(map[24]);
		map[31].addNeighbour(map[26]);
		map[31].addNeighbour(map[28]);
		map[31].addNeighbour(map[29]);
		map[31].addNeighbour(map[32]);

		//Node 32
		map[32].addNeighbour(map[20]);
		map[32].addNeighbour(map[29]);
		map[32].addNeighbour(map[30]);
		map[32].addNeighbour(map[31]);
		map[32].addNeighbour(map[33]);
		map[32].addNeighbour(map[35]);
		map[32].addNeighbour(map[37]);


		//Node 33
		map[33].addNeighbour(map[19]);
		map[33].addNeighbour(map[20]);
		map[33].addNeighbour(map[32]);
		map[33].addNeighbour(map[34]);
		map[33].addNeighbour(map[35]);


		//Node 34
		map[34].addNeighbour(map[18]);
		map[34].addNeighbour(map[19]);
		map[34].addNeighbour(map[33]);
		map[34].addNeighbour(map[36]);


		//Node 35
		map[35].addNeighbour(map[32]);
		map[35].addNeighbour(map[33]);
		map[35].addNeighbour(map[36]);
		map[35].addNeighbour(map[37]);


		//Node 36
		map[36].addNeighbour(map[18]);
		map[36].addNeighbour(map[34]);
		map[36].addNeighbour(map[35]);
		map[36].addNeighbour(map[37]);

		//Node 37
		map[37].addNeighbour(map[30]);
		map[37].addNeighbour(map[32]);
		map[37].addNeighbour(map[35]);
		map[37].addNeighbour(map[36]);
		map[37].addNeighbour(map[38]);

		//Node 38
		map[38].addNeighbour(map[30]);
		map[38].addNeighbour(map[37]);
		map[38].addNeighbour(map[39]);
		map[38].addNeighbour(map[46]);
		map[38].addNeighbour(map[47]);
		map[38].addNeighbour(map[48]);

		//Node 39
		map[39].addNeighbour(map[38]);
		map[39].addNeighbour(map[40]);
		map[39].addNeighbour(map[43]);
		map[39].addNeighbour(map[45]);
		map[39].addNeighbour(map[46]);

		//Node 40
		map[40].addNeighbour(map[39]);
		map[40].addNeighbour(map[41]);
		map[40].addNeighbour(map[43]);

		//Node 41
		map[41].addNeighbour(map[40]);
		map[41].addNeighbour(map[42]);
		map[41].addNeighbour(map[43]);

		//Node 42
		map[42].addNeighbour(map[41]);

		//Node 43
		map[43].addNeighbour(map[39]);
		map[43].addNeighbour(map[40]);
		map[43].addNeighbour(map[41]);
		map[43].addNeighbour(map[44]);
		map[43].addNeighbour(map[45]);

		//Node 44
		map[44].addNeighbour(map[43]);
		map[44].addNeighbour(map[45]);
		map[44].addNeighbour(map[46]);

		//Node 45
		map[45].addNeighbour(map[39]);
		map[45].addNeighbour(map[43]);
		map[45].addNeighbour(map[44]);
		map[45].addNeighbour(map[46]);

		//Node 46
		map[46].addNeighbour(map[38]);
		map[46].addNeighbour(map[39]);
		map[46].addNeighbour(map[44]);
		map[46].addNeighbour(map[45]);
		map[46].addNeighbour(map[47]);

		//Node 47
		map[47].addNeighbour(map[38]);
		map[47].addNeighbour(map[46]);
		map[47].addNeighbour(map[48]);

		//Node 48

		map[48].addNeighbour(map[29]);
		map[48].addNeighbour(map[30]);
		map[48].addNeighbour(map[38]);
		map[48].addNeighbour(map[47]);

		//Node 49
		map[49].addNeighbour(map[3]);
		map[49].addNeighbour(map[9]);

		//Node 50
		map[50].addNeighbour(map[1]);

		


















	}

}
