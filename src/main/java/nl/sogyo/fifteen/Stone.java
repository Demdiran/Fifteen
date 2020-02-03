package nl.sogyo.fifteen;

class Stone{
    private int value;
    private Stone north;
    private Stone west;

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

    Stone getNorth(){
        return this.north;
    }

    Stone getWest(){
        return this.west;
    }

    int getValue(){
        return value;
    }

    Stone getStepsWest(int steps){
        if(steps == 0){
            return this;
        }
        else{
            return this.west.getStepsWest(steps - 1);
        }
    }

    Stone getStepsNorth(int steps){
        if(steps == 0){
            return this;
        }
        else{
            return this.north.getStepsNorth(steps - 1);
        }
    }
}