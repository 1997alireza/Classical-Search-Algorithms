package problemGraph;

public class Action {
    public State nextState;
    public int stepCost;
    public Action(State nextState, int stepCost){
        this.nextState = nextState;
        this.stepCost = stepCost;
    }
}
