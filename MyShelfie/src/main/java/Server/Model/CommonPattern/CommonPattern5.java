package Server.Model.CommonPattern;

import Server.Model.Bookshelf;
import Server.Model.CommonGoalCard;
import Server.Model.ItemType;
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
    public boolean validated(Bookshelf bookshelf) {
        boolean ok = false;
        int count = 0;
        List<ItemType> found = new ArrayList<ItemType>();

        for (int j = 0; j < 5; j++) {
            for (int i = 1; i < 6; i++)
                if(!found.contains(bookshelf.getTile(i, j).getType())) found.add(bookshelf.getTile(i, j).getType());
            if(found.size() < 4) count++;
            found.clear();
        }
        if(count > 2) ok = true;
        return ok;
    }
}
