package Algorithm;

import main.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
public class DepthStrategy implements SearchStrategy {
	Stack<Node> frontier;
	ArrayList<Node> explored;
	int counter=0;
	public DepthStrategy() {
		
		frontier=new Stack<>();
		explored=new ArrayList<Node>();
	}
	@Override
	public boolean Tree_search(Node root) {
		
		frontier.push(root);
		while(!frontier.isEmpty()){
			Node state =frontier.pop();

			System.out.print(counter+"    ");
		//	System.out.println(	Arrays.deepToString(state));
			System.out.println(	Arrays.deepToString(state.getCurrentState()));
			state.setNextStates();
			counter++;
			
			explored.add(state);
			
			if (state.checkGoalState()) {
				return true;
			}
			boolean foundInExploredList=false;
			boolean foundInFrontierList=false;
			for (Node neighbour : state.getNextStatesList()) {
				
				// check to found if the neighbours had been explored before or not 
				for (Node node : explored) {
					if(Arrays.deepEquals(node.getCurrentState(), neighbour.getCurrentState()))	
						{foundInExploredList=true;
						break;
						}
				}
				//check to found if the frontier
				Iterator<Node> frontierItrator=frontier.iterator();
				while(frontierItrator.hasNext()){
					Node temp=frontierItrator.next();
					if(Arrays.deepEquals(temp.getCurrentState(), neighbour.getCurrentState())){
						foundInFrontierList=true;
						break;
					}
					
				}

				if(!(foundInExploredList||foundInFrontierList)){
					frontier.push(neighbour);
				}
				
				
			}
			
		}
		
		
		
		
		
		
		
		return false;
	}

}
