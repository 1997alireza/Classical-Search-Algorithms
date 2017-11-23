package problemGraph.problems;
import problemGraph.Action;
import problemGraph.State;
import problemGraph.Problem;

import java.util.ArrayList;

public class Problem1 extends Problem {

    public Problem1() {
    }

    @Override
    public State getInitialState() {
        return new PState(0);
    }

    @Override
    public boolean isFinal(State s) {
        return ((PState)s).pitcher4 == 2;
    }

    @Override
    public int hCost(State s) {
        return 0;
    }

    private class PState extends State {
        public int pitcher3, pitcher4;
        public PState(int totalCost) {
            super(totalCost);
            pitcher3 = 0;
            pitcher4 = 0;
        }
        public PState(int totalCost, int initialPitcher3, int initialPitcher4, State parent, String actionStr){
            super(totalCost, parent, actionStr);
            pitcher3 = initialPitcher3;
            pitcher4 = initialPitcher4;
        }

        @Override
        public ArrayList<Action> actionList() {
            ArrayList<Action> actions = new ArrayList<>();
            if(pitcher3 < 3)
                actions.add(new Action(new PState(
                        totalCost+1, 3, pitcher4, this, "full 3"), "full 3", 1));
            if(pitcher4 < 4)
                actions.add(new Action(new PState(
                        totalCost+1, pitcher3, 4, this, "full 4"), "full 4", 1));
            if(pitcher3 > 0) {
                actions.add(new Action(new PState(
                        totalCost + 1, 0, pitcher4, this, "empty 3"), "empty 3", 1));
                if(4 - pitcher4 >= pitcher3)
                    actions.add(new Action(new PState(
                            totalCost+1, 0, pitcher4 + pitcher3, this, "3 to 4"), "3 to 4", 1));
                else
                    actions.add(new Action(new PState(
                            totalCost+1, pitcher3 - (4 - pitcher4), 4, this, "3 to 4"), "3 to 4", 1));
            }
            if(pitcher4 > 0) {
                actions.add(new Action(new PState(
                        totalCost + 1, pitcher3, 0, this, "empty 4"), "empty 4", 1));

                if(3 - pitcher3 >= pitcher4)
                    actions.add(new Action(new PState(
                            totalCost+1, pitcher3 + pitcher4, 0, this, "4 to 3"), "4 to 3", 1));
                else
                    actions.add(new Action(new PState(
                            totalCost+1, 3, pitcher4 - (3 - pitcher3), this, "4 to 3"), "4 to 3", 1));

            }

            return actions;
        }

        @Override
        public String toString() {
            return "Pitcher 3: " + pitcher3 + ", Pitcher 4: " + pitcher4;
        }
    }
}
