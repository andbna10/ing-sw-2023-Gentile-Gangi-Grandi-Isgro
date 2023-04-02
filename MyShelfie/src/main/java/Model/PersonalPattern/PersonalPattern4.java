package Model.PersonalPattern;

import Model.Bookshelf;
import Model.ItemType;
import Model.PersonalGoalCard;

public class PersonalPattern4 extends PersonalGoalCard {

    public PersonalPattern4(String explanation, int[] points, ItemType[][] position) {
        super(explanation, points, position);
    }

    /**
     * Overview: controls player's bookshelf whether the combo as been achieved  and returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 1;

        if(bookshelf.getTile(0, 5).getType() != ItemType.GAMES ||
                bookshelf.getTile(2, 0).getType() != ItemType.TROPHIES ||
                bookshelf.getTile(2, 2).getType() != ItemType.FRAMES ||
                bookshelf.getTile(3, 3).getType() != ItemType.PLANTS ||
                bookshelf.getTile(4, 1).getType() != ItemType.BOOKS ||
                bookshelf.getTile(4, 2).getType() != ItemType.CATS)
            ok = 0;

        return ok;
    }
}
