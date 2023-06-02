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

    /**
     * Overview: patternNumber getter
     */
    @Override
    public int getPatternNumber() {return 6;}
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    @Override
    public boolean validated(Bookshelf bookshelf) {
        boolean ok = false;
        int count = 0;
        List<ItemType> found = new ArrayList<ItemType>();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++)
                if(bookshelf.getTile(i,j) != null && !found.contains(bookshelf.getTile(i, j).getType()))
                    found.add(bookshelf.getTile(i, j).getType());
            if(found.size() > 4)
                count++;
            found.clear();
        }

        return count > 1;
    }
}
