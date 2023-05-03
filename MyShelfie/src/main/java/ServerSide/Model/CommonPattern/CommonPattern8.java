package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;

public class CommonPattern8 extends CommonGoalCard {

    public CommonPattern8() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public boolean validated(Bookshelf bookshelf) {
        boolean ok = false;

        if(bookshelf.getTile(0, 0).getType() == bookshelf.getTile(0, 4).getType() &&
                bookshelf.getTile(0, 4).getType() == bookshelf.getTile(5, 0).getType() &&
                bookshelf.getTile(5, 0).getType() == bookshelf.getTile(5, 4).getType())
            ok = true;

        return ok;
    }
}
