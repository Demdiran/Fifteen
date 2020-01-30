package nl.sogyo.fifteen;

import javax.swing.*;
import java.awt.event.*;

class StoneButton extends JButton implements ActionListener{
    private Stone stone;

    StoneButton(Stone stone, String text){
        super(text);
        this.stone = stone;
        this.addActionListener(this);
    }

    void moveStone(){
        int xCoord = this.getX() / 100;
        int yCoord = this.getY() / 100;
        if(this.stone.getFromCoordinate(xCoord, yCoord).canMove()){
            this.stone.doMove(xCoord, yCoord);
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveStone();
        UserInterface frame = (UserInterface) this.getTopLevelAncestor();
        if(this.stone.isSolved()){
            frame.hasWon();
        }
        else{
            frame.updateFrame();
        }
    }
}