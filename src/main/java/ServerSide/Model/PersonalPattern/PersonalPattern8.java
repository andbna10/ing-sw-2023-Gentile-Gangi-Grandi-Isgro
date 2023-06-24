package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import ServerSide.Model.PersonalGoalCard;

public class PersonalPattern8 extends PersonalGoalCard {
    private Bookshelf personalGoal = new Bookshelf();
    public PersonalPattern8() {
        super();
        personalGoal.setTile(0,4,ItemType.FRAMES);
        personalGoal.setTile(1,1,ItemType.CATS);
        personalGoal.setTile(2,2,ItemType.TROPHIES);
        personalGoal.setTile(3,0,ItemType.PLANTS);
        personalGoal.setTile(4,3,ItemType.BOOKS);
        personalGoal.setTile(5,3,ItemType.GAMES);
    }

    /**
     * Overview: patternNumber getter
     */
    @Override
    public int getPatternNumber() {return 8;}

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

        if(bookshelf.getTile(0,4) != null){
            if(bookshelf.getTile(0, 4).getType() == ItemType.FRAMES) count++;
        }
        if(bookshelf.getTile(1,1) != null){
            if(bookshelf.getTile(1, 1).getType() == ItemType.CATS) count++;
        }
        if(bookshelf.getTile(2,2) != null){
            if(bookshelf.getTile(2, 2).getType() == ItemType.TROPHIES) count++;
        }
        if(bookshelf.getTile(3,0) != null){
            if(bookshelf.getTile(3, 0).getType() == ItemType.PLANTS) count++;
        }
        if(bookshelf.getTile(4,3) != null){
            if(bookshelf.getTile(4, 3).getType() == ItemType.BOOKS) count++;
        }
        if(bookshelf.getTile(5,3) != null){
            if(bookshelf.getTile(5, 3).getType() == ItemType.GAMES) count++;
        }

        return pointsConverter(count);
    }

    @Override
    public Bookshelf getPersonalGoal() {
        return personalGoal;
    }
}
