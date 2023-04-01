package Model;

import java.util.ArrayList;

public class CommonGoalCard extends GoalCard{
    private ArrayList<ScoringToken> stack;

    /**
     * Overview: CommonGoalCard constructor
     */
    public CommonGoalCard(String explanation){
        super(explanation);
    }

    /**
     * Overview: stack element getter
     */
    public ScoringToken getElementStack(int index){ return stack.get(index); }

    /**
     * Overview: stack setter
     */
    public void setElementStack(ScoringToken scoringtoken){ stack.add(scoringtoken); }
}