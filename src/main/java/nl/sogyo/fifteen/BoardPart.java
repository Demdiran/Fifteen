package nl.sogyo.fifteen;

abstract class BoardPart{
    BoardPart northNeighbour;
    BoardPart eastNeighbour;
    BoardPart southNeighbour;
    BoardPart westNeighbour;

    BoardPart(){};

    BoardPart getStepsNorth(int steps){
        if(steps == 0){
            return this;
        }
        else{
            return this.northNeighbour.getStepsNorth(steps - 1);
        }
    }

    BoardPart getStepsEast(int steps){
        if(steps == 0){
            return this;
        }
        else{
            return this.eastNeighbour.getStepsEast(steps - 1);
        }
    }

    BoardPart getStepsSouth(int steps){
        if(steps == 0){
            return this;
        }
        else{
            return this.southNeighbour.getStepsSouth(steps - 1);
        }
    }

    BoardPart getStepsWest(int steps){
        if(steps == 0){
            return this;
        }
        else{
            return this.westNeighbour.getStepsWest(steps - 1);
        }
    }

    BoardPart getNorthNeighbour(){
        return this.northNeighbour;
    }

    BoardPart getEastNeighbour(){
        return this.eastNeighbour;
    }

    BoardPart getSouthNeighbour(){
        return this.southNeighbour;
    }

    BoardPart getWestNeighbour(){
        return this.westNeighbour;
    }

    int getXCoordinate(){
        if(this.westNeighbour == null){
            return 0;
        }
        else{
            return this.westNeighbour.getXCoordinate() + 1;
        }
    }

    int getYCoordinate(){
        if(this.northNeighbour == null){
            return 0;
        }
        else{
            return this.northNeighbour.getYCoordinate() + 1;
        }
    }

    abstract int calculateBoardHeuristic();

    BoardPart getRelativeToInstance(int x, int y){
        BoardPart xSteps;
		if(x >= 0)
            xSteps = this.getStepsEast(x);
        else
            xSteps = this.getStepsWest(-x);
        
        if(y >= 0)
            return xSteps.getStepsSouth(y);
        else
            return xSteps.getStepsNorth(-y);
    }

    public BoardPart getFromAbsolutePosition(int x, int y){
        return getTopLeftOfBoard().getRelativeToInstance(x, y);
    }

    BoardPart getTopLeftOfBoard(){
        if(this.northNeighbour != null)
            return this.northNeighbour.getTopLeftOfBoard();
        if(this.westNeighbour != null)
            return this.westNeighbour.getTopLeftOfBoard();
        return this;
    }
    
    abstract boolean isSolved();

    void connectToNeighbours(BoardPart eastNeighbour, BoardPart southNeighbour, BoardPart westNeighbour){
        this.eastNeighbour = eastNeighbour;
        this.southNeighbour = southNeighbour;
        this.westNeighbour = westNeighbour;
        if(this.northNeighbour != null){
            BoardPart nextWest = westNeighbour != null ? westNeighbour.northNeighbour : null;
            BoardPart nextEast = eastNeighbour != null ? eastNeighbour.northNeighbour : null;
            this.northNeighbour.connectToNeighbours(nextEast, this, nextWest);
        }
        if(southNeighbour == null && westNeighbour != null){
            westNeighbour.connectToNeighbours(this, null, westNeighbour.westNeighbour);
        }
    }
}