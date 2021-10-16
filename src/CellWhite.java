public class CellWhite extends Cell {

    /**
     * Constructor : Instance with x coordinate and y coordinate
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public CellWhite(int x, int y) {
        super(x, y);
    }

    /**
     * Method of cell
     * @param r - rider
     * @return boolean - if rider can move or not
     */
    public boolean process(Rider r) {
        return false;
    }

    /**
     * Get name of cell
     * @return String
     */
    public String getName() {
        return "case blanche";
    }

    /**
     * Print cell
     * @return String
     */
    public String toString() {
        return " ";
    }
}
