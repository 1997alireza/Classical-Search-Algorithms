package problemGraph;

public class Action {
    public State nextState;
    public String actionStr;
    public int stepCost;
    public Action(State nextState, String actionStr, int stepCost){
        this.nextState = nextState;
        this.actionStr = actionStr;
        this.stepCost = stepCost;
    }
}
