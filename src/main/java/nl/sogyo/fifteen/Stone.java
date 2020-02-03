package nl.sogyo.fifteen;

class Stone extends SuperStone{
    private int value;

    Stone(int value){
        this.value = value;
        boolean isLeftOfBoard = value % 4 == 1;
        if(!isLeftOfBoard){
            this.west = new Stone(value - 1, this);
        }
        if(value > 4){
            this.north = new Stone(value - 4);
        }
    }

    Stone(int value, SuperStone east){
        this(value);
        this.east = east;
    }

    int getValue(){
        return value;
    }
}