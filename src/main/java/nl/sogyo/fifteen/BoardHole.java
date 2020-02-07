package nl.sogyo.fifteen;

import java.util.*;
class BoardHole extends BoardPart{

    public BoardHole(){
        this.northNeighbour = new BoardStone(12);
        this.westNeighbour = new BoardStone(15, this);
        connectToNeighbours(null, null, this.westNeighbour);
    }

    boolean isSolved(){
        if(this.northNeighbour == null || this.westNeighbour == null)
            return false;
        boolean northSolved = (((BoardStone) this.northNeighbour).getValue() == 12) && this.northNeighbour.isSolved();
        boolean westSolved = (((BoardStone) this.westNeighbour).getValue() == 15 && this.westNeighbour.isSolved());
        return westSolved && northSolved;
    }

    @Override
    int calculateBoardHeuristic() {
        int total = 0;
        if(this.westNeighbour == null && this.southNeighbour != null)
            total += this.southNeighbour.calculateBoardHeuristic();
        if(this.eastNeighbour != null)
            total += this.eastNeighbour.calculateBoardHeuristic();
        return total;
    }

    public int calculateHeuristic(){
        return this.getTopLeftOfBoard().calculateBoardHeuristic();
    }

    public void generateRandomPuzzle(int numberOfMoves){
        String previousMove = "";
        Random random = new Random();
        while(numberOfMoves > 0){
            List<String> possibleMoves = new ArrayList<String>();
            if(this.northNeighbour != null && previousMove != "north")
                possibleMoves.add("north");
            if(this.eastNeighbour != null && previousMove != "east")
                possibleMoves.add("east");
            if(this.southNeighbour != null && previousMove != "south")
                possibleMoves.add("south");
            if(this.westNeighbour != null && previousMove != "west")
                possibleMoves.add("west");

            int move = random.nextInt(possibleMoves.size());
            previousMove = possibleMoves.get(move);
            try{
                this.move(previousMove);
            }
            catch(InvalidMoveException e){
                e.printStackTrace();
            }
            numberOfMoves--;
        }
    }

    public void move(String direction)throws InvalidMoveException{
        BoardPart exNorth = this.northNeighbour;
        BoardPart exWest = this.westNeighbour;
        BoardPart exSouth = this.southNeighbour;
        BoardPart exEast = this.eastNeighbour;
        if(direction == "north"){
            if(this.northNeighbour == null)throw new InvalidMoveException("Moved too far north");

            this.westNeighbour = exNorth.westNeighbour;
            if(this.westNeighbour != null)
                this.westNeighbour.eastNeighbour = this;
            this.northNeighbour = exNorth.northNeighbour;
            if(this.northNeighbour != null)
                this.northNeighbour.southNeighbour = this;
            this.eastNeighbour = exNorth.eastNeighbour;
            if(this.eastNeighbour != null)
                this.eastNeighbour.westNeighbour = this;

            this.southNeighbour = exNorth;
            this.southNeighbour.northNeighbour = this;
            this.southNeighbour.westNeighbour = exWest;
            this.southNeighbour.southNeighbour = exSouth;
            this.southNeighbour.eastNeighbour = exEast;

            if(exWest != null)
                exWest.eastNeighbour = this.southNeighbour;
            if(exSouth != null)
                exSouth.northNeighbour = this.southNeighbour;
            if(exEast != null)
                exEast.westNeighbour = this.southNeighbour;
        }
        if(direction == "west"){
            if(this.westNeighbour == null)throw new InvalidMoveException("Moved too far west");

            this.westNeighbour = exWest.westNeighbour;
            if(this.westNeighbour != null)
                this.westNeighbour.eastNeighbour = this;
            this.northNeighbour = exWest.northNeighbour;
            if(this.northNeighbour != null)
                this.northNeighbour.southNeighbour = this;
            this.southNeighbour = exWest.southNeighbour;
            if(this.southNeighbour != null)
                this.southNeighbour.northNeighbour = this;
            this.eastNeighbour = exWest;
            this.eastNeighbour.westNeighbour = this;
            this.eastNeighbour.northNeighbour = exNorth;
            this.eastNeighbour.southNeighbour = exSouth;
            this.eastNeighbour.eastNeighbour = exEast;

            if(exNorth != null)
                exNorth.southNeighbour = this.eastNeighbour;
            if(exSouth != null)
                exSouth.northNeighbour = this.eastNeighbour;
            if(exEast != null)
                exEast.westNeighbour = this.eastNeighbour;
        }
        if(direction == "south"){
            if(this.southNeighbour == null)throw new InvalidMoveException("Moved too far south");

            this.westNeighbour = exSouth.westNeighbour;
            if(this.westNeighbour != null)
                this.westNeighbour.eastNeighbour = this;
            this.eastNeighbour = exSouth.eastNeighbour;
            if(this.eastNeighbour != null)
                this.eastNeighbour.westNeighbour = this;
            this.southNeighbour = exSouth.southNeighbour;
            if(this.southNeighbour != null)
                this.southNeighbour.northNeighbour = this;
            this.northNeighbour = exSouth;
            this.northNeighbour.southNeighbour = this;
            this.northNeighbour.westNeighbour = exWest;
            this.northNeighbour.eastNeighbour = exEast;
            this.northNeighbour.northNeighbour = exNorth;

            if(exWest != null)
                exWest.eastNeighbour = this.northNeighbour;
            if(exNorth != null)
                exNorth.southNeighbour = this.northNeighbour;
            if(exEast != null)
                exEast.westNeighbour = this.northNeighbour;
        }
        if(direction == "east"){
            if(this.eastNeighbour == null)throw new InvalidMoveException("Moved too far east");
            
            this.northNeighbour = exEast.northNeighbour;
            if(this.northNeighbour != null)
                this.northNeighbour.southNeighbour = this;
            this.eastNeighbour = exEast.eastNeighbour;
            if(this.eastNeighbour != null)
                this.eastNeighbour.westNeighbour = this;
            this.southNeighbour = exEast.southNeighbour;
            if(this.southNeighbour != null)
                this.southNeighbour.northNeighbour = this;
            this.westNeighbour = exEast;
            this.westNeighbour.eastNeighbour = this;
            this.westNeighbour.southNeighbour = exSouth;
            this.westNeighbour.westNeighbour = exWest;
            this.westNeighbour.northNeighbour = exNorth;

            if(exSouth != null)
                exSouth.northNeighbour = this.westNeighbour;
            if(exWest != null)
                exWest.eastNeighbour = this.westNeighbour;
            if(exNorth != null)
                exNorth.southNeighbour = this.westNeighbour;
        }
    }

    boolean testSolution(ArrayList<String> solution)throws InvalidMoveException{
        for (String move : solution) {
            this.move(move);
        }
        return this.isSolved();
    }
}