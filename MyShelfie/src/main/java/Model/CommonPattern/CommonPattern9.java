package Model.CommonPattern;

import Model.Bookshelf;
import Model.CommonGoalCard;
import Model.ItemType;

public class CommonPattern9 extends CommonGoalCard {

    public CommonPattern9(String explanation) {
        super(explanation);
    }

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 0;
        int count = 0;

        for (ItemType type : ItemType.values()) {
            count = 0;
            for (int i = 0; i < 6 - 1; i++)
                for (int j = 0; j < 5 - 1; j++)
                    if (bookshelf.getTile(i, j).getType() == type)
                        count++;
            if(count > 8) ok = 1;
        }

        return ok;
    }
}
