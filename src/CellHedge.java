public class CellHedge extends Cell {

    /**
     * Constructor : Instance with x coordinate and y coordinate
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public CellHedge(int x, int y) {
        super(x, y);
    }

    /**
     * Method of cell, need to make a odd number to continue
     * @param r - rider
     * @return boolean - if rider can move or not
     */
    public boolean process(Rider r) {
        if (r.getDice() % 2 == 1) return true; // odd number
        return false;
    }

    /**
     * Get name of cell
     * @return String
     */
    public String getName() {
        return "case haie";
    }

    /**
     * Print cell
     * @return String
     */
    public String toString() {
        return "|";
    }
}
