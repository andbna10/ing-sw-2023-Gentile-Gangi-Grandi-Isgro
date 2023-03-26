package Model;

public abstract  class GoalCard {
    private int id;
    private String explanation;
    private static int numGoalCards = 0;

    /**
     * Overview: GoalCard constructor
     */
    public GoalCard(String explanation){
        this.id = numGoalCards;
        this.explanation = explanation;
        numGoalCards++;
    }

    /**
     * Overview: id getter
     */
    public int getId(){ return id; }

    /**
     * Overview: explanation getter
     */
    public String getExplanation(){ return explanation; }
}
