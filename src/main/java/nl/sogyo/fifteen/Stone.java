package nl.sogyo.fifteen;

import java.util.ArrayList;
import java.util.Random;
import org.apache.commons.lang3.ArrayUtils;
public class Stone{
    private final int value;
    private Stone north;
    private Stone east;
    private Stone south;
    private Stone west;
    public Stone(){
        this.value = 1;
        this.east = new Stone(2);
        this.south = new Stone(5, this.east.south);
        this.south.setNorth(this);
        this.east.setWest(this);
    }
    public Stone(int v){
        this.value = v;
        if(v % 4 != 0){
            this.east = new Stone(v + 1);
        }
        if((v-1) / 4 < 3){
            if(this.east == null){
                this.south = new Stone(v + 4);
            }
            else{
                this.south = new Stone(v + 4, this.east.south);
            }
        }
    }
    public Stone(int v, Stone eastNeighbour){
        this.value = v;
        this.east = eastNeighbour;
        if(v / 4 < 3){
            this.south = new Stone(v + 4, this.east.south);
        }
    }

    public int getValue(){
        return this.value;
    }

    public Stone getEast(){
        return this.east;
    }

    public Stone getSouth(){
        return this.south;
    }

    public Stone getNorth(){
        return this.north;
    }

    public Stone getWest(){
        return this.west;
    }

    public int calculateHeuristic(){
        return this.getFromCoordinate(0, 0).heuristicFromScratch();
    }

    private int heuristicFromScratch(){
        int ownEstimate = 0;
        if(this.value != 16){
            int xDestination = (this.value - 1) % 4;
            int yDestination = (this.value - 1) / 4;
            ownEstimate = Math.abs(this.getXCoordinate() - xDestination) + Math.abs(this.getYCoordinate() - yDestination);
        }
        if(this.east != null){
            return ownEstimate + this.east.heuristicFromScratch();
        }
        else if(this.south != null){
            return ownEstimate + this.getFromCoordinate(0, this.getYCoordinate() + 1).heuristicFromScratch();
        }
        else {
            return ownEstimate;
        }
    }

    public Stone getEmptyStone(){
        return this.getFromCoordinate(0, 0).emptyStoneSearch();
    }

    private Stone emptyStoneSearch(){
        if(this.value == 16){
            return this;
        }
        else if(this.east != null){
            return this.east.emptyStoneSearch();
        }
        else {
            return this.getFromCoordinate(0, this.getYCoordinate() + 1).emptyStoneSearch();
        }
    }

    public String printBoard(){
        String line = "|-----------|\n\r";
        String row1 = this.getFromCoordinate(0, 0).rowToString();
        String row2 = this.getFromCoordinate(0, 1).rowToString();
        String row3 = this.getFromCoordinate(0, 2).rowToString();
        String row4 = this.getFromCoordinate(0, 3).rowToString();
        String boardString = line + row1 + line + row2 + line + row3 + line + row4 + "|-----------|";
        return boardString;
    }

    private String rowToString(){
        String part1 = "|" + this.getStringForBoard();
        String part2 = this.getStepsEast(1).getStringForBoard();
        String part3 = this.getStepsEast(2).getStringForBoard();
        String part4 = this.getStepsEast(3).getStringForBoard();
        String result = part1 + part2 + part3 + part4 + "\n\r";
        return result;
    }

    private String getStringForBoard(){
        if(this.value < 10){
            return " " + this.value + "|";
        }
        else if(this.value == 16){
            return "  " + "|";
        }
        else{
            return "" + this.value + "|";
        }
    }

    public Stone getFromCoordinate(int x, int y){
        int xCoordinate = this.getXCoordinate();
        int yCoordinate = this.getYCoordinate();
        int relativeXCoord = x - xCoordinate;
        int relativeYCoord = y - yCoordinate;
        Stone result;
        if(relativeXCoord < 0){
            result = this.getStepsWest(-relativeXCoord);
        }
        else{
            result = this.getStepsEast(relativeXCoord);
        }
        if(relativeYCoord < 0){
            return result.getStepsNorth(-relativeYCoord);
        }
        else{
            return result.getStepsSouth(relativeYCoord);
        }
    }

    public int getXCoordinate(){
        if(this.west == null){
            return 0;
        }
        else{
            return this.west.getXCoordinate() + 1;
        }
    }

    public int getYCoordinate(){
        if(this.north == null){
            return 0;
        }
        else{
            return this.north.getYCoordinate() + 1;
        }
    }

    private void setWest(Stone west){
        this.west = west;
        if(this.east != null){
            this.east.setWest(this);
        }
        if(this.south != null){
            this.south.setNorth(this);
        }
    }

    private void setNorth(Stone north){
        this.north = north;
        if(this.south != null){
            this.south.setNorth(this);
        }
        if(this.east != null){
            this.east.setWest(this);
        }
    }

