package ServerSide.Model;

/**
 * Overview: BoardCell class
 * @author: Andrea Isgrò
 */
public class BoardCell {
    private Status status;
    private boolean pickable;
    private ItemTile tile;

    /**
     * Overview: BoardCell constructor
     * @author Andrea Isgrò
     * @param status indicates whether the cell is fillable with a tile or not during the game
     */
    public BoardCell(Status status){
        pickable = false;
        this.status = status;
    }

    /**
     * Overview: status getter
     * @author Andrea Isgrò
     * @return the status of the BoardCell
     */
    public Status getStatus(){ return status; }

    /**
     * Overview: get tile
     * @author Andrea Isgrò
     * @return the tile associated to the cell
     */
    public ItemTile getTile(){ return tile; }

    /**
     * Overview: tile setter
     * @author Andrea Isgrò
     * @param tile ItemTile which will be associated to the cell
     */
    public void setTile(ItemTile tile){ this.tile = tile; }

    /**
     * Overview: pickable setter
     * @author Andrea Isgrò
     * @param arg boolean that marks the tile associated to the cell as pickable
     */
    public void setPickable(boolean arg) {pickable = arg;}

    /**
     * Overview: pickable getter
     * @author Andrea Isgrò
     * @return the pickable status
     */
    public Boolean getPickable(){ return this.pickable; }
}
