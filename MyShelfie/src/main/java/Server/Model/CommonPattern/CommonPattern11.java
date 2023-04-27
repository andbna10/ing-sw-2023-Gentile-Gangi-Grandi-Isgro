package Server.Model.CommonPattern;

import Server.Model.Bookshelf;
import Server.Model.CommonGoalCard;

public class CommonPattern11 extends CommonGoalCard {

    public CommonPattern11() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public boolean validated(Bookshelf bookshelf) {
        int ok = 2;
        boolean ret = false;

        for (int i = 0; i < 4; i++)
            if(bookshelf.getTile(i, i).getType() != bookshelf.getTile(i + 1, i + 1).getType()) {
                ok--;
                break;
            }
        for (int i = 0; i < 4; i++)
            if(bookshelf.getTile(i + 1, i).getType() != bookshelf.getTile(i + 2, i + 1).getType()) {
                ok--;
                break;
            }

        if(ok > 0) ret = true;
        return ret;
    }
}
