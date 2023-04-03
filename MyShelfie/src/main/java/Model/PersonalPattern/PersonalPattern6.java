package Model.PersonalPattern;

import Model.Bookshelf;
import Model.ItemType;
import Model.PersonalGoalCard;

public class PersonalPattern6 extends PersonalGoalCard {

    public PersonalPattern6() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 1;

        if(bookshelf.getTile(0, 2).getType() != ItemType.TROPHIES ||
                bookshelf.getTile(0, 4).getType() != ItemType.CATS ||
                bookshelf.getTile(2, 3).getType() != ItemType.BOOKS ||
                bookshelf.getTile(4, 1).getType() != ItemType.GAMES ||
                bookshelf.getTile(4, 3).getType() != ItemType.FRAMES ||
                bookshelf.getTile(5, 0).getType() != ItemType.PLANTS)
            ok = 0;

        return ok;
    }
}
