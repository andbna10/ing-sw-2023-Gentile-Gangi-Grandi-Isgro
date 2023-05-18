package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;
import ServerSide.Model.ItemType;

import java.util.ArrayList;
import java.util.List;

public class CommonPattern2 extends CommonGoalCard {

    public CommonPattern2() {
        super();
    }

    @Override
    public int getPatternNumber() {return 2;}
    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public boolean validated(Bookshelf bookshelf) {
        int count = 0;
        boolean flag = true;
        List<ItemType> found = new ArrayList<ItemType>();

        for (int j = 0; j < 5; j++, flag = true) {
            for (int i = 0; i < 6 && flag; i++) {
                if(bookshelf.getTile(i,j) != null && !found.contains(bookshelf.getTile(i, j).getType())){
                    found.add(bookshelf.getTile(i, j).getType());
                } else flag = false;
            }
            if(flag)
                count++;
            found.clear();
        }

        return count > 1;
    }
}
