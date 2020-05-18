package Algorithm;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

import main.Node;

public class DepthStategy implements SearchStrategy {
	Stack<Node> frontier;
	ArrayList<Node> explored;
	int counter=0;
	public DepthStategy() {
		super();
		frontier=new Stack<>();
		explored=new ArrayList<Node>();
	}
	@Override
	public boolean Tree_search(Node root) {
		
		frontier.push(root);
		int i=0;
		while(counter<=10000 && !frontier.isEmpty()) {//|| {i!=9 )
			System.out.println(counter++);
			Node state = frontier.pop();
			//System.out.println(	Arrays.deepToString(state.getCurrentState()));
			state.printMatrix(state.getCurrentState());
			state.setNextStates();
			
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
						{
						foundInExploredList=true;
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
				if(!foundInExploredList && !foundInFrontierList){
					frontier.push(neighbour);
				}	
			}
			i++;
			
		}

		
		return false;
	}

	
}
