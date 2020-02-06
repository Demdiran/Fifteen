package nl.sogyo.fifteen;

abstract class SuperStone{
    SuperStone north;
    SuperStone east;
    SuperStone south;
    SuperStone west;

    SuperStone(){};

    SuperStone getStepsNorth(int steps){
        if(steps == 0){
            return this;
        }
        else{
            return this.north.getStepsNorth(steps - 1);
        }
    }

    SuperStone getStepsEast(int steps){
        if(steps == 0){
            return this;
        }
        else{
            return this.east.getStepsEast(steps - 1);
        }
    }

    SuperStone getStepsSouth(int steps){
        if(steps == 0){
            return this;
        }
        else{
            return this.south.getStepsSouth(steps - 1);
        }
    }

    SuperStone getStepsWest(int steps){
        if(steps == 0){
            return this;
        }
        else{
            return this.west.getStepsWest(steps - 1);
        }
    }

    SuperStone getNorth(){
        return this.north;
    }

    SuperStone getEast(){
        return this.east;
    }

    SuperStone getSouth(){
        return this.south;
    }

    SuperStone getWest(){
        return this.west;
    }

    void connectToNeighbours(SuperStone east, SuperStone south, SuperStone west){
        this.east = east;
        this.south = south;
        this.west = west;
        if(this.north != null){
            SuperStone nextWest = west != null ? west.north : null;
            SuperStone nextEast = east != null ? east.north : null;
            this.north.connectToNeighbours(nextEast, this, nextWest);
        }
        if(south == null && west != null){
            west.connectToNeighbours(this, null, west.west);
        }
    }
}