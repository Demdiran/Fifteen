package nl.sogyo.fifteen;

import javax.swing.*;
import java.awt.event.*;

class StoneButton extends JButton implements ActionListener{
    private NonStone nonStone;

    StoneButton(NonStone nonStone, String text){
        super(text);
        this.nonStone = nonStone;
        this.addActionListener(this);
    }

    void moveStone(){
        int xCoord = this.getX() / 100;
        int yCoord = this.getY() / 100;
        String direction = "";
        SuperStone clicked = nonStone.getFromAbsolutePosition(xCoord, yCoord);
        if(nonStone.getNorth() == clicked)
            direction = "north";
        if(nonStone.getEast() == clicked)
            direction = "east";    
        if(nonStone.getSouth() == clicked)
            direction = "south";    
        if(nonStone.getWest() == clicked)
            direction = "west";
        try {
            System.out.println(direction);
            this.nonStone.move(direction);               
        } catch (InvalidMoveException e) {
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveStone();
        UserInterface frame = (UserInterface) this.getTopLevelAncestor();
        if(this.nonStone.isSolved()){
            frame.hasWon();
        }
        else{
            frame.updateFrame();
        }
    }
}