package main;

import java.util.ArrayList;
import java.util.Arrays;

public class Node {
	int dimension = 3;
	/* IMPORTNANT NOTE 
	 * YOU MUST INTIALIZE THE PARENT BEFORE SETTING THE NEXT STATES LIST
	 * SO THE NODE WILL HAVE THE PARENT TO COMPARE IT WITH THE NEXT STATES AND DELETE THE REPEATED STATE
	 */
	int currentState[][]=new int[3][3]; //2dArray have the current state of the puzzle
	int[][] GOAL_STATE1 = {
		      {0, 1, 2}, 
		      {3, 4, 5}, 
		      {6,7,8} 
		};
	int[][] GOAL_STATE2 = {
			{ 1, 2,3}, 
			{4, 5,6}, 
			{7,8,0} 
	};
	
	Node parent;
	//two variables to have the location of 0 in the 2d array because we will need it in getting the next states
	int i ;   
	int j;
	
	/*4 Elements Array for NextStates moves ( True if is valid move , false if not a valid move (Out of matrix bounds or back to parent's state) ) 
	index:	0 -> UP
			1 -> RIGHT
	  		2 -> DOWN
	  		3 -> LEFT
	  		ClockWise ^_^
	  		*/
	boolean[] moves ;
	
	
	ArrayList<Node> nextStatesList; //array list that have the next states 
				
	//constructor to define the two goal states 
	
	public Node(int[][] currentState) {
		//super();
		setCurrentState(currentState);
		nextStatesList=new ArrayList<Node>();
		parent = null;
		moves = new boolean[4];
	}
	public int[][] getCurrentState() {
		return currentState;
		
	}
	public void setCurrentState(int[][] currentState) {
		this.currentState = currentState;
		//to get the location of the 0 in the array 
		for(int m=0;m<3;m++){
			for(int n=0;n<3;n++){
				if(currentState[m][n]==0){
					this.i=m;
					this.j=n;
					
				}
			}
		}
	}
	public Node getParent() {
		return parent;
	}
	private void setParent(Node parent) {
		this.parent = parent;
	}
	
