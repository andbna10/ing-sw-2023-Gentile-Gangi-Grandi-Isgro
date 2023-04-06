package Server.Model.CommonPattern;

import Server.Model.Bookshelf;
import Server.Model.CommonGoalCard;

public class CommonPattern8 extends CommonGoalCard {

    public CommonPattern8() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public int validated(Bookshelf bookshelf) {
        int ok = 0;
        int count = 0;

        if(bookshelf.getTile(0, 0).getType() == bookshelf.getTile(0, 4).getType() &&
                bookshelf.getTile(0, 4).getType() == bookshelf.getTile(5, 0).getType() &&
                bookshelf.getTile(5, 0).getType() == bookshelf.getTile(5, 4).getType())
            ok = 1;

        return ok;
    }
}
