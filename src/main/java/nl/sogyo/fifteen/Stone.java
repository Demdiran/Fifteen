package nl.sogyo.fifteen;

public class Stone{
    private final int value;
    private Stone east;
    private Stone south;
    private Stone north;
    public static void main(String[] args){
        Stone s = new Stone();
        System.out.println(s.getStepsEast(2).getStepsSouth(2).getValue());
        System.out.println(s.getStepsSouth(2).getStepsEast(2).getValue());
    }
    public Stone(){
        //System.out.println("Created stone with value: " + 1);
        value = 1;
        east = new Stone(2, null, null);
        south = new Stone(5, this, this.east.south);
    }
    public Stone(int v, Stone northNeighbour, Stone eastNeighbour){
        //System.out.println("Created stone with value: " + v);
        value = v;
        if(northNeighbour != null){
            north = northNeighbour;        
        }
        if(eastNeighbour != null){
            east = eastNeighbour;
        }else if(v % 4 != 0){
            east = new Stone(v + 1, null, null);
        }
        if((v-1) / 4 < 3){
            if(east == null){
                south = new Stone(v + 4, this, null);
            }
            else{
                south = new Stone(v + 4, this, this.east.south);
            }
        }
    }

    public int getValue(){
        return value;
    }

    public Stone getEast(){
        return east;
    }

    public Stone getSouth(){
        return south;
    }

    public Stone getNorth(){
        return north;
    }

    public Stone getFromCoordinate(int x, int y){
        return getStepsEast(x).getStepsSouth(y);
    }

    public Stone getStepsEast(int s){
        if(s == 0){
            return this;
        }
        else if(east != null){
            return east.getStepsEast(s - 1);
        }
        else{
            throw new OutOfBoardException("Tried to acces stone too much to the east.");
        }
    }

    public Stone getStepsSouth(int s){
        if(s == 0){
            return this;
        }
        else if(south != null){
            return south.getStepsSouth(s - 1);
        }
        else{
            throw new OutOfBoardException("Tried to acces stone too much to the south.");
        }
    }

}