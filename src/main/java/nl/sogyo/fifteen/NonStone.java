package nl.sogyo.fifteen;

class NonStone extends SuperStone{

    public NonStone(){
        this.north = new Stone(12);
        this.west = new Stone(15, this);
        connectToNeighbours(null, null, this.west);
    }

    public void move(String direction){
        SuperStone exNorth = this.north;
        SuperStone exWest = this.west;
        SuperStone exSouth = this.south;
        SuperStone exEast = this.east;
        if(direction == "north"){
            this.west = exNorth.west;
            if(this.west != null)
                this.west.east = this;
            this.north = exNorth.north;
            if(this.north != null)
                this.north.south = this;
            this.east = exNorth.east;
            if(this.east != null)
                this.east.west = this;

            this.south = exNorth;
            this.south.north = this;
            this.south.west = exWest;
            this.south.south = exSouth;
            this.south.east = exEast;
        }
        if(direction == "west"){
            this.west = exWest.west;
            if(this.west != null)
                this.west.east = this;
            this.north = exWest.north;
            if(this.north != null)
                this.north.south = this;
            this.south = exWest.south;
            if(this.south != null)
                this.south.north = this;
            this.east = exWest;
            this.east.west = this;
            this.east.north = exNorth;
            this.east.south = exSouth;
            this.east.east = exEast;
        }
    }
}