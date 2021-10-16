public class CellHole extends Cell {
    private int turn = 3; // Turn stuck in this cell
    private String lastRider;

    /**
     * Constructor : Instance with x coordinate and y coordinate
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public CellHole(int x, int y) {
        super(x, y);
    }

    /**
     * Method of cell - stuck in this case 3 turn
     * @param r - rider
     * @return boolean - if rider can move or not
     */
    public boolean process(Rider r) {
        String currentRider = r.toString();
        // Reset turn if a new rider are in this cell
        if (! r.toString().equals(lastRider))
        {
            lastRider = currentRider;
            turn = 3;
        }
        if (turn != 0)
        {
            --turn;
            return false;
        }
        turn = 3;
        return true;
    }

    /**
     * Get turn
     * @return int
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Get name of cell
     * @return String
     */
    public String getName() {
        return "case trou";
    }

    /**
     * Print cell
     * @return String
     */
    public String toString() {
        return "@";
    }
}
