package Model.PersonalPattern;

import Model.Bookshelf;
import Model.ItemType;
import Model.PersonalGoalCard;

public class PersonalPattern1 extends PersonalGoalCard {

    public PersonalPattern1() {
        super();
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
}
