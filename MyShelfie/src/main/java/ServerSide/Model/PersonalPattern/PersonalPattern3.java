package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import ServerSide.Model.PersonalGoalCard;

public class PersonalPattern3 extends PersonalGoalCard {
    private Bookshelf personalGoal = new Bookshelf();
    public PersonalPattern3() {
        super();
        personalGoal.setTile(1,0,ItemType.FRAMES);
        personalGoal.setTile(1,3,ItemType.GAMES);
        personalGoal.setTile(2,2,ItemType.PLANTS);
        personalGoal.setTile(3,4,ItemType.TROPHIES);
        personalGoal.setTile(5,0,ItemType.BOOKS);
        personalGoal.setTile(3,1,ItemType.CATS);
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns points
     */
    public int validated(Bookshelf bookshelf) {
        int count = 0;
        if(bookshelf.getTile(1,0) != null){
            if(bookshelf.getTile(1, 0).getType() == ItemType.FRAMES) count++;
        }
        if(bookshelf.getTile(1,3) != null){
            if(bookshelf.getTile(1, 3).getType() == ItemType.GAMES) count++;
        }
        if(bookshelf.getTile(2,2) != null){
            if(bookshelf.getTile(2, 2).getType() == ItemType.PLANTS) count++;
        }
        if(bookshelf.getTile(3,4) != null){
            if(bookshelf.getTile(3, 4).getType() == ItemType.TROPHIES) count++;
        }
        if(bookshelf.getTile(5,0) != null){
            if(bookshelf.getTile(5, 0).getType() == ItemType.BOOKS) count++;
        }
        if(bookshelf.getTile(3,1) != null){
            if(bookshelf.getTile(3, 1).getType() == ItemType.CATS) count++;
        }


        return pointsConverter(count);
    }

    @Override
    public Bookshelf getPersonalGoal() {
        return personalGoal;
    }
}