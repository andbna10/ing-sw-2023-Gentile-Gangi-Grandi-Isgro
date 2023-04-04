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

    /**
     * Overview: converts counter to points scores
     */
    public int pointsConverter(int count) {
        int score = 0;
        switch(count) {
            case(1):
                score = 1;
                break;
            case(2):
                score = 2;
                break;
            case(3):
                score = 4;
                break;
            case(4):
                score = 6;
                break;
            case(5):
                score = 9;
                break;
            case(6):
                score = 12;
                break;
        }

        return score;
    }
}

