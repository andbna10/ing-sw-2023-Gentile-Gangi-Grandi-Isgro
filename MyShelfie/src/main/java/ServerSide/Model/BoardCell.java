package ServerSide.Model;

/**
 * Overview: BoardCell class
 * @author Andrea Isgrò
 */
public class BoardCell {
    private Status status;
    private boolean pickable;
    private ItemTile tile;

    /**
     * Overview: BoardCell constructor
     * @author Andrea Isgrò
     * @param status indicates whether the cell can be filled with a tile or not during the game
     */
    public BoardCell(Status status){
        pickable = false;
        this.status = status;
    }

    /**
     * Overview: status getter
     */
    public Status getStatus(){ return status; }

    /**
     * Overview: get tile
     */
    public ItemTile getTile(){ return tile; }

    /**
     * Overview: tile setter
     */
    public void setTile(ItemTile tile){ this.tile = tile; }

    /**
     * Overview: pickable setter
     */
    public void setPickable(boolean arg) {pickable = arg;}

    /**
     * Overview: pickable getter
     */
    public Boolean getPickable(){ return this.pickable; }
}
