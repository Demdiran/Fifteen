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

            if(exWest != null)
                exWest.east = this.south;
            if(exSouth != null)
                exSouth.north = this.south;
            if(exEast != null)
                exEast.west = this.south;
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

            if(exNorth != null)
                exNorth.south = this.east;
            if(exSouth != null)
                exSouth.north = this.east;
            if(exEast != null)
                exEast.west = this.east;
        }
        if(direction == "south"){
            this.west = exSouth.west;
            if(this.west != null)
                this.west.east = this;
            this.east = exSouth.east;
            if(this.east != null)
                this.east.west = this;
            this.south = exSouth.south;
            if(this.south != null)
                this.south.north = this;
            this.north = exSouth;
            this.north.south = this;
            this.north.west = exWest;
            this.north.east = exEast;
            this.north.north = exNorth;

            if(exWest != null)
                exWest.east = this.north;
            if(exNorth != null)
                exNorth.south = this.north;
            if(exEast != null)
                exEast.west = this.north;
        }
        if(direction == "east"){
            this.north = exEast.north;
            if(this.north != null)
                this.north.south = this;
            this.east = exEast.east;
            if(this.east != null)
                this.east.west = this;
            this.south = exEast.south;
            if(this.south != null)
                this.south.north = this;
            this.west = exEast;
            this.west.east = this;
            this.west.south = exSouth;
            this.west.west = exWest;
            this.west.north = exNorth;

            if(exSouth != null)
                exSouth.north = this.west;
            if(exWest != null)
                exWest.east = this.west;
            if(exNorth != null)
                exNorth.south = this.west;
        }
    }
}