package nl.sogyo.fifteen;

class NonStone extends SuperStone{

    public NonStone(){
        this.north = new Stone(12);
        this.west = new Stone(15, this);
        connectToNeighbours(null, null, this.west);
    }
}