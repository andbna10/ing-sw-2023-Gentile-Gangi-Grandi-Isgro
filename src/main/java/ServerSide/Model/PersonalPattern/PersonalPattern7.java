package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import ServerSide.Model.PersonalGoalCard;

public class PersonalPattern7 extends PersonalGoalCard {
    private Bookshelf personalGoal = new Bookshelf();
    public PersonalPattern7() {
        super();
        personalGoal.setTile(0,0,ItemType.CATS);
        personalGoal.setTile(1,3,ItemType.FRAMES);
        personalGoal.setTile(2,1,ItemType.PLANTS);
        personalGoal.setTile(3,0,ItemType.TROPHIES);
        personalGoal.setTile(4,4,ItemType.GAMES);
        personalGoal.setTile(5,2,ItemType.BOOKS);
    }

    /**
     * Overview: patternNumber getter
     */
    @Override
    public int getPatternNumber() {return 7;}

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf
     * @author Mirko Gentile
     * @author Simone Grandi
     * @author Andrea Isgrò
     * @param bookshelf bookshelf to be checked
     * @return points earned
     */
    @Override
    public int validated(Bookshelf bookshelf) {
        int count = 0;

        if(bookshelf.getTile(0,0) != null){
            if(bookshelf.getTile(0, 0).getType() == ItemType.CATS) count++;
        }
        if(bookshelf.getTile(1,3) != null){
            if(bookshelf.getTile(1, 3).getType() == ItemType.FRAMES) count++;
        }
        if(bookshelf.getTile(2,1) != null){
            if(bookshelf.getTile(2, 1).getType() == ItemType.PLANTS) count++;
        }
        if(bookshelf.getTile(3,0) != null){
            if(bookshelf.getTile(3, 0).getType() == ItemType.TROPHIES) count++;
        }
        if(bookshelf.getTile(4,4) != null){
            if(bookshelf.getTile(4, 4).getType() == ItemType.GAMES) count++;
        }
        if(bookshelf.getTile(5,2) != null){
            if(bookshelf.getTile(5, 2).getType() == ItemType.BOOKS) count++;
        }

        return pointsConverter(count);
    }

    @Override
    public Bookshelf getPersonalGoal() {
        return personalGoal;
    }
}
