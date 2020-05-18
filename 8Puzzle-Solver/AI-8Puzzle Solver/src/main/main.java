package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Algorithm.DepthStategy;
import Algorithm.SearchStrategy;

public class main {

	public static void main(String[] args) {
				int[][] a = {
						{1, 8, 2}, {0, 4, 3}, {7, 6, 5}
					};
				
				long startTime = System.currentTimeMillis();
				SearchStrategy search=new DepthStategy();
				Node nodeA=new Node(a);
				//Node nodeB=new Node(b);
				
				
				if(isSolvable(a)){
					search.Tree_search(nodeA);
					System.out.println("true");
				}
				else {
					System.out.println("FAAAALSE");
				}
				
				long endTime = System.currentTimeMillis();
				long duration = (endTime - startTime);
				System.out.println("Taken time: "+duration+" m.seconds");
//				ArrayList<Node> list=new ArrayList<>();
//				list.add(nodeB);
//				for (Node node : list) {
//					if(Arrays.deepEquals(node.currentState, a))	
//						System.out.println("true");
//				}
//				


				

	}
	//Checks if the Puzzle if solvable
		public static boolean isSolvable(int[][] matrix) {
			int count = 0;
			List<Integer> array = new ArrayList<Integer>();
			
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					array.add(matrix[i][j]);
				}
			}
			//Take a copy
			Integer[] anotherArray = new Integer[array.size()];
			array.toArray(anotherArray);
			for (int i = 0; i < anotherArray.length - 1; i++) {
				for (int j = i + 1; j < anotherArray.length; j++) {
					if (anotherArray[i] != 0 && anotherArray[j] != 0 && anotherArray[i] > anotherArray[j]) {
						count++;
					}
				}
			}
			return count % 2 == 0;
		}
	

}
