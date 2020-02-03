package nl.sogyo.fifteen;

class NonStone{
    private Stone north;
    private Stone west;

    public NonStone(){
        this.north = new Stone(12);
        this.west = new Stone(15);
    }

    Stone getNorth(){
        return this.north;
    }

    Stone getEast(){
        return null;
    }

    Stone getSouth(){
        return null;
    }

    Stone getWest(){
        return this.west;
    }

    Stone getStepsWest(int steps){
        return this.west.getStepsWest(steps - 1);
    }

    Stone getStepsNorth(int steps){
        return this.north.getStepsNorth(steps - 1);
    }
}