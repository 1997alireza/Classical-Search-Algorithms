package problemGraph.problems;
import problemGraph.Action;
import problemGraph.State;

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
    public ArrayList<State> getFinalStates() {
        ArrayList<State> finalStates = new ArrayList<>();
        for(int i = 0; i <= 3; i++){
            finalStates.add(new PState(0, i, 2));
        }
        return finalStates;
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
        public PState(int totalCost, int initialPitcher3, int initialPitcher4){
            super(totalCost);
            pitcher3 = initialPitcher3;
            pitcher4 = initialPitcher4;
        }

        @Override
        public ArrayList<Action> actionList() {
            ArrayList<Action> actions = new ArrayList<>();
            if(pitcher3 < 3)
                actions.add(new Action(new PState(
                        totalCost+1, 3, pitcher4, this, "fill 3"), 1));
            if(pitcher4 < 4)
                actions.add(new Action(new PState(
                        totalCost+1, pitcher3, 4, this, "fill 4"), 1));
            if(pitcher3 > 0) {
                actions.add(new Action(new PState(
                        totalCost+1, 0, pitcher4, this, "empty 3"), 1));
                if(4 - pitcher4 >= pitcher3)
                    actions.add(new Action(new PState(
                            totalCost+1, 0, pitcher4 + pitcher3, this, "3 to 4"), 1));
                else
                    actions.add(new Action(new PState(
                            totalCost+1, pitcher3 - (4 - pitcher4), 4, this, "3 to 4"), 1));
            }
            if(pitcher4 > 0) {
                actions.add(new Action(new PState(
                        totalCost + 1, pitcher3, 0, this, "empty 4"), 1));

                if(3 - pitcher3 >= pitcher4)
                    actions.add(new Action(new PState(
                            totalCost+1, pitcher3 + pitcher4, 0, this, "4 to 3"), 1));
                else
                    actions.add(new Action(new PState(
                            totalCost+1, 3, pitcher4 - (3 - pitcher3), this, "4 to 3"), 1));

            }

            return actions;
        }

        @Override
        public ArrayList<Action> actionInverseList() {
            ArrayList<Action> actions = new ArrayList<>();
            if(pitcher3 == 3) {
                actions.add(new Action(new PState(
                        totalCost + 1, 0, pitcher4, this, "fill 3"), 1));
                actions.add(new Action(new PState(
                        totalCost + 1, 1, pitcher4, this, "fill 3"), 1));
                actions.add(new Action(new PState(
                        totalCost + 1, 2, pitcher4, this, "fill 3"), 1));

                //p4: 0, 1, 2, 3
                //0: (0, 3)(1, 2)(2, 1)
                //1: (0, 4)(1,3)(2,2)
                //2: (1, 4)(2, 3)
                //3: (2, 4)
                int maxIteration = (pitcher4 == 0)? 3 : 4;
                for(int i = pitcher4+1; i <= maxIteration; i++){
                    actions.add(new Action(new PState(
                            totalCost + 1, pitcher4+3-i, i, this, "4 to 3"), 1));
                }
            }
            if(pitcher4 == 4) {
                actions.add(new Action(new PState(
                        totalCost + 1, pitcher3, 0, this, "fill 4"), 1));
                actions.add(new Action(new PState(
                        totalCost + 1, pitcher3, 1, this, "fill 4"), 1));
                actions.add(new Action(new PState(
                        totalCost + 1, pitcher3, 2, this, "fill 4"), 1));
                actions.add(new Action(new PState(
                        totalCost + 1, pitcher3, 3, this, "fill 4"), 1));

                for(int i = 3; i > pitcher3; i--){
                    actions.add(new Action(new PState(
                            totalCost + 1, i, 4+pitcher3-i, this, "3 to 4"), 1));
                }
            }
            if(pitcher3 == 0) {
                actions.add(new Action(new PState(
                        totalCost+1, 1, pitcher4, this, "empty 3"), 1));
                actions.add(new Action(new PState(
                        totalCost+1, 2, pitcher4, this, "empty 3"), 1));
                actions.add(new Action(new PState(
                        totalCost+1, 3, pitcher4, this, "empty 3"), 1));
            }
            if(pitcher4 == 0) {
                actions.add(new Action(new PState(
                        totalCost + 1, pitcher3, 1, this, "empty 4"), 1));
                actions.add(new Action(new PState(
                        totalCost + 1, pitcher3, 2, this, "empty 4"), 1));
                actions.add(new Action(new PState(
                        totalCost + 1, pitcher3, 3, this, "empty 4"), 1));
                actions.add(new Action(new PState(
                        totalCost + 1, pitcher3, 4, this, "empty 4"), 1));
            }

            if(pitcher4 < 4 && pitcher4 != 0 && pitcher3 == 0){
                for(int i = 1; i <= pitcher4; i++){
                    actions.add(new Action(new PState(
                            totalCost + 1, i, pitcher4-i, this, "3 to 4"), 1));
                }
            }

            if(pitcher3 < 3 && pitcher3 != 0 && pitcher4 == 0){
                for(int i = 0; i < pitcher3; i++){
                    actions.add(new Action(new PState(
                            totalCost + 1, i, pitcher3-i, this, "4 to 3"), 1));
                }
            }

            return actions;
        }


        @Override
        public String toString() {
            return "Pitcher 3: " + pitcher3 + ", Pitcher 4: " + pitcher4;
        }

        @Override
        public boolean equals(State s) {
            if (s instanceof PState){
                PState ps = (PState)s;
                return pitcher3 == ps.pitcher3 && pitcher4 == ps.pitcher4;
            }
            System.err.println("Is not of my type!");
            System.exit(1);
            return false;
        }
    }

}
