import java.awt.event.*;

public class Action implements ActionListener {
    Frame frame;
    Board board;
    Rider rider;

    /**
     * Constructor : Instance with frame and board
     * @param frame - JFrame parcheesi board
     * @param board - parcheesi board
     */
    public Action(Frame frame, Board board) {
        this.frame = frame;
        this.board = board;
        this.rider = board.getRiders().get(0); // default red rider start
    }

    /**
     * Action performed when event (click button)
     * @param e - action event
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frame.roll) frame.playRider(rider);

        // switch rider
        if (rider.toString().equals(board.getRiders().get(0).toString())) rider = board.getRiders().get(1);
        else rider = board.getRiders().get(0);
    }
}
