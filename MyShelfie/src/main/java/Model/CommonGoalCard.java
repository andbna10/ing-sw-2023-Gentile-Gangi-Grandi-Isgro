package Model;

import java.util.ArrayList;
import Model.CommonPattern.*;

public abstract class CommonGoalCard extends GoalCard{
    private ArrayList<ScoringToken> stack;

    /**
     * Overview: CommonGoalCard constructor
     */
    public CommonGoalCard(){
        super();
        this.stack = new ArrayList<>();
    }

    /**
     * Overview: stack element getter
     */
    public ScoringToken getElementStack(int index){ return stack.get(index); }

    /**
     * Overview: stack setter
     */
    public void setElementStack(ScoringToken scoringtoken){ stack.add(scoringtoken); }

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public abstract boolean validated(Bookshelf bookshelf);

    /**
     * Overview: stack getter
     */
    public ArrayList<ScoringToken> getStack(){return stack; }

}
