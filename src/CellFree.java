public class CellFree extends Cell {

    /**
     * Constructor : Instance with x coordinate and y coordinate
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public CellFree(int x,int y) {
        super(x, y);
    }

    /**
     * Method of cell
     * @param r - rider
     * @return boolean - if rider can move or not
     */
    public boolean process(Rider r) {
        return true;
    }

    /**
     * Get name of cell
     * @return String
     */
    public String getName() {
        return "case libre";
    }

    /**
     * Print cell
     * @return String
     */
    public String toString() {
        return ".";
    }
}
