package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.lang.Math;
import java.security.cert.CollectionCertStoreParameters;
import java.sql.Array;

public class Node {
	/* IMPORTNANT NOTE 
	 * YOU MUST INTIALIZE THE PARENT BEFORE SETTING THE NEXT STATES LIST
	 * SO THE NODE WILL HAVE THE PARENT TO COMPARE IT WITH THE NEXT STATES AND DELETE THE REPEATED STATE
	 */
	int currentState[][]=new int[3][3]; //2dArray have the current state of the puzzle
	int[][] GOAL_STATE1 = {
		      {0, 1, 2}, 
		      {3, 4, 5}, 
		      {6,7,8}, 
		};
	int[][] GOAL_STATE2 = {
			{ 1, 2,3}, 
			{4, 5,6}, 
			{7,8,0}, 
	};
	int goalState=-1;  // when we use goal state = -1 so it will compare it to both otherwise will compare it to only one of them
	int costToReach=0;
	int heuristic=0;
	boolean heursticFlag=false;
	public ArrayList<Node> getNextStatesList() {
		return nextStatesList;
	}
	Node parent;
	//two variables to have the location of 0 in the 2d array because we will need it in getting the next states
	public int i ;   
	public int j;
	ArrayList<Node> nextStatesList; //array list that have the next states 
				
	//constructor to define the two goal states 
	
	public Node(int[][] currentState) {
		super();
		setCurrentState(currentState);
//		System.out.println(Arrays.deepToString(this.currentState));
//		System.out.println(i);
//		System.out.println(j);
		
		nextStatesList=new ArrayList<Node>();
		
	}
	
	public int[][] getCurrentState() {
		return currentState;
		
	}
	public void setCurrentState(int[][] currentState) {
		this.currentState = currentState;
		for(int m=0;m<3;m++){
			for(int n=0;n<3;n++){
				if(currentState[m][n]==0){
					this.i=m;
					this.j=n;

					
				}
			}
		}

		//to get the location of the 0 in the array 
		calcHeurstics();
			}
	public Node getParent() {
		return parent;
	}
	private void setParent(Node parent) {
		
		this.parent = parent;
//		System.out.println("from setParent");
//		System.out.println(Arrays.deepToString(parent.getCurrentState()));
		
		
		
	}
	
