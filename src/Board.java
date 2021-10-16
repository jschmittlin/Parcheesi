import java.util.*;

public class Board {
    private static ArrayList<ArrayList<Cell>> board;
    private static ArrayList<Rider> riders;
    private final int length;
    private final int space = 4; // Space between cell end

    /**
     * Constructor : Instance with length of board
     * @param length - length of board
     */
    public Board(int length) {
        board = new ArrayList<>();
        riders = new ArrayList<>();
        this.length = length + 2; // +2 for border (Cell side)

        initBoard(); // Call function for initiate board
        setObstacles(); // Call function for add obstacles
    }

    /**
     * Initiate board and riders without obstacle
     */
    private void initBoard() {
        /* Initialization of riders */
        Rider red = new Rider("ROUGE", new Position(1,0));
        Rider blue = new Rider("BLEU", new Position(length - 2,6));
        riders.add(red);
        riders.add(blue);

        /* Initialization of cells */
        int alpha = (length - space - 2) / 2; // method to know distance for the red end cell

        CellStart startRed = new CellStart(1,1);
        CellStart startBlue = new CellStart(length - 2,5);

        CellEnd endRed = new CellEnd(alpha,3);
        CellEnd endBlue = new CellEnd(alpha + space + 1,3);

        /* Initialization of board */
        /* Initialization of row no.1 */
        ArrayList<Cell> row0 = new ArrayList<>();
        for (int i = 0; i < length; ++i)
            row0.add(new CellSide(i,0));

        /* Initialization of row no.2 */
        ArrayList<Cell> row1 = new ArrayList<>();
        row1.add(new CellSide(0,1));
        row1.add(startRed);
        for (int i = 2; i < length - 1; ++i)
            row1.add(new CellFree(i,1));
        row1.add(new CellSide(length - 1,1));

        /* Initialization of row no.3 */
        ArrayList<Cell> row2 = new ArrayList<>();
        row2.add(new CellSide(0,2));
        for (int i = 1; i < length - 1; ++i)
            row2.add(new CellWhite(i, 2));
        row2.add(new CellSide(length - 1,2));

        /* Initialization of row no.4 */
        ArrayList<Cell> row3 = new ArrayList<>();
        row3.add(new CellSide(0,3));
        for (int i = 1; i < alpha; ++i)
            row3.add(new CellFree(i, 3));
        row3.add(endRed);
        for (int i = alpha; i < alpha + space; ++i)
            row3.add(new CellWhite(i + 1, 3));
        row3.add(endBlue);
        for (int i = alpha + space + 2; i < length - 1; ++i)
            row3.add(new CellFree(i, 3));
        row3.add(new CellSide(length - 1,3));

        /* Initialization of row no.5 */
        ArrayList<Cell> row4 = new ArrayList<>();
        row4.add(new CellSide(0,4));
        for (int i = 1; i < length - 1; ++i)
            row4.add(new CellWhite(i, 4));
        row4.add(new CellSide(length - 1,4));

        /* Initialization of row no.6 */
        ArrayList<Cell> row5 = new ArrayList<>();
        row5.add(new CellSide(0,5));
        for (int i = 1; i < length - 2; ++i)
            row5.add(new CellFree(i, 5));
        row5.add(startBlue);
        row5.add(new CellSide(length - 1,5));

        /* Initialization of row no.7 */
        ArrayList<Cell> row6 = new ArrayList<>();
        for (int i = 0; i < length; ++i)
            row6.add(new CellSide(i,6));

        board.add(row0);
        board.add(row1);
        board.add(row2);
        board.add(row3);
        board.add(row4);
        board.add(row5);
        board.add(row6);
    }

    /**
     * Set obstacles on board
     */
    private void setObstacles() {
        ArrayList<Cell> obstacles = new ArrayList<>();

        /* Initialization of obstacles */
        CellHole hole = new CellHole(4,1);
        CellHedge hedge = new CellHedge(5, 5);
        CellRiver river = new CellRiver(10, 5);

        obstacles.add(hole);
        obstacles.add(hedge);
        obstacles.add(river);

        /* Set obstacles on board */
        for (Cell c : obstacles)
            board.get(c.getPosition().getY()).set(c.getPosition().getX(), c);
    }

    /**
     * Get cell
     * @param x - x coordinate
     * @param y - y coordinate
     * @return Cell
     */
    public Cell getCell(int x, int y) {
        return board.get(x).get(y);
    }

    /**
     * Get cell
     * @param p - position
     * @return Cell
     */
    public Cell getCell(Position p) {
        return getCell(p.getY(), p.getX());
    }

