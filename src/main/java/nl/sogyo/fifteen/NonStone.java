package nl.sogyo.fifteen;

import java.util.*;
class NonStone extends SuperStone{

    public NonStone(){
        this.north = new Stone(12);
        this.west = new Stone(15, this);
        connectToNeighbours(null, null, this.west);
    }

    boolean isSolved(){
        if(this.north == null || this.west == null)
            return false;
        boolean northSolved = (((Stone) this.north).getValue() == 12) && this.north.isSolved();
        boolean westSolved = (((Stone) this.west).getValue() == 15 && this.west.isSolved());
        return westSolved && northSolved;
    }

    public SuperStone getFromAbsolutePosition(int x, int y){
        return getTopLeft().getFromRelativeCoordinate(x, y);
    }

    @Override
    int heuristicCalculation() {
        int total = 0;
        if(this.west == null && this.south != null)
            total += this.south.heuristicCalculation();
        if(this.east != null)
            total += this.east.heuristicCalculation();
        return total;
    }

    public int calculateHeuristic(){
        return this.getTopLeft().heuristicCalculation();
    }

    public void generatePuzzle(int numberOfMoves){
        String previousMove = "";
        Random random = new Random();
        while(numberOfMoves > 0){
            List<String> possibleMoves = new ArrayList<String>();
            if(this.north != null && previousMove != "north")
                possibleMoves.add("north");
            if(this.east != null && previousMove != "east")
                possibleMoves.add("east");
            if(this.south != null && previousMove != "south")
                possibleMoves.add("south");
            if(this.west != null && previousMove != "west")
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
        SuperStone exNorth = this.north;
        SuperStone exWest = this.west;
        SuperStone exSouth = this.south;
        SuperStone exEast = this.east;
        if(direction == "north"){
            if(this.north == null)throw new InvalidMoveException("Moved too far north");
            this.west = exNorth.west;
            if(this.west != null)
                this.west.east = this;
            this.north = exNorth.north;
            if(this.north != null)
                this.north.south = this;
            this.east = exNorth.east;
            if(this.east != null)
                this.east.west = this;

            this.south = exNorth;
            this.south.north = this;
            this.south.west = exWest;
            this.south.south = exSouth;
            this.south.east = exEast;

            if(exWest != null)
                exWest.east = this.south;
            if(exSouth != null)
                exSouth.north = this.south;
            if(exEast != null)
                exEast.west = this.south;
        }
        if(direction == "west"){
            if(this.west == null)throw new InvalidMoveException("Moved too far west");
            this.west = exWest.west;
            if(this.west != null)
                this.west.east = this;
            this.north = exWest.north;
            if(this.north != null)
                this.north.south = this;
            this.south = exWest.south;
            if(this.south != null)
                this.south.north = this;
            this.east = exWest;
            this.east.west = this;
            this.east.north = exNorth;
            this.east.south = exSouth;
            this.east.east = exEast;

            if(exNorth != null)
                exNorth.south = this.east;
            if(exSouth != null)
                exSouth.north = this.east;
            if(exEast != null)
                exEast.west = this.east;
        }
        if(direction == "south"){
            if(this.south == null)throw new InvalidMoveException("Moved too far south");
            this.west = exSouth.west;
            if(this.west != null)
                this.west.east = this;
            this.east = exSouth.east;
            if(this.east != null)
                this.east.west = this;
            this.south = exSouth.south;
            if(this.south != null)
                this.south.north = this;
            this.north = exSouth;
            this.north.south = this;
            this.north.west = exWest;
            this.north.east = exEast;
            this.north.north = exNorth;

            if(exWest != null)
                exWest.east = this.north;
            if(exNorth != null)
                exNorth.south = this.north;
            if(exEast != null)
                exEast.west = this.north;
        }
        if(direction == "east"){
            if(this.east == null)throw new InvalidMoveException("Moved too far east");
            this.north = exEast.north;
            if(this.north != null)
                this.north.south = this;
            this.east = exEast.east;
            if(this.east != null)
                this.east.west = this;
            this.south = exEast.south;
            if(this.south != null)
                this.south.north = this;
            this.west = exEast;
            this.west.east = this;
            this.west.south = exSouth;
            this.west.west = exWest;
            this.west.north = exNorth;

            if(exSouth != null)
                exSouth.north = this.west;
            if(exWest != null)
                exWest.east = this.west;
            if(exNorth != null)
                exNorth.south = this.west;
        }
    }
}