package Server.Model.CommonPattern;

import Server.Model.Bookshelf;
import Server.Model.CommonGoalCard;

public class CommonPattern12 extends CommonGoalCard {

    public CommonPattern12() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public boolean validated(Bookshelf bookshelf) {
        boolean ok = true;

        for(int i = 0; i < 5; i++)
            if(bookshelf.getTile(i + 1, i) == null)
                ok = false;

        return ok;
    }
}
