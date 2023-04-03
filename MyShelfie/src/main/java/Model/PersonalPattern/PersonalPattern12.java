package Model.PersonalPattern;

import Model.Bookshelf;
import Model.ItemType;
import Model.PersonalGoalCard;

public class PersonalPattern12 extends PersonalGoalCard {

    public PersonalPattern12() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 1;

        if(bookshelf.getTile(0, 2).getType() != ItemType.BOOKS ||
                bookshelf.getTile(1, 1).getType() != ItemType.PLANTS ||
                bookshelf.getTile(2, 2).getType() != ItemType.FRAMES ||
                bookshelf.getTile(3, 3).getType() != ItemType.TROPHIES ||
                bookshelf.getTile(4, 4).getType() != ItemType.GAMES ||
                bookshelf.getTile(5, 0).getType() != ItemType.CATS)
            ok = 0;

        return ok;
    }
}