	public void setNextStates(){
//	********	  and don't forget to compare it with the parent's current state  *********
//don't forget to check if it's the root so it will have no parent 
		//don't forget i is the row number
		//don't forget j is the column number
			

		

		if(0==i && 0==j){
			/* 
			  if i=0 and j=0 so there is no left no up
			  |0 * *|
			  |* * *|
			  |* * *|*/
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getRightChild().currentState))) {
				nextStatesList.add(getRightChild());			
				
			}
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLowerChild().currentState))) {
				nextStatesList.add(getLowerChild());

			}
			
			

			
		}	
		else if(0==i && 1==j){
			/*  if i=0 and j=1 so there is no Up 
			  |* 0 *|
			  |* * *|
			  |* * *|*/
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getRightChild().currentState))) {
				nextStatesList.add(getRightChild());			
				
			}
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLowerChild().currentState))) {
				nextStatesList.add(getLowerChild());

			}
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLeftChild().currentState))) {
				nextStatesList.add(getLeftChild());			
				
			}


			
		}else if(0==i && 2==j){
			/*if i=0 and j=2 so there is no right no up
					  |* * 0|
					  |* * *|
					  |* * *|
					  */
			
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLowerChild().currentState))) {
				nextStatesList.add(getLowerChild());

			}
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLeftChild().currentState))) {
				nextStatesList.add(getLeftChild());			
				
			}

		}else if(1==i && 0==j){
		/*	 if i=1 and j=0 so there is no left 
					  |* * *|
					  |0 * *|
					  |* * *|
		 */
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getRightChild().currentState))) {
				nextStatesList.add(getRightChild());			
				
			}
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLowerChild().currentState))) {
				nextStatesList.add(getLowerChild());

			}
			
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getUpperChild().currentState))) {
				nextStatesList.add(getUpperChild());

			}
		}else if(1==i && 1==j){
			/* if i=1 and j=1 so all possible 
					  |* * *|
					  |* 0 *|
					  |* * *|*/
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getRightChild().currentState))) {
				nextStatesList.add(getRightChild());			
				
			}
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLowerChild().currentState))) {
				nextStatesList.add(getLowerChild());

			}
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLeftChild().currentState))) {
				nextStatesList.add(getLeftChild());			
				
			}
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getUpperChild().currentState))) {
				nextStatesList.add(getUpperChild());

			}
			
			
			
			
		}else if(1==i && 2==j){
		/*	  if i=1 and j=2 so there is no right 
					  |* * *|
					  |* * 0|
					  |* * *|
				*/
			
			
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLowerChild().currentState))) {
				nextStatesList.add(getLowerChild());

			}
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLeftChild().currentState))) {
				nextStatesList.add(getLeftChild());			
				
			}
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getUpperChild().currentState))) {
				nextStatesList.add(getUpperChild());

			}

		}else if(2==i && 0==j){
			/* 
			 
			  if i=2 and j=0 so there is no left no down
			  |* * *|
			  |* * *|
			  |0 * *|
			  
			*/
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getRightChild().currentState))) {
				nextStatesList.add(getRightChild());			
				
			}
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getUpperChild().currentState))) {
				nextStatesList.add(getUpperChild());

			}

		
		}else if(2==i && 1==j){
			/*  if i=2 and j=1 so there is no down 
					  |* * *|
					  |* * *|
					  |* 0 *|
					  */
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getRightChild().currentState))) {
				nextStatesList.add(getRightChild());			
				
			}
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLeftChild().currentState))) {
				nextStatesList.add(getLeftChild());			
				
			}
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getUpperChild().currentState))) {
				nextStatesList.add(getUpperChild());

			}

					
		}else if(2==i && 2==j){
		/*	 if i=2 and j=2 so there is no right no down 
					  |* * *|
					  |* * *|
					  |* * 0|
			*/		 
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getLeftChild().currentState))) {
				nextStatesList.add(getLeftChild());			
				
			}
			if (parent==null||(!Arrays.deepEquals(parent.currentState, getUpperChild().currentState))) {
				nextStatesList.add(getUpperChild());

			}

		}
		
			
		
	}
	public void setCost(){
		if(parent!=null){
			costToReach=parent.getCostToReach()+1;
		}else{
			costToReach=0;
		}
	}
	public int getCostToReach() {
		return costToReach;
	}
	
	public boolean checkGoalState(){
		if(parent!=null){
			this.goalState=parent.goalState;
		}
		System.out.println("goal state " + this.goalState);
		switch (this.goalState) {
		case -1:
				if (Arrays.deepEquals(GOAL_STATE1, currentState)||Arrays.deepEquals(GOAL_STATE2, currentState)) {
					return true;
					}
				break;
		case 1:
			if (Arrays.deepEquals(GOAL_STATE1, currentState)) {
				return true;
				}
			break;
		case 2:
			if (Arrays.deepEquals(GOAL_STATE2, currentState)) {
				return true;
				}
			break;

		default:
			break;
		}
		return false;
		
	}
	public void calcHeurstics(){
	
		int finalHeurstics=Integer.MAX_VALUE;
		int heurstics1=0;
		int heurstics2=0;
		
		//System.out.println(Arrays.deepToString(currentState));
		for(int goalState=1;goalState<3;goalState++){
			for(int number=0;number<9;number++){
				int temp=heursticsHelper(number, goalState);
			//	System.out.println("temp "+ temp);
				if(1==goalState){
					heurstics1+=temp;
				}else{
					heurstics2+=temp;
				}
				
			}
		}
		if(heurstics1<=heurstics2){
			finalHeurstics=heurstics1;
			this.goalState=1;
	
		}else{
			finalHeurstics=heurstics2;
			this.goalState=2;
	
			}
		
//		System.out.println("heurstic 1  ="+heurstics1);
//		System.out.println("heurstic 2  ="+heurstics2);
		
		this.heuristic=finalHeurstics;
//		System.out.println("final heurstic ="+this.heuristic);
	//	System.out.println("cost   ="+costToReach);
		//System.out.println();
	}
	public int getHeuristics() {
		return heuristic;
	}
	private int heursticsHelper(int number,int goalStateNumber){
		int m=0; // for row 
		int n=0; //for column
		int heurstic=0;
		if(goalStateNumber==1){
			for(int rowIter=0;rowIter<3;rowIter++){
				for(int columnIter=0;columnIter<3;columnIter++){
					if(currentState[rowIter][columnIter]==number){
						m=rowIter;
						n=columnIter;
						break;
					}
				}
			}
			/*
			 * |0 1 2|
			 * |3 4 5|
			 * |6 7 8|
			 */
			switch(number){
			case 0:
				heurstic=Math.abs(0-m)+Math.abs(0-n);
				break;
			case 1:
				heurstic=Math.abs(0-m)+Math.abs(1-n);
				break;
			case 2:
				heurstic=Math.abs(0-m)+Math.abs(2-n);
				break;
			case 3:
				heurstic=Math.abs(1-m)+Math.abs(0-n);
				break;
			case 4:
				heurstic=Math.abs(1-m)+Math.abs(1-n);
				break;
			case 5:
				heurstic=Math.abs(1-m)+Math.abs(2-n);
				break;
			case 6:
				heurstic=Math.abs(2-m)+Math.abs(0-n);
				break;
			case 7:
				heurstic=Math.abs(2-m)+Math.abs(1-n);
				break;
			case 8:
				heurstic=Math.abs(2-m)+Math.abs(2-n);
				break;
			default:
				break;
			

				
			
			}

			
		}else if(goalStateNumber ==2){
			for(int rowIter=0;rowIter<3;rowIter++){
				for(int columnIter=0;columnIter<3;columnIter++){
					if(currentState[rowIter][columnIter]==number){
						m=rowIter;
						n=columnIter;
						break;
					}
				}
			}
			/*
			 * |1 2 3|
			 * |4 5 6|
			 * |7 8 0|
			 */
			switch(number){
			case 0:
				heurstic=Math.abs(2-m)+Math.abs(2-n);
				break;
			case 1:
				heurstic=Math.abs(0-m)+Math.abs(0-n);
				break;
			case 2:
				heurstic=Math.abs(0-m)+Math.abs(1-n);
				break;
			case 3:
				heurstic=Math.abs(1-m)+Math.abs(2-n);
				break;
			case 4:
				heurstic=Math.abs(1-m)+Math.abs(0-n);
				break;
			case 5:
				heurstic=Math.abs(1-m)+Math.abs(1-n);
				break;
			case 6:
				heurstic=Math.abs(2-m)+Math.abs(2-n);
				break;
			case 7:
				heurstic=Math.abs(2-m)+Math.abs(0-n);
				break;
			case 8:
				heurstic=Math.abs(2-m)+Math.abs(1-n);
				break;
			default:
				break;
			

				
			
			}

			
		}else{
			//do nothing
		}
		return heurstic;
		
	}
	private Node getRightChild(){
		int arrayAfterRight[][]=new int[3][3];
		arrayAfterRight=copyArray(this.currentState);
		//swap the location value of 0 and the one at it's right 
	
		int temp=arrayAfterRight[i][j];
		arrayAfterRight[i][j]=arrayAfterRight[i][j+1];
		arrayAfterRight[i][j+1]=temp;
	//	arrayAfterRight[i][j+1]=currentState[i][j];
		Node rightChild=new Node(arrayAfterRight);
		rightChild.setParent(this);
		return rightChild;
	
		
	}
	private Node getLowerChild(){
		int arrayAfterDown[][]=copyArray(currentState);
		//swap the location value of 0 and the one under it 
		int temp=arrayAfterDown[i][j];

		arrayAfterDown[i][j]=arrayAfterDown[i+1][j];
		arrayAfterDown[i+1][j]=temp;
		Node lowerChild= new Node(arrayAfterDown);
		lowerChild.setParent(this);
		return lowerChild;
	}
	
	private Node getLeftChild(){
		int arrayAfterLeft[][]=copyArray(currentState);
		int temp=arrayAfterLeft[i][j];
		arrayAfterLeft[i][j]=arrayAfterLeft[i][j-1];
		arrayAfterLeft[i][j-1]=temp;
		Node leftChild=new Node(arrayAfterLeft);
		leftChild.setParent(this);
		return leftChild;
	}
	private Node getUpperChild(){
		int arrayAfterUp[][]=copyArray(currentState);
		int temp=arrayAfterUp[i][j];
		arrayAfterUp[i][j]=arrayAfterUp[i-1][j];
		arrayAfterUp[i-1][j]=temp;
		Node upperChild=new Node(arrayAfterUp);
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
	 

	
	

}
