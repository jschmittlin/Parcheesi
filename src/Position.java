public class Position {
    private int x;
    private int y;

    /**
     * Constructor : Instance with x coordinate and y coordinate
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get x coordinate
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * Get y coordinate
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * Method equals between 2 position
     * @param p - position
     * @return true if is equals, false if is not
     */
    public boolean equals(Position p) {
        return x == p.x && y == p.y;
    }

    /**
     * Print position
     * @return String
     */
    public String toString() {
        return "[" + x + "," + y + ']';
    }
}
