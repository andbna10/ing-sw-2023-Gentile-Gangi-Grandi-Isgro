package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import ServerSide.Model.PersonalGoalCard;

public class PersonalPattern11 extends PersonalGoalCard {
    private Bookshelf personalGoal = new Bookshelf();
    public PersonalPattern11() {
        super();
        personalGoal.setTile(0,2,ItemType.PLANTS);
        personalGoal.setTile(1,1,ItemType.BOOKS);
        personalGoal.setTile(2,0,ItemType.GAMES);
        personalGoal.setTile(3,2,ItemType.FRAMES);
        personalGoal.setTile(4,4,ItemType.CATS);
        personalGoal.setTile(5,3,ItemType.TROPHIES);
    }

    /**
     * Overview: patternNumber getter
     */
    @Override
    public int getPatternNumber() {return 11;}

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

        if(bookshelf.getTile(0,2) != null){
            if(bookshelf.getTile(0, 2).getType() == ItemType.PLANTS) count++;
        }
        if(bookshelf.getTile(1,1) != null){
            if(bookshelf.getTile(1, 1).getType() == ItemType.BOOKS) count++;
        }
        if(bookshelf.getTile(2,0) != null){
            if(bookshelf.getTile(2, 0).getType() == ItemType.GAMES) count++;
        }
        if(bookshelf.getTile(3,2) != null){
            if(bookshelf.getTile(3, 2).getType() == ItemType.FRAMES) count++;
        }
        if(bookshelf.getTile(4,4) != null){
            if(bookshelf.getTile(4, 4).getType() == ItemType.CATS) count++;
        }
        if(bookshelf.getTile(5,3) != null){
            if(bookshelf.getTile(5, 3).getType() == ItemType.TROPHIES) count++;
        }

        return pointsConverter(count);
    }

    @Override
    public Bookshelf getPersonalGoal() {
        return personalGoal;
    }
}