    public boolean isSolved(){
        for(int x = 0; x < 4; x++){
            for (int y = 0; y < 4; y++){
                if(!this.getFromCoordinate(x, y).isRightPlace()){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isRightPlace(){
        int xCoordinate = this.getXCoordinate();
        int yCoordinate = this.getYCoordinate();
        return this.value == (yCoordinate*4 + xCoordinate + 1);
    }

    public void doMove(int x, int y){
        getFromCoordinate(x, y).move();
    }

    public Stone getStepsNorth(int steps){
        if(steps == 0){
            return this;
        }
        else if(this.north != null){
            return this.north.getStepsNorth(steps - 1);
        }
        else{
            throw new OutOfBoardException("Tried to access a stone too much to the north.");
        }
    }

    public Stone getStepsEast(int steps){
        if(steps == 0){
            return this;
        }
        else if(this.east != null){
            return this.east.getStepsEast(steps - 1);
        }
        else{
            throw new OutOfBoardException("Tried to access stone too much to the east.");
        }
    }

    public Stone getStepsSouth(int steps){
        if(steps == 0){
            return this;
        }
        else if(this.south != null){
            return this.south.getStepsSouth(steps - 1);
        }
        else{
            throw new OutOfBoardException("Tried to access stone too much to the south.");
        }
    }

    public Stone getStepsWest(int steps){
        if(steps == 0){
            return this;
        }
        else if(this.west != null){
            return this.west.getStepsWest(steps - 1);
        }
        else{
            throw new OutOfBoardException("Tried to access stone too much to the west.");
        }
    }

    public boolean canMove(){
        return ((this.north != null && this.north.value == 16) || (this.east != null && this.east.value == 16) || (this.south != null && this.south.value == 16) || (this.west != null && this.west.value == 16));
    }

    private void move(){
        if(!this.canMove()){
            throw new RuntimeException("Invalid move attempted!");
        }
        Stone tNorth = this.north;
        Stone tEast = this.east;
        Stone tSouth = this.south;
        Stone tWest = this.west;
        Stone emptyStone;
        if(this.north != null && this.north.getValue() == 16){
            emptyStone = this.north;
            giveNeighboursNewNeighbour(emptyStone);
            this.north = emptyStone.getNorth();
            this.east = emptyStone.getEast();
            this.south = emptyStone;
            this.west = emptyStone.getWest();
            giveNeighboursNewNeighbour(this);
            emptyStone.move(this, tEast, tSouth, tWest);
        }else if(this.east != null && this.east.getValue() == 16){
            emptyStone = this.east;
            giveNeighboursNewNeighbour(emptyStone);
            this.north = emptyStone.getNorth();
            this.east = emptyStone.getEast();
            this.south = emptyStone.getSouth();
            this.west = emptyStone;
            giveNeighboursNewNeighbour(this);
            emptyStone.move(tNorth, this, tSouth, tWest);
        }else if(this.south != null && this.south.getValue() == 16){
            emptyStone = this.south;
            giveNeighboursNewNeighbour(emptyStone);
            this.north = emptyStone;
            this.east = emptyStone.getEast();
            this.south = emptyStone.getSouth();
            this.west = emptyStone.getWest();
            giveNeighboursNewNeighbour(this);
            emptyStone.move(tNorth, tEast, this, tWest);
        }else if(this.west != null && this.west.getValue() == 16){
            emptyStone = this.west;
            giveNeighboursNewNeighbour(emptyStone);
            this.north = emptyStone.getNorth();
            this.east = emptyStone;
            this.south = emptyStone.getSouth();
            this.west = emptyStone.getWest();
            giveNeighboursNewNeighbour(this);
            emptyStone.move(tNorth, tEast, tSouth, this);
        }

    }

    private void move(Stone north, Stone east, Stone south, Stone west){
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }

    private void giveNeighboursNewNeighbour(Stone newNeighbour){
        if(this.north != null){
            this.north.south = newNeighbour;
        }
        if(this.east != null){
            this.east.west = newNeighbour;
        }
        if(this.south != null){
            this.south.north = newNeighbour;
        }
        if(this.west != null){
            this.west.east = newNeighbour;
        }
    }

    public int[] generateNewPuzzle(int difficulty){
        int x = 3;
        int y = 3;
        Random random = new Random();
        ArrayList<Integer> moves = new ArrayList<Integer>();
        for(int i = 0; i < difficulty; i++){
            moves.add((4*y + x + 1));
            ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
            if(x != 0){
                possibleMoves.add(1);
            }
            if(x != 3){
                possibleMoves.add(0);
            }
            if(y != 0){
                possibleMoves.add(3);
            }
            if(y != 3){
                possibleMoves.add(2);
            }
            int move = possibleMoves.get(random.nextInt(possibleMoves.size()));
            switch(move){
                case 0:
                    x++;
                    break;
                case 1:
                    x--;
                    break;
                case 2:
                    y++;
                    break;
                case 3:
                    y--;
                    break;
            }
            doMove(x, y);
        }
        return ArrayUtils.toPrimitive(moves.toArray(new Integer[moves.size()]));
    }

    public boolean trySolution(int[] solution){
        for(int i = solution.length; i > 0; i--){
            int xCoord = (solution[i-1] - 1) % 4;
            int yCoord = (solution[i-1] - 1) / 4;
            this.doMove(xCoord, yCoord);
        }
        return isSolved();
    }

}