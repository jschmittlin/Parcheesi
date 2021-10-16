public class Rider {
    private String name;
    private Dice dice;
    private Position position;

    /**
     * Constructor : Instance with name and position
     * @param name - name of rider
     * @param p - position
     */
    public Rider(String name, Position p) {
        this.name = name;
        this.position = p;
        dice = new Dice();
    }

    /**
     * Get value of dice
     * @return int
     */
    public int getDice() {
        return dice.getValue();
    }

    /**
     * Simulate a roll dice
     */
    public void rollDice() {
        dice.roll();
    }

    /**
     * Get name of rider
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get position of rider
     * @return Position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Set position to rider
     * @param p - position
     */
    public void setPosition(Position p) {
        this.position = p;
    }

    /**
     * Print rider (first letter of name)
     * @return String
     */
    public String toString() {
        return name.substring(0, 1);
    }
}