	public void setNextStates(){
//	********	  and don't forget to compare it with the parent's current state  *********
//don't forget to check if it's the root so it will have no parent 
		//don't forget i is the row number
		//don't forget j is the column number
		
		if(parent!=null) {
		//UP
		moves[0] = isSafe(i-1) && (i-1 != parent.i) ?nextStatesList.add(getUpperChild()):false;
		//RIGHT
		moves[1] = isSafe(j+1) && (j+1 != parent.j) ?nextStatesList.add(getRightChild()):false;
		//DOWN
		moves[2] = isSafe(i+1) && (i+1 != parent.i) ?nextStatesList.add(getLowerChild()):false;
		//LEFT
		moves[3] = isSafe(j-1) && (j-1 != parent.j) ?nextStatesList.add(getLeftChild()):false;
		}
		else { //For First Node that has no parent 
			//UP
			moves[0] = isSafe(i-1) ?nextStatesList.add(getUpperChild()):false;
			//RIGHT
			moves[1] = isSafe(j+1) ?nextStatesList.add(getRightChild()):false;
			//DOWN
			moves[2] = isSafe(i+1) ?nextStatesList.add(getLowerChild()):false;
			//LEFT
			moves[3] = isSafe(j-1) ?nextStatesList.add(getLeftChild()):false;
		}
		System.out.println(Arrays.toString(moves));
//		
//		
//		moves[0] = isSafe(i-1);moves[1] = isSafe(j+1);
//		moves[2] = isSafe(i+1);moves[3] = isSafe(j-1);
//		if(moves[0])nextStatesList.add(getUpperChild());
//		if(moves[1])nextStatesList.add(getRightChild());
//		if(moves[2])nextStatesList.add(getLowerChild());
//		if(moves[3])nextStatesList.add(getLeftChild());

//		if(0==i && 0==j){
//			/* 
//			  if i=0 and j=0 so there is no left no up
//			  |0 * *|
//			  |* * *|
//			  |* * *|*/
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getRightChild().currentState))) {
//				nextStatesList.add(getRightChild());			
//				
//			}
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLowerChild().currentState))) {
//				nextStatesList.add(getLowerChild());
//
//			}
//
//		}	
//		else if(0==i && 1==j){
//			/*  if i=0 and j=1 so there is no Up 
//			  |* 0 *|
//			  |* * *|
//			  |* * *|*/
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getRightChild().currentState))) {
//				nextStatesList.add(getRightChild());			
//				
//			}
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLowerChild().currentState))) {
//				nextStatesList.add(getLowerChild());
//
//			}
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLeftChild().currentState))) {
//				nextStatesList.add(getLeftChild());			
//				
//			}
//		}else if(0==i && 2==j){
//			/*if i=0 and j=2 so there is no right no up
//					  |* * 0|
//					  |* * *|
//					  |* * *|
//					  */
//			
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLowerChild().currentState))) {
//				nextStatesList.add(getLowerChild());
//
//			}
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLeftChild().currentState))) {
//				nextStatesList.add(getLeftChild());			
//				
//			}
//
//		}else if(1==i && 0==j){
//		/*	 if i=1 and j=0 so there is no left 
//					  |* * *|
//					  |0 * *|
//					  |* * *|
//		 */
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getRightChild().currentState))) {
//				nextStatesList.add(getRightChild());			
//				
//			}
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLowerChild().currentState))) {
//				nextStatesList.add(getLowerChild());
//
//			}
//			
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getUpperChild().currentState))) {
//				nextStatesList.add(getUpperChild());
//
//			}
//		}else if(1==i && 1==j){
//			/* if i=1 and j=1 so all possible 
//					  |* * *|
//					  |* 0 *|
//					  |* * *|*/
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getRightChild().currentState))) {
//				nextStatesList.add(getRightChild());			
//				
//			}
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLowerChild().currentState))) {
//				nextStatesList.add(getLowerChild());
//
//			}
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLeftChild().currentState))) {
//				nextStatesList.add(getLeftChild());			
//				
//			}
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getUpperChild().currentState))) {
//				nextStatesList.add(getUpperChild());
//
//			}
//			
//			
//			
//			
//		}else if(1==i && 2==j){
//		/*	  if i=1 and j=2 so there is no right 
//					  |* * *|
//					  |* * 0|
//					  |* * *|
//				*/
//			
//			
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLowerChild().currentState))) {
//				nextStatesList.add(getLowerChild());
//
//			}
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLeftChild().currentState))) {
//				nextStatesList.add(getLeftChild());			
//				
//			}
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getUpperChild().currentState))) {
//				nextStatesList.add(getUpperChild());
//
//			}
//
//		}else if(2==i && 0==j){
//			/* 
//			 
//			  if i=2 and j=0 so there is no left no down
//			  |* * *|
//			  |* * *|
//			  |0 * *|
//			  
//			*/
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getRightChild().currentState))) {
//				nextStatesList.add(getRightChild());			
//				
//			}
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getUpperChild().currentState))) {
//				nextStatesList.add(getUpperChild());
//
//			}
//
//		
//		}else if(2==i && 1==j){
//			/*  if i=2 and j=1 so there is no down 
//					  |* * *|
//					  |* * *|
//					  |* 0 *|
//					  */
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getRightChild().currentState))) {
//				nextStatesList.add(getRightChild());			
//				
//			}
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLeftChild().currentState))) {
//				nextStatesList.add(getLeftChild());			
//				
//			}
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getUpperChild().currentState))) {
//				nextStatesList.add(getUpperChild());
//
//			}
//
//					
//		}else if(2==i && 2==j){
//		/*	 if i=2 and j=2 so there is no right no down 
//					  |* * *|
//					  |* * *|
//					  |* * 0|
//			*/		 
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLeftChild().currentState))) {
//				nextStatesList.add(getLeftChild());			
//				
//			}
//			if (parent==null||(!Arrays.deepEquals(parent.currentState, getUpperChild().currentState))) {
//				nextStatesList.add(getUpperChild());
//
//			}
//
//		}
		
		System.out.println(getNextStatesList().size());
		System.out.println("It's next states: \n");
		for(Node node :getNextStatesList()) {
			printMatrix(node.getCurrentState());
			System.out.println("----------");
		}
		System.out.println("-------------------------------------");
		
		
	}
	public boolean checkGoalState(){
		if (Arrays.deepEquals(GOAL_STATE1, currentState)||Arrays.deepEquals(GOAL_STATE2, currentState)) {
			return true;
		} else {
			return false;
		}
	}
	private Node getRightChild(){
		int arrayAfterRight[][]=new int[3][3];
		arrayAfterRight=copyArray(this.currentState);
		//swap the location value of 0 and the one at it's right 
	
		//int temp=arrayAfterRight[i][j];
		arrayAfterRight[i][j]=arrayAfterRight[i][j+1];
		arrayAfterRight[i][j+1] = 0;
	//	arrayAfterRight[i][j+1]=currentState[i][j];
		Node rightChild = new Node(arrayAfterRight);
		rightChild.setParent(this);
		return rightChild;
	}
	private Node getLowerChild(){
		int arrayAfterDown[][] = copyArray(currentState);
		//swap the location value of 0 and the one under it 
		//int temp=arrayAfterDown[i][j];

		arrayAfterDown[i][j]=arrayAfterDown[i+1][j];
		arrayAfterDown[i+1][j] = 0;
		Node lowerChild = new Node(arrayAfterDown);
		lowerChild.setParent(this);
		return lowerChild;
	}
	private Node getLeftChild(){
		int arrayAfterLeft[][]=copyArray(currentState);
		//int temp=arrayAfterLeft[i][j];
		arrayAfterLeft[i][j]=arrayAfterLeft[i][j-1];
		arrayAfterLeft[i][j-1] = 0;
		Node leftChild = new Node(arrayAfterLeft);
		leftChild.setParent(this);
		return leftChild;
	}
	private Node getUpperChild(){
		int arrayAfterUp[][] = copyArray(currentState);
		//int temp=arrayAfterUp[i][j];
		arrayAfterUp[i][j]=arrayAfterUp[i-1][j];
		arrayAfterUp[i-1][j] = 0;
		Node upperChild = new Node(arrayAfterUp);
		upperChild.setParent(this);
		return upperChild;
	}
	private  int[][] copyArray(int[][]firstArray){
		int a[][]=new int [3][3];
		a[0][0]=firstArray[0][0];
		a[0][1]=firstArray[0][1];
		a[0][2]=firstArray[0][2];
		a[1][0]=firstArray[1][0];
		a[1][1]=firstArray[1][1];
		a[1][2]=firstArray[1][2];
		a[2][0]=firstArray[2][0];
		a[2][1]=firstArray[2][1];
		a[2][2]=firstArray[2][2];
		return a;
	}
	public ArrayList<Node> getNextStatesList() {
		return nextStatesList;
	} 
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	// Function to check if (x, y) is a valid matrix coordinate 
		public boolean isSafe(int x) {

			return (x >= 0 && x < dimension);
		}

}
