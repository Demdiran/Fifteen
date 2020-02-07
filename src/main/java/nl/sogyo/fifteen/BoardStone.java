package nl.sogyo.fifteen;

class BoardStone extends BoardPart{
    private int value;

    BoardStone(int value){
        this.value = value;
        boolean isLeftOfBoard = value % 4 == 1;
        if(!isLeftOfBoard){
            this.westNeighbour = new BoardStone(value - 1);
        }
        if(value > 4){
            this.northNeighbour = new BoardStone(value - 4);
        }
    }

    boolean isSolved(){
        boolean northSolved;
        boolean westSolved;
        if(this.northNeighbour == null && this.value - 4 <= 0)
            northSolved = true;
        else if(this.northNeighbour == null)
            return false;
        else
            northSolved = (((BoardStone) this.northNeighbour).getValue() == this.value - 4) && this.northNeighbour.isSolved();

        if(this.westNeighbour == null && this.value % 4 == 1)
            westSolved = true;
        else if(this.westNeighbour == null)
            westSolved = false;
        else
            westSolved = (((BoardStone) this.westNeighbour).getValue() == this.value - 1 && this.westNeighbour.isSolved());
        return westSolved && northSolved;
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