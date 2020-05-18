package Map;

import java.util.ArrayList;

import GameBoard.Player;

public class Territory implements Comparable<Territory> {

    private int id;
    private Player owner;
    private int troopsNumber;
    private boolean safeState;
    private ArrayList<Territory> neighbours;
    private int neighboursNum = 0;
    public static int FIRST_TROOPS_NUMBER = 0;

    private int cost;

    private int heuristic;

    //for making tree:
    private ArrayList<Territory> treeChildren;
    private Territory parent;

    //private Territory root;
    public Territory() {
        id = -1;
        owner = null;
        troopsNumber = FIRST_TROOPS_NUMBER;
        safeState = false;
        neighbours = new ArrayList<>();

    }

    public boolean isSafeState() {
        for (Territory territory : neighbours) {
            if (territory.getOwner() == this.owner) {
                continue;
            } else if (territory.getOwner() == null) {
                safeState = false;
                return false;
            } else {
                safeState = false;
                return false;
            }
        }
        safeState = true;
        return true;

    }

    public void addNeighbour(Territory neighbour) {
        if (neighbour != null) {
            neighbours.add(neighbour);
            neighboursNum++;

        } else {
            System.out.println("the new inserted neighbout is null ");
            System.out.println("owner Id " + owner.getId());
            System.out.println();
        }

    }
    //number of attacks 

    public int calculateCost() {
        int maximumTroopsLoseAtOneAttack = 2;
        int minimumNumberOfAttacks = troopsNumber / maximumTroopsLoseAtOneAttack;
        cost = minimumNumberOfAttacks;
        return minimumNumberOfAttacks;
    }

    public int getID() {
        return id;
    }

    public void setID(int iD) {
        id = iD;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        owner.addTerrritory(this);
        this.owner = owner;
    }

    public int getTroopsNumber() {
        return troopsNumber;
    }

    public void setTroopsNumber(int troopsNumber) {
        this.troopsNumber = troopsNumber;
    }

    public ArrayList<Territory> getNeighbours() {
        return neighbours;
    }

//new methods...
    public ArrayList<Territory> getChildren() {

        return this.treeChildren;

    }

    public void addChild(Territory parent, Territory child) {
        this.parent = parent;
        parent.treeChildren.add(child);

    }

    public void removeChild(Territory parent, Territory child) {
        this.parent = parent;
        parent.treeChildren.remove(child);

    }

    //for A* real-time
    public void resetTree(ArrayList<Territory> territoriesOwned) {
        int i;
        territoriesOwned.removeAll(treeChildren);
        for (i = 0; i < territoriesOwned.size(); i++) {

            territoriesOwned.get(i).parent = null;

        }

    }

    public float calculateHeuristic() {

        float bst = 0, bsr;
        //int counter=0;
        int owner = this.getID();
        for (int i = 0; i < this.getNeighbours().size(); i++) {
            int currentNeighbourTroops = this.getNeighbours().get(i).getTroopsNumber();
            int currentNeighbour = this.getNeighbours().get(i).getID();

            if (owner != currentNeighbour) {
                bst += currentNeighbourTroops;
            }
        }
        if(this.getTroopsNumber()!=0)
        bsr = bst / (this.getTroopsNumber());
        else
            bsr =bst;
        return bsr;
    }


    public void setG(int heuristic) {
        this.heuristic = heuristic;
    }

    public int getG() {
        return heuristic;
    }

    public void checkSafety() {
        int flag = 0;
        Territory node = this;
        int size = neighbours.size();
        for (int i = 0; i < size; i++) {

            Territory currentNeighbour = neighbours.get(i);
            Player currentNeighbourOwner = currentNeighbour.getOwner();
            if (!currentNeighbour.isSafeState()) {
                flag = 1;
                for (int j = 0; j < currentNeighbour.getNeighbours().size(); j++) {
                    Territory neighbourOfNeighbour = currentNeighbour.getNeighbours().get(j);
                    Player currentNeighbourOfNeighbourOwner = neighbourOfNeighbour.getOwner();
                    if (currentNeighbourOwner == currentNeighbourOfNeighbourOwner) {
                        flag = 1;
                    } else if (currentNeighbourOfNeighbourOwner == null) {
                        flag = 1;
                    } else {
                        flag = 0;
                    }

                }
                if (flag == 1) {
                    currentNeighbour.setSafeState(true);
                }

            }

        }

    }

    //to adjust priorities in priority queue..
    @Override
    public int compareTo(Territory t) {
        //	System.out.println(t.getHeuristic());
        // 	System.err.println(getthis.getHeuristic());
        if (this.getG() > t.getG()) {
            return -1;
        } else if (this.getG() < t.getG()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setSafeState(boolean safeState) {
        this.safeState = safeState;
    }

}
