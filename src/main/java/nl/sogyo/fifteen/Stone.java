package nl.sogyo.fifteen;


public class Stone{
    private int value;
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

    public Stone getFromCoordinate(int x, int y){
        return getStepsEast(x).getStepsSouth(y);
    }

    public Stone getStepsEast(int steps){
        if(steps == 0){
            return this;
        }
        else if(this.east != null){
            return this.east.getStepsEast(steps - 1);
        }
        else{
            throw new OutOfBoardException("Tried to acces stone too much to the east.");
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
            throw new OutOfBoardException("Tried to acces stone too much to the south.");
        }
    }

    private void move(){
        if(this.north != null && this.north.getValue() == 16){
            this.north.value = this.value;
            this.value = 16;
        }else if(this.west != null && this.west.getValue() == 16){
            this.west.value = this.value;
            this.value = 16;
        }else if(this.south != null && this.south.getValue() == 16){
            this.south.value = this.value;
            this.value = 16;
        }else if(this.east != null && this.east.getValue() == 16){
            this.east.value = this.value;
            this.value = 16;
        }
        else{
            throw new RuntimeException();
        }

    }

}