import javax.swing.*;

public class Parcheesi {
    public static void main(String[] args) {
        Board board = new Board(16);
        //board.playGame();

        Frame gui = new Frame(board);
        JFrame frame = new JFrame("Petits chevaux");
        frame.add(gui.getGUI());
        frame.setSize(1280, 580);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
