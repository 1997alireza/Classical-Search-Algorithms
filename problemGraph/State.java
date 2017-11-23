package problemGraph;
import java.util.ArrayList;

public abstract class State {
    public int totalCost; // g(state)
    public State parent;
    public String actionStr;
    public State(int totalCost){
        this.totalCost = totalCost;
    }
    public State(int totalCost, State parent, String actionStr){
        this(totalCost);
        this.parent = parent;
        this.actionStr = actionStr;
    }
    public abstract ArrayList<Action> actionList();
    public abstract String toString();
}