    /**
     * Get length
     * @return int
     */
    public int getLength() {
        return length;
    }

    /**
     * Get riders
     * @return ArrayList of Rider
     */
    public ArrayList<Rider> getRiders() {
        return riders;
    }

    /**
     * Get a new rider position with the value of dice roll
     * @param r - rider
     * @return New rider position
     */
    private Position nextPosition(Rider r) {
        Position nextPosition = r.getPosition();
        int x = nextPosition.getX();
        int y = nextPosition.getY();

        if (getCell(r.getPosition()) instanceof CellSide)
        {
            if (r.toString().equals(riders.get(0).toString())) ++y;
            if (r.toString().equals(riders.get(1).toString())) --y;
            return new Position(x, y);
        }

        int value = r.getDice();
        while (value > 0)
        {
            if (r.toString().equals(riders.get(0).toString()))
            {
                /* Check if rider doesn't go out of path */
                if (y == 3 && (x < length - 2 && x > ((length - space - 2) / 2) - 1)) return null;
                if ((y == 1 || y == 3) && x == length - 2) y += 2;
                else if ((y == 5) && x == 1) y -= 2;
                else if (y == 1 || y == 3) ++x;
                else if (y == 5) --x;
            }

            if (r.toString().equals(riders.get(1).toString()))
            {
                /* Check if rider doesn't go out of path */
                if (y == 3 && (x > 1 && x < ((length - space - 2) / 2) + space + 2)) return null;
                if ((y == 5 || y == 3) && x == 1) y -= 2;
                else if ((y == 1) && x == length - 2) y += 2;
                else if (y == 5 || y == 3) --x;
                else if (y == 1) ++x;
            }
            nextPosition = new Position(x, y);
            --value;
        }

        return nextPosition;
    }

    /**
     * Make a rider move with obstacle process
     * @param r - rider
     * @return True if rider move, false if rider don't move
     */
    private boolean move(Rider r) {
        Position nextPosition = nextPosition(r);
        if (nextPosition == null) return false;
        if (getCell(r.getPosition()).process(r)) // Call process method of cell, if return true set next position to rider, else not
        {
            /* Check if riders aren't in same cell */
            if (riders.get(0).getPosition().equals(nextPosition)) riders.get(0).setPosition(new Position(1,1));
            if (riders.get(1).getPosition().equals(nextPosition)) riders.get(1).setPosition(new Position(length - 2,5));
            r.setPosition(nextPosition); // Set next rider position
            return true;
        }
        return false;
    }

    /**
     * Tell if a rider achieve finish
     * @return True if rider achieve finish, false if is not
     */
    public boolean isWin() {
        for (Rider r : riders)
            if (getCell(r.getPosition()) instanceof CellEnd) return true;
        return false;
    }

    /**
     * Play round
     * @param r - rider
     */
    public void playRound(Rider r) {
        System.out.println("Le cavalier de couleur " + r.getName() + " joue");
        r.rollDice();
        System.out.println("Valeur du dé : " + r.getDice() + "\n");

        // Call method move, if is return true print message, else not
        if (move(r)) System.out.println("** Le cavalier " + r.getName() + " est sur la " + getCell(r.getPosition()).getName() + " **\n");
        System.out.println(this); // Print board
    }

    /**
     * Play a game until a rider win
     */
    public void playGame() {
        int round = 0;
        while (!isWin())
        {
            ++round;

            /* Select which rider play */
            Rider rider;
            if (round % 2 == 1) rider = riders.get(0); // Red rider
            else rider = riders.get(1); // Blue rider

            playRound(rider);
        }
    }

    /**
     * Print board
     * @return String
     */
    public String toString() {
        String str = "";
        for (ArrayList<Cell> array : board)
        {
            for (Cell c : array)
            {
                boolean isRider = false;
                for(Rider r : riders)
                {
                    if (c.getPosition().equals(r.getPosition()))
                    {
                        str += " " + r + " ";
                        isRider = true;
                    }
                }
                if (! isRider) str += " " + c.toString() + " ";
            }
            str += "\n";
        }
        for (int i = 0; i < length - 1; ++i)
            str += "___";
        str += "\n   ";
        str += riders.get(0) + " " + riders.get(0).getName() + " "
                + getCell(riders.get(0).getPosition()).getName() + "   "
                + riders.get(1) + " " + riders.get(1).getName() + " "
                + getCell(riders.get(1).getPosition()).getName() + "\n";
        for (int i = 0; i < length - 1; ++i)
            str += "___";
        str += "\n";
        return str;
    }
}
