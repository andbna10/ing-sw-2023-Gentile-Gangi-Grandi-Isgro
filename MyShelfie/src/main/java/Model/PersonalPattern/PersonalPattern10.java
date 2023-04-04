package Model.PersonalPattern;

import Model.Bookshelf;
import Model.ItemType;
import Model.PersonalGoalCard;

public class PersonalPattern10 extends PersonalGoalCard {

    public PersonalPattern10() {
        super();
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
}
