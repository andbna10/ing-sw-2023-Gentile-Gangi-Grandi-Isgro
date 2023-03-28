package Model;

public class ItemTile {
    private int id;
    private ItemType type;
    private Boolean inGame;
    private static int numTiles = 0;

    /**
     * Overview: ItemTile constructor
     */
    public ItemTile(ItemType type){
        this.type = type;
        inGame = false;
        id = numTiles;
        numTiles++;
    }

    /**
     * Overviw: inGame getter
     */
    public Boolean getInGame(){ return inGame; }

    /**
     * Overview: id getter
     */
    public int getId(){ return id; }

    /**
     * Overview: type getter
     */
    public ItemType getType(){ return type; }
}
