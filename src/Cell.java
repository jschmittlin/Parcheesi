public abstract class Cell implements Questionable {
    private Position position;

    /**
     * Constructor : Instance with x coordinate and y coordinate
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public Cell(int x, int y) {
        this.position = new Position(x, y);
    }

    /**
     * Get position of cell
     * @return Position of cell
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Method of cell
     * @param r - rider
     * @return boolean - if rider can move or not
     */
    public abstract boolean process(Rider r);

    /**
     * Get name of cell
     * @return String
     */
    public abstract String getName();

    /**
     * Print cell
     * @return String
     */
    public abstract String toString();
}
