public class Dice {
    private int value;

    /**
     * Constructor : Instance with value = 0
     */
    public Dice() {
        value = 0;
    }

    /**
     * Set value to random number between 1 to 6
     */
    public void roll() {
        value = (int)(Math.random() * 6) + 1;
    }

    /**
     * Get value
     * @return int
     */
    public int getValue() {
        return value;
    }
}
