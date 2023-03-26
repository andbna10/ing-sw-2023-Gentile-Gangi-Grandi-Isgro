package Model;

public class PersonalGoalCard extends GoalCard{
    private ItemType[][] description = new ItemType[6][5];
    private int[] points;

    /**
     * Overview: PersonalGoalCard constructor
     */
    public PersonalGoalCard(String explanation, int[] points, ItemType[][] position){
        super(explanation);
        this.points = points;
        this.description = position;
    }

    /**
     * Overview: description getter
     */
    public ItemType[][] getDescription(){ return description; }

    /**
     * Overview: points getter
     */
    public int[] getPoints(){ return points; }
}

