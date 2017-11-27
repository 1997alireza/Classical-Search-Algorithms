package problemGraph.problems;

import problemGraph.Action;
import problemGraph.State;

import java.util.ArrayList;

/**
 * Robot Navigation
 */
public class Problem3 extends Problem{
    private int m, n;
    private boolean map [][]; // true: way, false: block
    public Problem3(int m, int n, boolean [/*m*/][/*n*/] map){
        this.m = m;
        this.n = n;
        if(map.length != m || map[0].length != n){
            System.err.println("Wrong input, 'm or 'n isn't equal to map size");
            System.exit(1);
        }
        if(!map[0][0]){
            System.err.println("Wrong input, start room is on wall!");
            System.exit(1);
        }
        if(!map[m-1][n-1]){
            System.err.println("Wrong input, final room is on wall!");
            System.exit(1);
        }
        this.map = map;
    }
    @Override
    public State getInitialState() {
        return new PState(0, 0, 0);
    }

    @Override
    public boolean isFinal(State s) {
        PState ps = (PState)s;
        return ps.lm == (m-1) && ps.ln == (n-1);
    }

    @Override
    public ArrayList<State> getFinalStates() {
        ArrayList<State> finalStates = new ArrayList<>();
        finalStates.add(new PState(0, m-1, n-1));
        return finalStates;
    }

    @Override
    public int hCost(State s) { // Manhattan distance = deltaX + deltaY
        PState ps = (PState)s;
        return (m-1 - ps.lm) + (n-1 - ps.ln);
    }

    private class PState extends State {
        int lm, ln;
        public PState(int totalCost, int lm, int ln){
            super(totalCost);
            this.lm = lm;
            this.ln = ln;
        }

        public PState(int totalCost, int lm, int ln, State parent, String actionStr){
            super(totalCost, parent, actionStr);
            this.lm = lm;
            this.ln = ln;
        }

        @Override
        public ArrayList<Action> actionList() {
            ArrayList<Action> actions = new ArrayList<>();

            if(lm > 0 && map[lm-1][ln]){
                actions.add(new Action(new PState(
                        totalCost+1, lm-1, ln, this, "Up"), 1));
            }
            if(lm < m-1 && map[lm+1][ln]){
                actions.add(new Action(new PState(
                        totalCost+1, lm+1, ln, this, "Down"), 1));
            }
            if(ln < n-1 && map[lm][ln+1]){
                actions.add(new Action(new PState(
                        totalCost+1, lm, ln+1, this, "Right"), 1));
            }
            if(ln > 0 && map[lm][ln-1]){
                actions.add(new Action(new PState(
                        totalCost+1, lm, ln-1, this, "Left"), 1));
            }

            return actions;
        }

        @Override
        public ArrayList<Action> actionInverseList() {
            ArrayList<Action> actions = new ArrayList<>();

            if(lm > 0 && map[lm-1][ln]){
                actions.add(new Action(new PState(
                        totalCost+1, lm-1, ln, this, "Down"), 1));
            }
            if(lm < m-1 && map[lm+1][ln]){
                actions.add(new Action(new PState(
                        totalCost+1, lm+1, ln, this, "Up"), 1));
            }
            if(ln < n-1 && map[lm][ln+1]){
                actions.add(new Action(new PState(
                        totalCost+1, lm, ln+1, this, "Left"), 1));
            }
            if(ln > 0 && map[lm][ln-1]){
                actions.add(new Action(new PState(
                        totalCost+1, lm, ln-1, this, "Right"), 1));
            }

            return actions;
        }

        @Override
        public String toString() {
            return "X: " + lm + ", y: " + ln;
        }

        @Override
        public boolean equals(State s) {
            PState ps = (PState)s;
            return ps.lm == lm && ps.ln == ln;
        }
    }
}
