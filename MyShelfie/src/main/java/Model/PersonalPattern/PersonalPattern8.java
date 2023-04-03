package Model.PersonalPattern;

import Model.Bookshelf;
import Model.ItemType;
import Model.PersonalGoalCard;

public class PersonalPattern8 extends PersonalGoalCard {

    public PersonalPattern8() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 1;

        if(bookshelf.getTile(0, 4).getType() != ItemType.FRAMES ||
                bookshelf.getTile(1, 1).getType() != ItemType.CATS ||
                bookshelf.getTile(2, 2).getType() != ItemType.TROPHIES ||
                bookshelf.getTile(3, 0).getType() != ItemType.PLANTS ||
                bookshelf.getTile(4, 3).getType() != ItemType.BOOKS ||
                bookshelf.getTile(5, 3).getType() != ItemType.GAMES)
            ok = 0;

        return ok;
    }
}
