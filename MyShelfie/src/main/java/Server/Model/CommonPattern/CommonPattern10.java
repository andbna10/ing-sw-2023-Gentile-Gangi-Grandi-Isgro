package Server.Model.CommonPattern;

import Server.Model.Bookshelf;
import Server.Model.CommonGoalCard;

public class CommonPattern10 extends CommonGoalCard {

    public CommonPattern10() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public boolean validated(Bookshelf bookshelf) {
        boolean ok = false;

        for (int i = 1; i < 6 - 1; i++)
            for(int j = 1; j < 5 - 1; j++)
                if(bookshelf.getTile(i, j).getType() == bookshelf.getTile(i - 1, j - 1).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i - 1, j + 1).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i + 1, j - 1).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i + 1, j + 1).getType())
                    ok = true;

        return ok;
    }
}
