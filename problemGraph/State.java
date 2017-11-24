package problemGraph;
import java.util.ArrayList;

public abstract class State {
    public int totalCost; // g(state)
    public State parent; // if this is root or start node so parent is null
    public String actionStr; // the action detail that transit from parent to this state
    public int height;
    public State(int totalCost){ // it's for root
        this.totalCost = totalCost;
        height = 0;
    }
    public State(int totalCost, State parent, String actionStr){
        this(totalCost);
        this.parent = parent;
        this.actionStr = actionStr;
        height = parent.height + 1;
    }
    public abstract ArrayList<Action> actionList();
    public abstract String toString();
}
