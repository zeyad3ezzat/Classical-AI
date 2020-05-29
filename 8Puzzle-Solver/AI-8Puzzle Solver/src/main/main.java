package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

import Algorithm.AStrategy;
import Algorithm.BreadthStrategy;
import Algorithm.DepthStrategy;
import Algorithm.SearchStrategy;

public class main {

	public static void main(String[] args) {
		//int[][] a = {{1, 8, 2}, {7, 4, 3}, {0, 6, 5}};
	//	int[][] a = {{1, 8, 2}, {0, 4, 3}, {7, 6, 5}};
		int[][] a = {{1, 0, 3}, {2, 4, 5}, {6, 7, 8}};

		int[][] b = {{1, 8, 2}, {0, 4, 3}, {7, 6, 5}};

//		int[][] a = {{1, 3, 4}, {0, 2, 5}, {7, 8, 6}};
//		int[][] b = {{1, 3, 4}, {2, 0, 5}, {7, 8, 6}};
		int[][] c = {{1, 3, 0}, {4, 2, 5}, {7, 8, 6}};
		int[][] d = {{1, 3, 4}, {7, 2, 5}, {0, 8, 6}};
		int[][] e = {{1, 3, 4}, {0, 2, 5}, {7, 8, 6}};
			Node nodeA =new Node(a);
//			Node nodeB =new Node(a);
			Node nodeC =new Node(c);
			Node nodeD =new Node(d);
			Node nodeE =new Node(e);
			
				SearchStrategy search=new AStrategy();
//				SearchStrategy search=new BreadthStrategy();
		//		SearchStrategy search=new DepthStrategy();
				
				if(search.Tree_search(nodeC)){
					System.out.println("true");
				}else{
					System.out.println("false");
				}

				
				


				

	}
	

}
