package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import ServerSide.Model.PersonalGoalCard;

public class PersonalPattern10 extends PersonalGoalCard {
    private Bookshelf personalGoal = new Bookshelf();
    public PersonalPattern10() {
        super();
        personalGoal.setTile(0,4,ItemType.TROPHIES);
        personalGoal.setTile(1,1,ItemType.GAMES);
        personalGoal.setTile(2,0,ItemType.BOOKS);
        personalGoal.setTile(3,3,ItemType.CATS);
        personalGoal.setTile(4,1,ItemType.FRAMES);
        personalGoal.setTile(5,3,ItemType.PLANTS);
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns points
     */
    public int validated(Bookshelf bookshelf) {
        int count = 0;

        if(bookshelf.getTile(0, 4).getType() != ItemType.TROPHIES) count++;
        if(bookshelf.getTile(1, 1).getType() != ItemType.GAMES) count++;
        if(bookshelf.getTile(2, 0).getType() != ItemType.BOOKS) count++;
        if(bookshelf.getTile(3, 3).getType() != ItemType.CATS) count++;
        if(bookshelf.getTile(4, 1).getType() != ItemType.FRAMES) count++;
        if(bookshelf.getTile(5, 3).getType() != ItemType.PLANTS) count++;

        return pointsConverter(count);
    }

    @Override
    public Bookshelf getPersonalGoal() {
        return personalGoal;
    }
}
