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
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public int validated(Bookshelf bookshelf) {
        int ok = 1;

        if(bookshelf.getTile(0, 0).getType() != ItemType.PLANTS ||
                bookshelf.getTile(0, 2).getType() != ItemType.FRAMES ||
                bookshelf.getTile(1, 4).getType() != ItemType.CATS ||
                bookshelf.getTile(2, 3).getType() != ItemType.BOOKS ||
                bookshelf.getTile(3, 1).getType() != ItemType.GAMES ||
                bookshelf.getTile(5, 2).getType() != ItemType.TROPHIES)
            ok = 0;

        return ok;
    }
}
