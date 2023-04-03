package Model.CommonPattern;

import Model.Bookshelf;
import Model.CommonGoalCard;
import Model.ItemType;
import java.util.ArrayList;
import java.util.List;

public class CommonPattern5 extends CommonGoalCard {

    public CommonPattern5() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public int validated(Bookshelf bookshelf) {
        int ok = 0;
        int count = 0;
        List<ItemType> found = new ArrayList<ItemType>();

        for (int j = 0; j < 5; j++) {
            for (int i = 1; i < 6; i++)
                if(!found.contains(bookshelf.getTile(i, j).getType())) found.add(bookshelf.getTile(i, j).getType());
            if(found.size() < 4) count++;
            found.clear();
        }
        if(count > 2) ok = 1;
        return ok;
    }
}
