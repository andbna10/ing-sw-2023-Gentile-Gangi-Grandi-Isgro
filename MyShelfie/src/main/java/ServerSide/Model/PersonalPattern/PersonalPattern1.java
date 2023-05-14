package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import ServerSide.Model.PersonalGoalCard;

public class PersonalPattern1 extends PersonalGoalCard {
    private Bookshelf personalGoal = new Bookshelf();
    public PersonalPattern1() {
        super();
        personalGoal.setTile(0,0,ItemType.PLANTS);
        personalGoal.setTile(0,2,ItemType.FRAMES);
        personalGoal.setTile(1,4,ItemType.CATS);
        personalGoal.setTile(2,3,ItemType.BOOKS);
        personalGoal.setTile(3,1,ItemType.GAMES);
        personalGoal.setTile(5,2,ItemType.TROPHIES);
    }


    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns points
     */
    public int validated(Bookshelf bookshelf) {
        int count = 0;

        if(bookshelf.getTile(0, 0).getType() == ItemType.PLANTS) count++;
        if(bookshelf.getTile(0, 2).getType() == ItemType.FRAMES) count++;
        if(bookshelf.getTile(1, 4).getType() == ItemType.CATS) count++;
        if(bookshelf.getTile(2, 3).getType() == ItemType.BOOKS) count++;
        if(bookshelf.getTile(3, 1).getType() == ItemType.GAMES) count++;
        if(bookshelf.getTile(5, 2).getType() == ItemType.TROPHIES) count++;

        return pointsConverter(count);
    }

    @Override
    public Bookshelf getPersonalGoal(){
        return personalGoal;
    }
}
