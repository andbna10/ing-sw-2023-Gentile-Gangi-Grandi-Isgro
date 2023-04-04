package Model.PersonalPattern;

import Model.Bookshelf;
import Model.ItemType;
import Model.PersonalGoalCard;

public class PersonalPattern2 extends PersonalGoalCard {

    public PersonalPattern2() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns points
     */
    public int validated(Bookshelf bookshelf) {
        int count = 0;

        if(bookshelf.getTile(1, 1).getType() != ItemType.PLANTS) count++;
        if(bookshelf.getTile(2, 0).getType() != ItemType.CATS) count++;
        if(bookshelf.getTile(2, 2).getType() != ItemType.GAMES) count++;
        if(bookshelf.getTile(3, 4).getType() != ItemType.BOOKS) count++;
        if(bookshelf.getTile(4, 3).getType() != ItemType.TROPHIES) count++;
        if(bookshelf.getTile(5, 4).getType() != ItemType.FRAMES) count++;

        return pointsConverter(count);
    }
}
