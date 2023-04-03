package Model;

public abstract class PersonalGoalCard extends GoalCard{
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
}

