package nl.sogyo.fifteen;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.event.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.*;

public class UserInterface extends JFrame {
    Stone board = new Stone();

    public static void main(String[] args) {
        UserInterface ui = new UserInterface("Fifteen");
        ui.board.generateNewPuzzle(50);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.updateFrame();
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);
    }

    public UserInterface(String title) {
        super(title);
    }

    private JPanel getBoard() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        for (int i = 0; i < 16; i++) {
            int xCoord = i % 4;
            int yCoord = i / 4;
            Stone tempStone = this.board.getFromCoordinate(xCoord, yCoord);
            if (tempStone.getValue() != 16) {
                StoneButton tempButton = new StoneButton(this.board, "" + tempStone.getValue());
                tempButton.setBounds(xCoord * 100, yCoord * 100, 100, 100);
                contentPane.add(tempButton);
            }
        }
        contentPane.setPreferredSize(new Dimension(400, 400));
        return contentPane;
    }

    private JPanel getBoard(Color backgroundColor) {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        for (int i = 0; i < 16; i++) {
            int xCoord = i % 4;
            int yCoord = i / 4;
            Stone tempStone = this.board.getFromCoordinate(xCoord, yCoord);
            if (tempStone.getValue() != 16) {
                StoneButton tempButton = new StoneButton(this.board, "" + tempStone.getValue());
                tempButton.setBounds(xCoord * 100, yCoord * 100, 100, 100);
                tempButton.setBackground(backgroundColor);
                contentPane.add(tempButton);
            }
        }
        contentPane.setPreferredSize(new Dimension(400, 400));
        return contentPane;
    }

    void createFrame(JPanel board) {
        JPanel newContentPane = new JPanel(new BorderLayout());
        newContentPane.add(BorderLayout.CENTER, board);

        JPanel menu = new JPanel();

        NewPuzzleButton newPuzzleEasy = new NewPuzzleButton("Easy", 25);
        NewPuzzleButton newPuzzleMedium = new NewPuzzleButton("Medium", 50);
        NewPuzzleButton newPuzzleHard = new NewPuzzleButton("Hard", 75);

        SolveButton solve = new SolveButton("Solve");
        menu.add(newPuzzleEasy);
        menu.add(newPuzzleMedium);
        menu.add(newPuzzleHard);
        menu.add(solve);

        newContentPane.add(BorderLayout.CENTER, board);
        newContentPane.add(BorderLayout.NORTH, menu);
        this.setContentPane(newContentPane);
        this.pack();
    }

    void updateFrame() {
        this.createFrame(getBoard());
    }

    void hasWon() {
        this.createFrame(getBoard(Color.green));
    }

    void newPuzzle(int difficulty) {
        this.board = new Stone();
        board.generateNewPuzzle(difficulty);
        this.updateFrame();
    }

    void solvePuzzle() {
        ArrayList<Integer> solution = FifteenSolver.solveFifteenPuzzle(board);
        int interval = 500;
        Timer timer = new Timer(interval, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    board.doMove(solution.get(0));
                }
                catch(InvalidMoveException exception){
                    ((Timer) e.getSource()).stop();
                }
                solution.remove(0);
                updateFrame();
                if(solution.isEmpty()){
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

}