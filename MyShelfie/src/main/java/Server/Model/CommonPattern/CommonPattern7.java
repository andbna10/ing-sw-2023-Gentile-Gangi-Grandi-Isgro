package Server.Model.CommonPattern;

import Server.Model.Bookshelf;
import Server.Model.CommonGoalCard;
import Server.Model.ItemType;

import java.util.ArrayList;
import java.util.List;

public class CommonPattern7 extends CommonGoalCard {

    public CommonPattern7() {
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

        for (int i = 0; i < 6; i++) {
            for (int j = 1; j < 5; j++)
                if(!found.contains(bookshelf.getTile(i, j).getType())) found.add(bookshelf.getTile(i, j).getType());
            if(found.size() < 4) count++;
            found.clear();
        }
        if(count > 3) ok = 1;
        return ok;
    }
}
