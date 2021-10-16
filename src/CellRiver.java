public class CellRiver extends Cell {

    /**
     * Constructor : Instance with x coordinate and y coordinate
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public CellRiver(int x, int y) {
        super(x, y);
    }

    /**
     * Method of cell - need to make a even number to continue
     * @param r - rider
     * @return boolean - if rider can move or not
     */
    public boolean process(Rider r) {
        if (r.getDice() % 2 == 0) return true; // even number
        return false;
    }

    /**
     * Get name of cell
     * @return String
     */
    public String getName() {
        return "case rivière";
    }

    /**
     * Print cell
     * @return String
     */
    public String toString() {
        return "~";
    }
}
