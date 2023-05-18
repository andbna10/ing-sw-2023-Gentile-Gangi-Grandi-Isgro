package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;
import ServerSide.Model.ItemType;

import java.util.ArrayList;
import java.util.List;

public class CommonPattern7 extends CommonGoalCard {

    public CommonPattern7() {
        super();
    }

    @Override
    public int getPatternNumber() {return 7;}
    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public boolean validated(Bookshelf bookshelf) {
        boolean flag = false;
        int count = 0;
        List<ItemType> found = new ArrayList<ItemType>();

        for (int i = 0; i < 6; i++, flag = false) {
            for (int j = 0; j < 5; j++) {
                if(bookshelf.getTile(i,j) == null){
                    flag = true;
                } else if(!found.contains(bookshelf.getTile(i, j).getType()))
                    found.add(bookshelf.getTile(i, j).getType());
            }
            if(found.size() < 4 && !flag)
                count++;
            found.clear();
        }
        return count > 3;
    }
}
