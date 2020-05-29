package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import main.Node;

public class BreadthStrategy implements SearchStrategy  {
	Queue<Node> frontier;
	ArrayList<Node> explored;
	int counter =0;
	public BreadthStrategy() {
		frontier=new LinkedList<Node>();
		explored=new ArrayList<Node>();
	}
	@Override
	public boolean Tree_search(Node root) {
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node state=frontier.poll();
			System.out.println(counter+"    ");
			counter++;
			System.out.println(Arrays.deepToString(state.getCurrentState()));
			if(state.checkGoalState()){
				return true;
			}
			boolean foundInExplored=false;
			boolean foundInFrontier=false;
					state.setNextStates();
				for (Node neighbour : state.getNextStatesList()) {

					 foundInExplored=false;
					 foundInFrontier=false;
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
							break;
						}
					}
					if (!(foundInExplored||foundInFrontier)) {
						frontier.add(neighbour);
							}
				}
		}

			
		
		return false;
	}

}
