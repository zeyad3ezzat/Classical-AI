package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import main.Node;

public class AStrategy implements SearchStrategy {
	ArrayList<Node> explored;
	PriorityQueue<Node> frontier;
	
	public AStrategy() {
		super();
		explored=new ArrayList<>();
        Comparator<Node> heursticsPlusCostComparator = new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
            	int costPlusHeurstics1=n1.getCostToReach()+n1.getHeuristics();
            	int costPlusHeurstics2=n2.getCostToReach()+n2.getHeuristics();

//            	System.out.println("Node 1 =" +Arrays.deepToString(n1.getCurrentState()));
//            	System.out.println("cost plus heurstics 1 =" +costPlusHeurstics1);
//            	System.out.println("Node 2 =" +Arrays.deepToString(n2.getCurrentState()));
//            	System.out.println("cost plus heurstics 2 =" +costPlusHeurstics2);
                return costPlusHeurstics1 - costPlusHeurstics2;
            }
        };

		frontier=new PriorityQueue<>(heursticsPlusCostComparator);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean Tree_search(Node root){
		Node state=null;
		frontier.add(root);
		while(!frontier.isEmpty()){
			state=frontier.poll();
			explored.add(state);
			state.setNextStates();
			state.setCost();
			System.out.println(Arrays.deepToString(state.getCurrentState()));
			
			if(state.checkGoalState()){
				return true;
			}
			boolean foundInExplored;
			boolean foundInFrontier;
			boolean foundWithSmallerCost;
			for (Node neighbour : state.getNextStatesList()) {
				

					 foundInExplored=false;
					 foundInFrontier=false;
					 foundWithSmallerCost=false;
					Iterator<Node> exploredIterator=explored.iterator();
					Iterator<Node> frontierIterator=frontier.iterator();
					while(exploredIterator.hasNext()){
						Node temp=exploredIterator.next();
						if(Arrays.deepEquals(temp.getCurrentState(), neighbour.getCurrentState())){
							foundInExplored=true;
							break;
						}
					}
					while(frontierIterator.hasNext()){
						Node temp=frontierIterator.next();
						if(Arrays.deepEquals(temp.getCurrentState(), neighbour.getCurrentState())){
							foundInFrontier=true;
							if(temp.getCostToReach()<neighbour.getCostToReach()){
								//we will compare just the cost not the key because the heurstics is constant  
								// but the cost could be different from state to another state with the same 2dArray values
								//System.out.println("found with heigher key");
								foundWithSmallerCost=true;
								frontierIterator.remove();
								
							}
							break;
						}else{
							foundInFrontier=false;
							
							
						}
					}
					if (!(foundInExplored||foundInFrontier)) {
						frontier.add(neighbour);
							}
					if(foundWithSmallerCost){
						frontier.add(neighbour);
					}
				
			}
			
			
		}
		
		
		
		
		return false;
	}
	public boolean draft(Node root) {
		int[][] c = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
		int[][] d = {{1, 0, 2}, {3, 4, 5}, {6, 7, 8}};
		int[][] e = {{1, 0, 2}, {3, 4, 5}, {6, 7, 8}};
//		int[][] e = {{1, 2, 0}, {3, 4, 5}, {6, 7, 8}};
		int[][] f = { {3, 4, 5},{0, 1, 2}, {6, 7, 8}};

	//	int[][] e = {{1, 3, 4}, {7, 2, 5}, {0, 8, 6}};
		

		Node nodeD =new Node(d);
		Node nodeE =new Node(d);
//		nodeD.calcHeurstics();
		nodeD.setNextStates();
		nodeE.setNextStates();
		
		frontier.add(nodeD);
		//Iterator<Node> it=frontier.iterator();
		
		for (Node neighbour : nodeD.getNextStatesList()) {
			neighbour.calcHeurstics();
			neighbour.setCost();
			frontier.add(neighbour);
			
		}
		Iterator<Node> iterator=frontier.iterator();
		
		Node temp=null;
		while (iterator.hasNext()) {
			temp=iterator.next();
			if(Arrays.deepEquals(temp.getCurrentState(),nodeE.getCurrentState())){
				System.out.println("found");
				iterator.remove();
				break;
				
			}
			
		}
		//frontier.remove(temp);
		while(!frontier.isEmpty()){
			System.out.println(Arrays.deepToString(frontier.poll().getCurrentState()));
		}
		//System.out.println(		frontier.toString());


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return false;
	}

}
