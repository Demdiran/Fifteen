package nl.sogyo.fifteen;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Container;
public class UserInterface extends JFrame{
    Stone stone = new Stone();
    public static void main(String[] args) {
        UserInterface ui = new UserInterface("Fifteen");
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.getContentPane().setPreferredSize(new Dimension(400, 400));
        ui.pack();
        Container contentPane = ui.getFilledContentPane();
        ui.setContentPane(contentPane);
        ui.setLayout(null);
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);   
    }

    public UserInterface(String title){
        super(title);
    }

    private Container getFilledContentPane(){
        Container contentPane = new Container();
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
        return contentPane;
    }

    void updateFrame(){
        Container newContentPane = this.getFilledContentPane();
        this.setContentPane(newContentPane);
        this.getContentPane().setPreferredSize(new Dimension(400, 400));
        this.pack();
    }
    

}