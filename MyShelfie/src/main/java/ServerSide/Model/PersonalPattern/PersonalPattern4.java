package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import ServerSide.Model.PersonalGoalCard;

public class PersonalPattern4 extends PersonalGoalCard {
    private Bookshelf personalGoal = new Bookshelf();
    public PersonalPattern4() {
        super();
        personalGoal.setTile(0,4,ItemType.GAMES);
        personalGoal.setTile(2,0,ItemType.TROPHIES);
        personalGoal.setTile(2,2,ItemType.FRAMES);
        personalGoal.setTile(3,3,ItemType.PLANTS);
        personalGoal.setTile(4,1,ItemType.BOOKS);
        personalGoal.setTile(4,2,ItemType.CATS);
    }

    /**
     * Overview: patternNumber getter
     */
    @Override
    public int getPatternNumber() {return 4;}

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns points
     */
    @Override
    public int validated(Bookshelf bookshelf) {
        int count = 0;

        if(bookshelf.getTile(0,4) != null){
            if(bookshelf.getTile(0, 4).getType() == ItemType.GAMES) count++;
        }
        if(bookshelf.getTile(2,0) != null){
            if(bookshelf.getTile(2, 0).getType() == ItemType.TROPHIES) count++;
        }
        if(bookshelf.getTile(2,2) != null){
            if(bookshelf.getTile(2, 2).getType() == ItemType.FRAMES) count++;
        }
        if(bookshelf.getTile(3,3) != null){
            if(bookshelf.getTile(3, 3).getType() == ItemType.PLANTS) count++;
        }
        if(bookshelf.getTile(4,1) != null){
            if(bookshelf.getTile(4, 1).getType() == ItemType.BOOKS) count++;
        }
        if(bookshelf.getTile(4,2) != null){
            if(bookshelf.getTile(4, 2).getType() == ItemType.CATS) count++;
        }

        return pointsConverter(count);
    }

    @Override
    public Bookshelf getPersonalGoal() {
        return personalGoal;
    }
}
