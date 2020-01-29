package nl.sogyo.fifteen;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
public class UserInterface extends JFrame{
    Stone stone = new Stone();
    public static void main(String[] args) {
        UserInterface ui = new UserInterface("Fifteen");
        ui.stone.generateNewPuzzle(10);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.updateFrame();
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);   
    }

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
        
        JPanel panel = new JPanel(new BorderLayout());
        JButton newPuzzle = new JButton("New puzzle");
        JButton solve = new JButton("Solve");
        panel.add(BorderLayout.WEST, newPuzzle);
        panel.add(BorderLayout.EAST, solve);
        
        newContentPane.add(BorderLayout.CENTER, board);
        newContentPane.add(BorderLayout.NORTH, panel);
        this.setContentPane(newContentPane);
        this.pack();
    }

    void updateFrame(){
        createFrame(getBoard());
    }

    void hasWon(){
        createFrame(getBoard(Color.green));
    }
    

}