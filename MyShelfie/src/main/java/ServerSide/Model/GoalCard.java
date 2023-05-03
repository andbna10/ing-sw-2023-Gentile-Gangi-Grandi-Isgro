package ServerSide.Model;

public abstract  class GoalCard {
    private int id;
    private static int numGoalCards = 0;

    /**
     * Overview: GoalCard constructor
     */
    public GoalCard(){
        this.id = numGoalCards;
        numGoalCards++;
    }

    /**
     * Overview: id getter
     */
    public int getId(){ return id; }

}
