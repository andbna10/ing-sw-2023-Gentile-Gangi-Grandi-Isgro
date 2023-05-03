package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;

public class CommonPattern3 extends CommonGoalCard {

    public CommonPattern3() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public boolean validated(Bookshelf bookshelf) {
        boolean ok = false;
        int count = 0;
        int pairs = 0;

        for (int j = 0; j < 5; j++, count = 0) {
            for (int i = 0; i < 6 - 1; i++)
                if (bookshelf.getTile(i, j).getType() == bookshelf.getTile(i + 1, j).getType())
                    count++;
                else count = 0;
            if (count > 3) pairs++;
        }

        if (pairs > 3) ok = true;
        return ok;
    }
}
