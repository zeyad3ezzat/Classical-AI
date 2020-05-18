package GameBoard;

import java.util.Random;
import java.util.ArrayList;
import Map.Territory;

public class Dice {

    public int getRandomNumber() {
        Random rand = new Random();

        int n = rand.nextInt(6) + 1;
        return n;
    }

    public void rollDices(Territory attacker, Territory defender) {

        ArrayList<Integer> defenderDices = new ArrayList<>();
        ArrayList<Integer> attackerDices = new ArrayList<>();
        Player defenderOwner;

        defenderOwner = defender.getOwner();
        while (attacker.getTroopsNumber() > 1 && defender.getOwner()== defenderOwner) {
        //	System.out.println(attacker.getTroopsNumber());
        
            //roll set of dices
            if (defender.getTroopsNumber() >= 2&& attacker.getTroopsNumber()>2) {
                defenderDices = getDiceSet(2);

            } else if(defender.getTroopsNumber() == 1||attacker.getTroopsNumber()==2){
                defenderDices = getDiceSet(1);

            }else {
            	defenderDices = getDiceSet(0);
            }
            //attacker -1 = number of attacking troops
            attackerDices = getDiceSet(attacker.getTroopsNumber() - 1);
            if (attackerDices.size() >= 2 && defenderDices.size() == 2) {
                Calculate_Win_Loss_Tie(attacker, defender, highest(attackerDices), medium(attackerDices, highest(attackerDices), lowest(attackerDices)), highest(defenderDices), lowest(defenderDices));
            } else if (attackerDices.size() >= 2 && defenderDices.size() == 1) {
                Calculate_Win_Loss_Tie(attacker, defender, highest(attackerDices), 0, highest(defenderDices), 0);

            } else if (attackerDices.size() == 1 && defenderDices.size() == 1) {
                Calculate_Win_Loss_Tie(attacker, defender, highest(attackerDices), 0, highest(defenderDices), 0);
            }

        }
    }

    ArrayList<Integer> getDiceSet(int troops) {
        ArrayList<Integer> Dices = new ArrayList<>();
        int i = 0;
        if (troops >= 3) {
            for (i = 0; i < 3; i++) {
                Dices.add(getRandomNumber());
            }
        } else if (troops == 2) {
            for (i = 0; i < 2; i++) {
                Dices.add(getRandomNumber());
            }
        } else if (troops == 1) {
            Dices.add(getRandomNumber());
        }else{
        	Dices.add(0);
        }

        return Dices;
    }

    public int highest(ArrayList<Integer> random) {
        int i = 0, temp;
        temp = random.get(i);
        for (i = 0; i < random.size(); i++) {
            if (temp < random.get(i)) {
                temp = random.get(i);
            }
        }
        return temp;
    }

    public int medium(ArrayList<Integer> random, int temph, int templ) {
        int i = 0, temp;
        temp = random.get(i);
        for (i = 0; i < random.size(); i++) {
            if (random.get(i) != temph && random.get(i) != templ) {
                temp = random.get(i);
                break;
            }
        }
        if (random.size() == 2 || random.size() == 1) {
            temp = templ;
        }
        return temp;
    }

    public int lowest(ArrayList<Integer> random) {
        int i = 0, temp;
        temp = random.get(i);
        for (i = 0; i < random.size(); i++) {
            if (temp > random.get(i)) {
                temp = random.get(i);
            }
        }
        return temp;
    }

    public void Calculate_Win_Loss_Tie(Territory attacker, Territory defender,
            int AttackHigh, int AttackMedium, int DefenceHigh, int DefenceLow) {
    	System.out.print("the attacker node id "+attacker.getID()+"---->");
    	System.out.println("the defender node id "+defender.getID());
    	
    	System.out.print("the attacker troops number "+attacker.getTroopsNumber()+"---->");
    	System.out.println("the defender troops number "+defender.getTroopsNumber());
    	System.out.println("attack high "+AttackHigh);
    	System.out.println("attack medi "+AttackMedium);
    	System.out.println("defense high "+DefenceHigh);
    	System.out.println("attack low "+DefenceLow);
    	int numberOfAttackingTroops=attacker.getTroopsNumber()-1;
        //3 vs 2  or  2 vs 2
        if (DefenceLow == 0) {
            if (AttackHigh > DefenceHigh) {
                defender.setOwner(attacker.getOwner());
                if (defender.getTroopsNumber() != 1) {
                    defender.setTroopsNumber(numberOfAttackingTroops);
                    attacker.setTroopsNumber(attacker.getTroopsNumber()-numberOfAttackingTroops);
                } else {
                    defender.setOwner(attacker.getOwner());
                    defender.getOwner().getOwnedTerritories().remove(defender);
                    attacker.getOwner().getOwnedTerritories().add(defender);
                    
                    defender.checkSafety();
                }

            } else {
                attacker.setTroopsNumber(numberOfAttackingTroops);

            }
        } else {
            if (AttackHigh > DefenceHigh) {
                //call update method defender troops = defender troops -1
                  System.out.println("defender lose 1 first dice");

                if (AttackMedium <= DefenceLow) {
                    System.out.println("attacker and defender lose 1");
                    defender.setTroopsNumber(defender.getTroopsNumber() - 1);
                    attacker.setTroopsNumber(attacker.getTroopsNumber() - 1);
                    

                } else if (AttackMedium > DefenceLow) {
                    if (defender.getTroopsNumber() == 2) {
                        defender.setOwner(attacker.getOwner());
                        defender.setTroopsNumber(attacker.getTroopsNumber() - 1);
                        attacker.setTroopsNumber(1);
                        
                        defender.getOwner().getOwnedTerritories().remove(defender);
                        attacker.getOwner().getOwnedTerritories().add(defender);
                        
                        
                        defender.checkSafety();

                    } else {
                        defender.setTroopsNumber(defender.getTroopsNumber() - 2);
                    }
                }
            } else if (AttackHigh <= DefenceHigh) {
                //call update method attacker troops = attacker troops -1
                System.out.println("attacker lose 1 first dice");
                //  attacker.setTroopsNumber(attacker.getTroopsNumber()-1);

                if (AttackMedium <= DefenceLow) {
                    System.out.println("attacker lose 1 sec dice");

                    attacker.setTroopsNumber(attacker.getTroopsNumber() - 2);
                } else if (AttackMedium > DefenceLow) {
                    System.out.println("attacke and defender lose 1");
                    defender.setTroopsNumber(defender.getTroopsNumber() - 1);
                    attacker.setTroopsNumber(attacker.getTroopsNumber() - 1);
                }

            }
        }

    }

}
