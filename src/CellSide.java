public class CellSide extends Cell {

    /**
     * Constructor : Instance with x coordinate and y coordinate
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public CellSide(int x, int y) {
        super(x, y);
    }

    /**
     * Method of cell - need to make 6 to continue
     * @param r - rider
     * @return boolean - if rider can move or not
     */
    public boolean process(Rider r) {
        if (r.getDice() == 6) return true;
        return false;
    }

    /**
     * Get name of cell
     * @return String
     */
    public String getName() {
        return "dans l'écurie";
    }

    /**
     * Print cell
     * @return String
     */
    public String toString() {
        return "+";
    }
}
