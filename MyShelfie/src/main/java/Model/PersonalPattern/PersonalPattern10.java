package Model.PersonalPattern;

import Model.Bookshelf;
import Model.ItemType;
import Model.PersonalGoalCard;

public class PersonalPattern10 extends PersonalGoalCard {

    public PersonalPattern10(String explanation, int[] points, ItemType[][] position) {
        super(explanation, points, position);
    }

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 1;

        if(bookshelf.getTile(0, 4).getType() != ItemType.TROPHIES ||
                bookshelf.getTile(1, 1).getType() != ItemType.GAMES ||
                bookshelf.getTile(2, 0).getType() != ItemType.BOOKS ||
                bookshelf.getTile(3, 3).getType() != ItemType.CATS ||
                bookshelf.getTile(4, 1).getType() != ItemType.FRAMES ||
                bookshelf.getTile(5, 3).getType() != ItemType.PLANTS)
            ok = 0;

        return ok;
    }
}
