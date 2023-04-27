package Server.Model.CommonPattern;

import Server.Model.Bookshelf;
import Server.Model.CommonGoalCard;
import Server.Model.ItemType;

public class CommonPattern9 extends CommonGoalCard {

    public CommonPattern9() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public boolean validated(Bookshelf bookshelf) {
        boolean ok = false;
        int count = 0;

        for (ItemType type : ItemType.values()) {
            count = 0;
            for (int i = 0; i < 6 - 1; i++)
                for (int j = 0; j < 5 - 1; j++)
                    if (bookshelf.getTile(i, j).getType() == type)
                        count++;
            if(count > 8) ok = true;
        }

        return ok;
    }
}
