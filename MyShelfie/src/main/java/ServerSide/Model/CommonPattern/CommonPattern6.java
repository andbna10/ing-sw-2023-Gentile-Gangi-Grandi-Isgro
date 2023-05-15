package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;
import ServerSide.Model.ItemType;

import java.util.ArrayList;
import java.util.List;

public class CommonPattern6 extends CommonGoalCard {

    public CommonPattern6() {
        super();
    }

    @Override
    public int getPatternNumber() {return 6;}
    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public boolean validated(Bookshelf bookshelf) {
        boolean ok = false;
        int count = 0;
        List<ItemType> found = new ArrayList<ItemType>();

        for (int i = 0; i < 6; i++) {
            for (int j = 1; j < 5; j++)
                if(!found.contains(bookshelf.getTile(i, j).getType())) found.add(bookshelf.getTile(i, j).getType());
            if(found.size() > 4) count++;
            found.clear();
        }
        if(count > 1) ok = true;
        return ok;
    }
}
