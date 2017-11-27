package problemGraph.problems;

import problemGraph.Action;
import problemGraph.State;

import java.util.ArrayList;

public class Problem2 extends Problem {
    private int[] initialStateTable;
    // p00 p01 p02
    // p10 p11 p12
    // p20 p21 p22
    public Problem2(int p00, int p01, int p02, int p10, int p11, int p12, int p20, int p21, int p22 ){
        initialStateTable = new int[]{p00,  p01,  p02,  p10,  p11,  p12,  p20,  p21,  p22};
    }
    @Override
    public State getInitialState() {
        return new PState(0, initialStateTable);
    }

    @Override
    public boolean isFinal(State s) {
        for(int i = 0; i < 9; i++){
            if(i != ((PState)s).table[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public ArrayList<State> getFinalStates() {
        ArrayList<State> finalStates = new ArrayList<>();
        finalStates.add(new PState(0, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}));
        return finalStates;
    }

    @Override
    public int hCost(State s) {
        PState ps = (PState) s;
        int distSum = 0;
        for(int i = 0; i < 9; i++){
            int v = ps.table[i];
            if(v == 0)
                continue;
            distSum += Math.abs(i/3 - v/3); // deltaY
            distSum += Math.abs(i%3 - v%3); // deltaX
        }
        return distSum;
    }

    private class PState extends State {
        int[] table;
        public PState(int totalCost, int[] table){
            super(totalCost);
            this.table = table.clone();
            if(table.length != 9){
                System.err.print("Wrong input");
                System.exit(1);
            }
        }

        public PState(int totalCost, int[] table, State parent, String actionStr){
            super(totalCost, parent, actionStr);
            this.table = table.clone();
            if(table.length != 9){
                System.err.print("Wrong input");
                System.exit(1);
            }
        }

        @Override
        public ArrayList<Action> actionList() {
            ArrayList<Action> actions = new ArrayList<>();
            int blankPlace = 0;
            for(int i = 0; i < 9; i++){
                if(table[i] == 0){
                    blankPlace = i;
                    break;
                }
            }

            if(blankPlace >= 3){
                int[] newTable = table.clone();
                newTable[blankPlace] = newTable[blankPlace-3];
                newTable[blankPlace-3] = 0;
                actions.add(new Action(new PState(
                        totalCost+1, newTable, this, "Up"), 1));
            }
            if(blankPlace <= 5){
                int[] newTable = table.clone();
                newTable[blankPlace] = newTable[blankPlace+3];
                newTable[blankPlace+3] = 0;
                actions.add(new Action(new PState(
                        totalCost+1, newTable, this, "Down"), 1));
            }
            if(blankPlace % 3 != 2){
                int[] newTable = table.clone();
                newTable[blankPlace] = newTable[blankPlace+1];
                newTable[blankPlace+1] = 0;
                actions.add(new Action(new PState(
                        totalCost+1, newTable, this, "Right"), 1));
            }
            if(blankPlace % 3 != 0){
                int[] newTable = table.clone();
                newTable[blankPlace] = newTable[blankPlace-1];
                newTable[blankPlace-1] = 0;
                actions.add(new Action(new PState(
                        totalCost+1, newTable, this, "Left"), 1));
            }

            return actions;
        }

        @Override
        public ArrayList<Action> actionInverseList() {
            ArrayList<Action> actions = new ArrayList<>();
            int blankPlace = 0;
            for(int i = 0; i < 9; i++){
                if(table[i] == 0){
                    blankPlace = i;
                    break;
                }
            }

            if(blankPlace <= 5){
                int[] newTable = table.clone();
                newTable[blankPlace] = newTable[blankPlace+3];
                newTable[blankPlace+3] = 0;
                actions.add(new Action(new PState(
                        totalCost+1, newTable, this, "Up"), 1));
            }
            if(blankPlace >= 3){
                int[] newTable = table.clone();
                newTable[blankPlace] = newTable[blankPlace-3];
                newTable[blankPlace-3] = 0;
                actions.add(new Action(new PState(
                        totalCost+1, newTable, this, "Down"), 1));
            }
            if(blankPlace % 3 != 0){
                int[] newTable = table.clone();
                newTable[blankPlace] = newTable[blankPlace-1];
                newTable[blankPlace-1] = 0;
                actions.add(new Action(new PState(
                        totalCost+1, newTable, this, "Right"), 1));
            }
            if(blankPlace % 3 != 2){
                int[] newTable = table.clone();
                newTable[blankPlace] = newTable[blankPlace+1];
                newTable[blankPlace+1] = 0;
                actions.add(new Action(new PState(
                        totalCost+1, newTable, this, "Left"), 1));
            }

            return actions;
        }

        @Override
        public String toString() {
            String s = "";
            for (int i = 0; i < 7; i++){
                s = s + table[i] + ", ";
            }
            s = s + table[7];
            return s;
        }

        @Override
        public boolean equals(State s) {
            if (s instanceof PState){
                PState ps = (PState)s;
                for(int i = 0; i < 9; i++){
                    if(ps.table[i] != this.table[i]) return false;
                }
                return true;
            }
            System.err.println("Is not of my type!");
            System.exit(1);
            return false;
        }
    }
}
