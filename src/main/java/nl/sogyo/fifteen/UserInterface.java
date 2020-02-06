package nl.sogyo.fifteen;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.event.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.*;

public class UserInterface extends JFrame {
    NonStone nonStone = new NonStone();

    public static void main(String[] args){
        UserInterface ui = new UserInterface("Fifteen");
        ui.nonStone.generatePuzzle(50);
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
            SuperStone tempStone = this.nonStone.getFromAbsolutePosition(xCoord, yCoord);
            if (tempStone instanceof Stone) {
                StoneButton tempButton = new StoneButton(this.nonStone, "" + ((Stone) tempStone).getValue());
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
            SuperStone tempStone = this.nonStone.getFromAbsolutePosition(xCoord, yCoord);
            if (tempStone instanceof Stone) {
                StoneButton tempButton = new StoneButton(this.nonStone, "" + ((Stone) tempStone).getValue());
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
        this.nonStone = new NonStone();
        this.nonStone.generatePuzzle(difficulty);
        this.updateFrame();
    }

    void solvePuzzle() {
        ArrayList<String> solution = FifteenSolver.solveFifteenPuzzle(nonStone);
        System.out.println(solution);
        int interval = 500;
        Timer timer = new Timer(interval, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    nonStone.move(solution.get(0));
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