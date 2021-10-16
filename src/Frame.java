import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Frame extends JFrame {
    private final JPanel gui = new JPanel(new BorderLayout());
    private final JPanel[][] parcheesiBoardSquare;
    private final Board board;
    private final JLabel message;
    private final JLabel status1;
    private final JLabel status2;
    public JButton roll = new JButton("Rouler le dé");


    /**
     * Constructor : Instance with board
     * @param board - parcheesi board
     */
    public Frame(Board board) {
        gui.setBorder(new EmptyBorder(0,0,0,0));
        this.board = board;
        int length = board.getLength();
        Font font = new Font("Helvetica Neue Light", Font.PLAIN, 24);
        parcheesiBoardSquare = new JPanel[7][length];

        /* Commands */
        JPanel commands = new JPanel(new BorderLayout());
        gui.add(commands, BorderLayout.PAGE_START);

        message = new JLabel(" ", SwingConstants.CENTER);
        commands.add(message, BorderLayout.PAGE_START);
        message.setPreferredSize(new Dimension(640,60));
        message.setFont(font);

        JPanel flowPanel = new JPanel(new FlowLayout());
        commands.add(flowPanel, BorderLayout.PAGE_END);
        flowPanel.add(roll);
        roll.setFont(font);

        /* Status */
        JPanel panel = new JPanel(new BorderLayout());
        gui.add(panel, BorderLayout.PAGE_END);

        status1 = new JLabel("", SwingConstants.CENTER);
        panel.add(status1, BorderLayout.PAGE_START);
        status1.setFont(font);

        status2 = new JLabel("", SwingConstants.CENTER);
        panel.add(status2, BorderLayout.PAGE_END);
        status2.setFont(font);

        /* Board */
        JPanel parcheesiBoard = new JPanel(new GridLayout(0, length));
        gui.add(parcheesiBoard);

        for (int i = 0; i < parcheesiBoardSquare.length; ++i)
        {
            for (int j = 0; j < parcheesiBoardSquare[i].length; ++j)
            {
                JPanel cell = new JPanel(new GridBagLayout());
                if (board.getCell(i,j) instanceof CellSide) cell.setBackground(Color.GREEN);
                else
                {
                    cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    if (board.getCell(i,j) instanceof CellEnd) cell.setBackground(Color.CYAN);
                    else if (board.getCell(i,j) instanceof CellFree) cell.setBackground(Color.LIGHT_GRAY);
                    else if (board.getCell(i,j) instanceof CellHedge) cell.setBackground(Color.DARK_GRAY);
                    else if (board.getCell(i,j) instanceof CellHole) cell.setBackground(Color.YELLOW);
                    else if (board.getCell(i,j) instanceof CellRiver) cell.setBackground(Color.BLUE);
                    else if (board.getCell(i,j) instanceof CellStart) cell.setBackground(Color.PINK);
                    else if (board.getCell(i,j) instanceof CellWhite) cell.setBackground(Color.WHITE);
                }

                JLabel label = new JLabel("", SwingConstants.CENTER);
                label.setFont(font);
                cell.add(label);

                parcheesiBoardSquare[i][j] = cell;
                parcheesiBoard.add(parcheesiBoardSquare[i][j]);
            }
        }

        /* Dice */
        for (Component jc : getCell(3,((length - 6) / 2) + 2 ).getComponents())
            if (jc instanceof JLabel) ((JLabel) jc).setText("Dé");

        /* Riders */
        for (Rider r : board.getRiders()) {
            addRider(r);
        }

        /* Action */
        Action action = new Action(this, board);
        roll.addActionListener(action);

    }

    /**
     * Make a rider move and update the board (rider position, game information)
     * @param r - rider
     */
    public void playRider(Rider r) {
        Rider other;
        boolean played = true;
        if (r.toString().equals(board.getRiders().get(0).toString())) other = board.getRiders().get(1);
        else
        {
            other = board.getRiders().get(0);
            played = false;
        }

        addMessage(r);  // message who played
        dellRider(r);   // remove rider
        board.playRound(r); // play Round
        addDice(r);     // print dice value
        addRider(r);    // print rider
        addRider(other);    // print other rider case if rider 'eat' the other rider to see him in start cell
        addStatus1(board.getRiders().get(0), played);  // print massage on first line
        addStatus2(board.getRiders().get(1), !played);  // print massage on second line

        if (board.isWin())
        {
            roll.setEnabled(false);
            message.setText("** Le cavalier " + r.getName() + " a gagné **");
            status2.setText("");
            status1.setPreferredSize(new Dimension(640,60));
            addStatus1(other, true);
        }
    }

    /**
     * Get cell (JPanel)
     * @param y - y coordinate
     * @param x - y coordinate
     * @return JComponents
     */
    private JComponent getCell(int y, int x) {
        return parcheesiBoardSquare[y][x];
    }

    /**
     * Add at cell the string of rider at the rider position
     * @param r - rider
     */
    private void addRider(Rider r) {
        for (Component jc : getCell(r.getPosition().getY(),r.getPosition().getX()).getComponents())
            if (jc instanceof JLabel) ((JLabel) jc).setText(r.toString());
    }

    /**
     * Delete the string of rider at rider cell position
     * @param r - rider
     */
    private void dellRider(Rider r) {
        for (Component jc : getCell(r.getPosition().getY(),r.getPosition().getX()).getComponents())
            if (jc instanceof JLabel) ((JLabel) jc).setText(" ");
    }

    /**
     * Add at cell the value of dice roll
     * @param r - rider
     */
    public void addDice(Rider r) {
        for (Component jc : getCell(3,((board.getLength() - 6) / 2) + 3).getComponents())
            if (jc instanceof JLabel) ((JLabel) jc).setText( String.valueOf(r.getDice()));
    }

    /**
     * Add text into message (Jlabel)
     * @param r - rider
     */
    private void addMessage(Rider r) {
        message.setText("Le cavalier de couleur " + r.getName() + " joue");
    }

    /**
     * Text for status
     * @param r - rider
     * @param played - if rider is currently played
     * @return String
     */
    private String text(Rider r, boolean played) {
        String str;
        if (board.getCell(r.getPosition()) instanceof CellSide)
            str = "Le cavalier " + r.getName() + " reste " + board.getCell(r.getPosition()).getName() + " !";
        else if (board.getCell(r.getPosition()) instanceof CellHole)
            str = "Le cavalier " + r.getName() + " est sur la " + board.getCell(r.getPosition()).getName() + " pendant " + ((CellHole) board.getCell(r.getPosition())).getTurn() + " tour";
        else
            str = "Le caliver " + r.getName() + " est sur la " + board.getCell(r.getPosition()).getName();

        if (played)
            str = "** " + str + " **";

        return str;
    }

    /**
     * Add text into Jlabel status 1
     * @param r - rider
     * @param played - if rider is currently played
     */
    private void addStatus1(Rider r, boolean played) {
        status1.setText(text(r, played));
    }

    /**
     * Add text into Jlabel status 2
     * @param r - rider
     * @param played - if rider is currently played
     */
    private void addStatus2(Rider r, boolean played) {
        status2.setText(text(r, played));
    }

    /**
     * Get GUI
     * @return JComponent
     */
    public JComponent getGUI()
    {
        return gui;
    }
}
