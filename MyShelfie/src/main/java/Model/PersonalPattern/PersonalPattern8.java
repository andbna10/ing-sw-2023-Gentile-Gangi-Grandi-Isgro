package Model.PersonalPattern;

import Model.Bookshelf;
import Model.ItemType;
import Model.PersonalGoalCard;

public class PersonalPattern8 extends PersonalGoalCard {

    public PersonalPattern8(String explanation, int[] points, ItemType[][] position) {
        super(explanation, points, position);
    }

    /**
     * Overview: controls player's bookshelf whether the combo as been achieved  and returns 1
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
