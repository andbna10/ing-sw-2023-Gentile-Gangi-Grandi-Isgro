package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import ServerSide.Model.PersonalGoalCard;

public class PersonalPattern6 extends PersonalGoalCard {
    private Bookshelf personalGoal = new Bookshelf();
    public PersonalPattern6() {
        super();
        personalGoal.setTile(0,2,ItemType.TROPHIES);
        personalGoal.setTile(0,4,ItemType.CATS);
        personalGoal.setTile(2,3,ItemType.BOOKS);
        personalGoal.setTile(4,1,ItemType.GAMES);
        personalGoal.setTile(4,3,ItemType.FRAMES);
        personalGoal.setTile(5,0,ItemType.PLANTS);
    }

    @Override
    /**
     * Overview: patternNumber getter
     */
    public int getPatternNumber() {return 6;}

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns points
     */
    public int validated(Bookshelf bookshelf) {
        int count = 0;

        if(bookshelf.getTile(0,2) != null){
            if(bookshelf.getTile(0, 2).getType() == ItemType.TROPHIES) count++;
        }
        if(bookshelf.getTile(0,4) != null){
            if(bookshelf.getTile(0, 4).getType() == ItemType.CATS) count++;
        }
        if(bookshelf.getTile(2,3) != null){
            if(bookshelf.getTile(2, 3).getType() == ItemType.BOOKS) count++;
        }
        if(bookshelf.getTile(4,1) != null){
            if(bookshelf.getTile(4, 1).getType() == ItemType.GAMES) count++;
        }
        if(bookshelf.getTile(4,3) != null){
            if(bookshelf.getTile(4, 3).getType() == ItemType.FRAMES) count++;
        }
        if(bookshelf.getTile(5,0) != null){
            if(bookshelf.getTile(5, 0).getType() == ItemType.PLANTS) count++;
        }

        return pointsConverter(count);
    }

    @Override
    public Bookshelf getPersonalGoal() {
        return personalGoal;
    }
}
