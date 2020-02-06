package nl.sogyo.fifteen;

class Stone extends SuperStone{
    private int value;

    Stone(int value){
        this.value = value;
        boolean isLeftOfBoard = value % 4 == 1;
        if(!isLeftOfBoard){
            this.west = new Stone(value - 1);
        }
        if(value > 4){
            this.north = new Stone(value - 4);
        }
    }

    boolean isSolved(){
        boolean northSolved;
        boolean westSolved;
        if(this.north == null && this.value - 4 <= 0)
            northSolved = true;
        else if(this.north == null)
            return false;
        else
            northSolved = (((Stone) this.north).getValue() == this.value - 4) && this.north.isSolved();

        if(this.west == null && this.value % 4 == 1)
            westSolved = true;
        else if(this.west == null)
            westSolved = false;
        else
            westSolved = (((Stone) this.west).getValue() == this.value - 1 && this.west.isSolved());
        return westSolved && northSolved;
    }

    @Override
    int heuristicCalculation(){
        int total = this.getOwnHeuristic();
        if(this.west == null && this.south != null)
            total += this.south.heuristicCalculation();
        if(this.east != null)
            total += this.east.heuristicCalculation();
        return total;        
    }

    Stone(int value, SuperStone east){
        this(value);
    }


    int getValue(){
        return value;
    }

    int getOwnHeuristic(){
        int x = this.getXCoord();
        int y = this.getYCoord();
        int goalx = (this.value - 1) % 4;
        int goaly = (this.value - 1)/ 4;
        return Math.abs(x - goalx) + Math.abs(y - goaly);
    }
}