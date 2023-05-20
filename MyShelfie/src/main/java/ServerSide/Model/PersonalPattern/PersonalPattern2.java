package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import ServerSide.Model.PersonalGoalCard;

public class PersonalPattern2 extends PersonalGoalCard {
    private Bookshelf personalGoal = new Bookshelf();
    public PersonalPattern2() {
        super();
        personalGoal.setTile(1,1,ItemType.PLANTS);
        personalGoal.setTile(2,0,ItemType.CATS);
        personalGoal.setTile(2,2,ItemType.GAMES);
        personalGoal.setTile(3,4,ItemType.BOOKS);
        personalGoal.setTile(4,3,ItemType.TROPHIES);
        personalGoal.setTile(5,4,ItemType.FRAMES);
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns points
     */
    public int validated(Bookshelf bookshelf) {
        int count = 0;

        if(bookshelf.getTile(1,1) != null){
            if(bookshelf.getTile(1, 1).getType() == ItemType.PLANTS) count++;
        }
        if(bookshelf.getTile(2,0) != null){
            if(bookshelf.getTile(2, 0).getType() == ItemType.CATS) count++;
        }
        if(bookshelf.getTile(2,2) != null){
            if(bookshelf.getTile(2, 2).getType() == ItemType.GAMES) count++;
        }
        if(bookshelf.getTile(3,4) != null){
            if(bookshelf.getTile(3, 4).getType() == ItemType.BOOKS) count++;
        }
        if(bookshelf.getTile(4,3) != null){
            if(bookshelf.getTile(4, 3).getType() == ItemType.TROPHIES) count++;
        }
        if(bookshelf.getTile(5,4) != null){
            if(bookshelf.getTile(5, 4).getType() == ItemType.FRAMES) count++;
        }

        return pointsConverter(count);
    }

    @Override
    public Bookshelf getPersonalGoal() {
        return personalGoal;
    }
}
