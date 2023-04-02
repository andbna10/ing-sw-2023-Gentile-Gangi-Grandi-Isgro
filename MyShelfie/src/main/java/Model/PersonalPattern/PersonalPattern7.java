package Model.PersonalPattern;

import Model.Bookshelf;
import Model.ItemType;
import Model.PersonalGoalCard;

public class PersonalPattern7 extends PersonalGoalCard {

    public PersonalPattern7(String explanation, int[] points, ItemType[][] position) {
        super(explanation, points, position);
    }

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 1;

        if(bookshelf.getTile(0, 0).getType() != ItemType.CATS ||
                bookshelf.getTile(1, 3).getType() != ItemType.FRAMES ||
                bookshelf.getTile(2, 1).getType() != ItemType.PLANTS ||
                bookshelf.getTile(3, 0).getType() != ItemType.TROPHIES ||
                bookshelf.getTile(4, 4).getType() != ItemType.GAMES ||
                bookshelf.getTile(5, 2).getType() != ItemType.BOOKS)
            ok = 0;

        return ok;
    }
}
