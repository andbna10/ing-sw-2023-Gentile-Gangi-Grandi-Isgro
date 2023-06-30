package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;

public class CommonPattern3 extends CommonGoalCard {

    public CommonPattern3() {
        super();
    }

    /**
     * Overview: patternNumber getter
     */
    @Override
    public int getPatternNumber() {return 3;}

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf
     * @author Mirko Gentile
     * @author Simone Grandi
     * @author Francesco Gangi
     * @param bookshelf bookshelf to be checked
     * @return (true) if accomplished, (false) if not
     */
    @Override
    public boolean validated(Bookshelf bookshelf) {
        int count = 0;

        //horizontal
        for(int i=0; i<6; i++){
            for(int j=0; j<2; j++)
                if(     bookshelf.getTile(i, j)!=null &&
                        bookshelf.getTile(i, 1+j)!=null &&
                        bookshelf.getTile(i, 2+j)!=null &&
                        bookshelf.getTile(i, 3+j)!=null &&

                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i , 1+j).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i , 2+j).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i , 3+j).getType() )
                { count++;}

        }

        //vertical
        for(int j=0; j<5; j++){
            for(int i=0; i<3; i++)
                if(     bookshelf.getTile(i, j)!=null &&
                        bookshelf.getTile(i+1, j)!=null &&
                        bookshelf.getTile(i+2, j)!=null &&
                        bookshelf.getTile(i+3, j)!=null &&

                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i+1 , j).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i+2 , j).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i+3 , j).getType() )
                {count++;}

        }

        return count>3;
    }
}
