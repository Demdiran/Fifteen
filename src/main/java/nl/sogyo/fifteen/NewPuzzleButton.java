package nl.sogyo.fifteen;

import javax.swing.*;
import java.awt.event.*;

class NewPuzzleButton extends JButton implements ActionListener{
    int difficulty;

    NewPuzzleButton(String name, int difficulty){
        super(name);
        this.difficulty = difficulty;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UserInterface frame = (UserInterface) this.getTopLevelAncestor();
        frame.newPuzzle(difficulty);
    }
    
}