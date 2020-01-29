package nl.sogyo.fifteen;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Container;

class StoneButton extends JButton implements ActionListener{
    private Stone stone;

    StoneButton(Stone stone, String text){
        super(text);
        this.stone = stone;
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