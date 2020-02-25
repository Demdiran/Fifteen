package nl.sogyo.fifteen;

import javax.swing.*;
import java.awt.event.*;

class StoneButton extends JButton implements ActionListener{
    private BoardHole boardAccess;

    StoneButton(BoardHole boardAccess, String text){
        super(text);
        this.boardAccess = boardAccess;
        this.addActionListener(this);
    }

    void moveStone(){
        int xCoord = this.getX() / 100;
        int yCoord = this.getY() / 100;
        String direction = "";
        BoardPart clicked = boardAccess.getFromAbsolutePosition(xCoord, yCoord);
        if(boardAccess.getNorthNeighbour() == clicked)
            direction = "north";
        if(boardAccess.getEastNeighbour() == clicked)
            direction = "east";    
        if(boardAccess.getSouthNeighbour() == clicked)
            direction = "south";    
        if(boardAccess.getWestNeighbour() == clicked)
            direction = "west";
        try {
            this.boardAccess.move(direction);               
        } catch (InvalidMoveException e) {
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveStone();
        UserInterface frame = (UserInterface) this.getTopLevelAncestor();
        if(this.boardAccess.isSolved()){
            frame.hasWon();
        }
        else{
            frame.updateFrame();
        }
    }
}