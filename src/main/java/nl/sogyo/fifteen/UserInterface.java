package nl.sogyo.fifteen;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.*;
public class UserInterface extends JFrame{
    Stone stone = new Stone();
    public static void main(String[] args) {
        UserInterface ui = new UserInterface("Fifteen");
        ui.stone.generateNewPuzzle(50);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.updateFrame();
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);   
    }

    /*public static void main(String[] args){
        Stone stone = new Stone();
        stone.doMove(3, 2);
        int[] solution = FifteenSolver.solveFifteenPuzzle(stone);
        System.out.println(Arrays.toString(solution));
    }*/

    public UserInterface(String title){
        super(title);
    }

    private JPanel getBoard(){
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        for(int i = 0; i < 16; i++){
            int xCoord = i % 4;
            int yCoord = i / 4;
            Stone tempStone = this.stone.getFromCoordinate(xCoord, yCoord);
            if(tempStone.getValue() != 16){
                StoneButton tempButton = new StoneButton(this.stone, "" + tempStone.getValue());
                tempButton.setBounds(xCoord * 100, yCoord * 100, 100, 100);
                tempButton.addActionListener(tempButton);
                contentPane.add(tempButton);
            }
        }
        contentPane.setPreferredSize(new Dimension(400, 400));
        return contentPane;
    }

    private JPanel getBoard(Color backgroundColor){
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        for(int i = 0; i < 16; i++){
            int xCoord = i % 4;
            int yCoord = i / 4;
            Stone tempStone = this.stone.getFromCoordinate(xCoord, yCoord);
            if(tempStone.getValue() != 16){
                StoneButton tempButton = new StoneButton(this.stone, "" + tempStone.getValue());
                tempButton.setBounds(xCoord * 100, yCoord * 100, 100, 100);
                tempButton.addActionListener(tempButton);
                tempButton.setBackground(backgroundColor);
                contentPane.add(tempButton);
            }
        }
        contentPane.setPreferredSize(new Dimension(400, 400));
        return contentPane;
    }

    void createFrame(JPanel board){
        JPanel newContentPane = new JPanel(new BorderLayout());
        newContentPane.add(BorderLayout.CENTER, board);
        
        JPanel menu = new JPanel();

        NewPuzzleButton newPuzzleEasy = new NewPuzzleButton("Easy", 25);
        newPuzzleEasy.addActionListener(newPuzzleEasy);

        NewPuzzleButton newPuzzleMedium = new NewPuzzleButton("Medium", 50);
        newPuzzleMedium.addActionListener(newPuzzleMedium);

        NewPuzzleButton newPuzzleHard = new NewPuzzleButton("Hard", 75);
        newPuzzleHard.addActionListener(newPuzzleHard);

        JButton solve = new JButton("Solve");
        menu.add(newPuzzleEasy);
        menu.add(newPuzzleMedium);
        menu.add(newPuzzleHard);
        menu.add(solve);
        
        newContentPane.add(BorderLayout.CENTER, board);
        newContentPane.add(BorderLayout.NORTH, menu);
        this.setContentPane(newContentPane);
        this.pack();
    }

    void updateFrame(){
        createFrame(getBoard());
    }

    void hasWon(){
        createFrame(getBoard(Color.green));
    }
    
    void newPuzzle(int difficulty){
        this.stone = new Stone();
        stone.generateNewPuzzle(difficulty);
        updateFrame();
    }

}