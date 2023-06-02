package ServerSide.Model;


//TODO: fix newpoints - oldpoints

public abstract class PersonalGoalCard extends GoalCard{
    private int patternNumber;

    /**
     * Overview: PersonalGoalCard constructor
     */
    public PersonalGoalCard(){
        super();
    }

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public abstract int validated(Bookshelf bookshelf);

    /**
     * Overview: patternNumber getter
     */
    public int getPatternNumber(){ return patternNumber; }

    /**
     * Overview: ??
     */
    public abstract Bookshelf getPersonalGoal();

    /**
     * Overview: converts counter to points scores
     */
    public int pointsConverter(int count) {

        return switch (count) {
            case (1) -> 1;
            case (2) -> 2;
            case (3) -> 4;
            case (4) -> 6;
            case (5) -> 9;
            case (6) -> 12;
            default -> 0;
        };

    }
}

