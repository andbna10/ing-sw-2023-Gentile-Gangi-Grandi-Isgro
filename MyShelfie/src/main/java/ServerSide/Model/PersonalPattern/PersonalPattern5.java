package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import ServerSide.Model.PersonalGoalCard;

public class PersonalPattern5 extends PersonalGoalCard {
    private Bookshelf personalGoal = new Bookshelf();
    public PersonalPattern5() {
        super();
        personalGoal.setTile(1,1,ItemType.TROPHIES);
        personalGoal.setTile(3,1,ItemType.FRAMES);
        personalGoal.setTile(3,2,ItemType.BOOKS);
        personalGoal.setTile(4,4,ItemType.PLANTS);
        personalGoal.setTile(5,0,ItemType.GAMES);
        personalGoal.setTile(5,3,ItemType.CATS);
    }

    /**
     * Overview: patternNumber getter
     */
    @Override
    public int getPatternNumber() {return 5;}

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

        if(bookshelf.getTile(1,1) != null){
            if(bookshelf.getTile(1, 1).getType() == ItemType.TROPHIES) count++;
        }
        if(bookshelf.getTile(3,1) != null){
            if(bookshelf.getTile(3, 1).getType() == ItemType.FRAMES) count++;
        }
        if(bookshelf.getTile(3,2) != null){
            if(bookshelf.getTile(3, 2).getType() == ItemType.BOOKS) count++;
        }
        if(bookshelf.getTile(4,4) != null){
            if(bookshelf.getTile(4, 4).getType() == ItemType.PLANTS) count++;
        }
        if(bookshelf.getTile(5,0) != null){
            if(bookshelf.getTile(5, 0).getType() == ItemType.GAMES) count++;
        }
        if(bookshelf.getTile(5,3) != null){
            if(bookshelf.getTile(5, 3).getType() == ItemType.CATS) count++;
        }

        return pointsConverter(count);
    }

    @Override
    public Bookshelf getPersonalGoal() {
        return personalGoal;
    }
}
