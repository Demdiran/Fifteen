package nl.sogyo.fifteen;


public class Stone{
    private final int value;
    private Stone north;
    private Stone east;
    private Stone south;
    private Stone west;
    public static void main(String[] args){
    }
    public Stone(){
        //System.out.println("Created stone with value: " + 1);
        this.value = 1;
        this.east = new Stone(2);
        this.south = new Stone(5, this.east.south);
        this.south.setNorth(this);
        this.east.setWest(this);
    }
    public Stone(int v){
        //System.out.println("Created stone with value: " + v);
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
        //System.out.println("Created stone with value: " + v);
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

    public Stone getFromCoordinate(int x, int y){
        int xCoordinate = this.getXCoordinate(0);
        int yCoordinate = this.getYCoordinate(0);
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

    public int getXCoordinate(int stepsWestTaken){
        if(this.west == null){
            return stepsWestTaken;
        }
        else{
            return this.west.getXCoordinate(stepsWestTaken + 1);
        }
    }

    public int getYCoordinate(int stepsNorthTaken){
        if(this.north == null){
            return stepsNorthTaken;
        }
        else{
            return this.north.getYCoordinate(stepsNorthTaken + 1);
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

    private void move(){
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
        }else {
            throw new RuntimeException();
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

}