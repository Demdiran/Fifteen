package nl.sogyo.fifteen;

class BoardStone extends BoardPart{
    private int value;

    BoardStone(int value){
        this.value = value;
        boolean isLeftOfBoard = value % 4 == 1;
        boolean isTopOfBoard = value <= 4;
        if(!isLeftOfBoard){
            this.westNeighbour = new BoardStone(value - 1);
        }
        if(!isTopOfBoard){
            this.northNeighbour = new BoardStone(value - 4);
        }
    }

    boolean isSolved(){
        if(this.calculateOwnHeuristic() != 0)
            return false;
        boolean northSolved = this.checkNorthNeighbour();
        boolean westSolved = this.checkWestNeighbour();
        return westSolved && northSolved;
    }

    private boolean checkNorthNeighbour(){
        if(this.hasNorthNeighbour())
            return this.northNeighbour.isSolved();
        else{
            boolean shouldHaveNorthNeighbour = this.value > 4;
            return !shouldHaveNorthNeighbour;
        }
    }

    private boolean hasNorthNeighbour(){
        return this.northNeighbour != null;
    }

    private boolean checkWestNeighbour(){
        if(this.hasWestNeighbour())
            return this.westNeighbour.isSolved();
        else{
            boolean shouldHaveWestNeighbour = this.value % 4 != 1;
            return !shouldHaveWestNeighbour;
        }
    }

    private boolean hasWestNeighbour(){
        return this.westNeighbour != null;
    }

    @Override
    int calculateBoardHeuristic(){
        int total = this.calculateOwnHeuristic();
        if(this.westNeighbour == null && this.southNeighbour != null)
            total += this.southNeighbour.calculateBoardHeuristic();
        if(this.eastNeighbour != null)
            total += this.eastNeighbour.calculateBoardHeuristic();
        return total;        
    }

    BoardStone(int value, BoardPart east){
        this(value);
    }


    int getValue(){
        return value;
    }

    int calculateOwnHeuristic(){
        int x = this.getXCoordinate();
        int y = this.getYCoordinate();
        int goalx = (this.value - 1) % 4;
        int goaly = (this.value - 1)/ 4;
        return Math.abs(x - goalx) + Math.abs(y - goaly);
    }
}