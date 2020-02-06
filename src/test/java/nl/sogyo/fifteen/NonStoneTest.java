package nl.sogyo.fifteen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class NonStoneTest{

    @Test
    public void TestNonStoneHasNorth(){
        NonStone nonStone = new NonStone();
        Stone north = (Stone) nonStone.getNorth();
        assert(north != null);
    }

    @Test
    public void TestGetNorthConsistent(){
        NonStone nonStone = new NonStone();
        Stone north1 = (Stone) nonStone.getNorth();
        Stone north2 = (Stone) nonStone.getNorth();
        assertEquals(north1, north2);
    }

    @Test
    public void Test3North(){
        NonStone nonStone = new NonStone();
        Stone north3 = (Stone) nonStone.getStepsNorth(3);
        int value4 = north3.getValue();
        assertEquals(4, value4);
    }

    @Test
    public void TestNonStoneHasEast(){
        NonStone nonStone = new NonStone();
        Stone east = (Stone) nonStone.getEast();
        assert(east == null);
    }

    @Test
    public void TestGetEastConsistent(){
        NonStone nonStone = new NonStone();
        Stone east1 = (Stone) nonStone.getEast();
        Stone east2 = (Stone) nonStone.getEast();
        assertEquals(east1, east2);
    }

    @Test
    public void TestNonStoneHasSouth(){
        NonStone nonStone = new NonStone();
        Stone south = (Stone) nonStone.getSouth();
        assert(south == null);
    }

    @Test
    public void TestGetSouthConsistent(){
        NonStone nonStone = new NonStone();
        Stone south1 = (Stone) nonStone.getSouth();
        Stone south2 = (Stone) nonStone.getSouth();
        assertEquals(south1, south2);
    }

    @Test
    public void TestNonStoneHasWest(){
        NonStone nonStone = new NonStone();
        Stone west = (Stone) nonStone.getWest();
        assert(west != null);
    }

    @Test
    public void TestGetWestConsistent(){
        NonStone nonStone = new NonStone();
        Stone west1 = (Stone) nonStone.getWest();
        Stone west2 = (Stone) nonStone.getWest();
        assertEquals(west1, west2);
    }

    @Test
    public void TestWestValue(){
        NonStone nonStone = new NonStone();
        Stone west = (Stone) nonStone.getWest();
        assertEquals(15, west.getValue());
    }

    @Test
    public void TestWestHasWest(){
        NonStone nonStone = new NonStone();
        Stone west = (Stone) nonStone.getWest();
        Stone westwest = (Stone) west.getWest();
        assert(westwest != null);
        assert(westwest != west);
    }

    @Test
    public void TestWestWestValue(){
        NonStone nonStone = new NonStone();
        Stone westwest = (Stone) nonStone.getWest().getWest();
        int wwvalue = westwest.getValue();
        assertEquals(14, wwvalue);
    }

    @Test
    public void Test3West(){
        NonStone nonStone = new NonStone();
        Stone stone13 = (Stone) nonStone.getStepsWest(3);
        int value13 = stone13.getValue();
        assertEquals(13, value13);
    }

    @Test
    public void TestMoveNorthOwnNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("north");
        Stone stone12 = (Stone) nonStone.getSouth();
        Stone stone11 = (Stone) nonStone.getWest();
        Stone stone8 = (Stone) nonStone.getNorth();
        Stone none = (Stone) nonStone.getEast();

        assertEquals(12, stone12.getValue());
        assertEquals(11, stone11.getValue());
        assertEquals(8, stone8.getValue());
        assertEquals(null, none);
    }
    @Test
    public void TestMoveNorthOtherNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("north");
        Stone stone12 = (Stone) nonStone.getSouth();
        Stone stone15 = (Stone) stone12.getWest();
        
        TestNeighbours(nonStone);
        TestNeighbours(stone12);
        assertEquals(15, stone15.getValue());
    }

    @Test
    public void TestMoveWestOwnNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("west");
        Stone none = (Stone) nonStone.getSouth();
        Stone stone14 = (Stone) nonStone.getWest();
        Stone stone11 = (Stone) nonStone.getNorth();
        Stone stone15 = (Stone) nonStone.getEast();
        assertEquals(15, stone15.getValue());
        assertEquals(11, stone11.getValue());
        assertEquals(14, stone14.getValue());
        assertEquals(null, none);
    }

    @Test
    public void TestMoveWestOtherNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("west");
        Stone stone15 = (Stone) nonStone.getEast();
        Stone stone12 = (Stone) stone15.getNorth();
        
        assertEquals(12, stone12.getValue());
        assertEquals(15, stone15.getValue());
        TestNeighbours(nonStone);
        TestNeighbours(stone15);
    }

    @Test
    public void TestMoveWestNorthNorthOwnNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("west");
        nonStone.move("north");
        nonStone.move("north");

        Stone stone7 = (Stone) nonStone.getSouth();
        Stone stone6 = (Stone) nonStone.getWest();
        Stone stone8 = (Stone) nonStone.getEast();
        Stone stone3 = (Stone) nonStone.getNorth();

        assertEquals(7, stone7.getValue());
        assertEquals(6, stone6.getValue());
        assertEquals(8, stone8.getValue());
        assertEquals(3, stone3.getValue());
    }

    @Test
    public void TestMoveWestNorthNorthOtherNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("west");
        nonStone.move("north");
        nonStone.move("north");

        Stone stone7 = (Stone) nonStone.getSouth();
        Stone stone12 = (Stone) stone7.getEast();
        Stone stone10 = (Stone) stone7.getWest();
        Stone stone11 = (Stone) stone7.getSouth();

        assertEquals(12, stone12.getValue());
        assertEquals(11, stone11.getValue());
        assertEquals(10, stone10.getValue());
        TestNeighbours(nonStone);
        TestNeighbours(stone7);
    }

    @Test
    public void TestMoveNorthWestWestOwnNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("north");
        nonStone.move("west");
        nonStone.move("west");
        
        Stone stone14 = (Stone) nonStone.getSouth();
        Stone stone9 = (Stone) nonStone.getWest();
        Stone stone10 = (Stone) nonStone.getEast();
        Stone stone6 = (Stone) nonStone.getNorth();

        assertEquals(6, stone6.getValue());
        assertEquals(10, stone10.getValue());
        assertEquals(9, stone9.getValue());
        assertEquals(14, stone14.getValue());
    }

    @Test
    public void TestMoveNorthWestWestOtherNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("north");
        nonStone.move("west");
        nonStone.move("west");

        Stone stone10 = (Stone) nonStone.getEast();
        Stone stone7 = (Stone) stone10.getNorth();
        Stone stone11 = (Stone) stone10.getEast();
        Stone stone15 = (Stone) stone10.getSouth();

        assertEquals(7, stone7.getValue());
        assertEquals(11, stone11.getValue());
        assertEquals(15, stone15.getValue());
        TestNeighbours(nonStone);
        TestNeighbours(stone10);
    }

    @Test
    public void TestMoveNorthThrice(){
        NonStone nonStone = new NonStone();
        nonStone.move("north");
        nonStone.move("north");
        nonStone.move("north");

        Stone stone3 = (Stone) nonStone.getWest();
        Stone stone4 = (Stone) nonStone.getSouth();

        assertEquals(3, stone3.getValue());
        assertEquals(4, stone4.getValue());
    }

    @Test
    public void TestMoveSouthOwnNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("north");
        nonStone.move("north");
        nonStone.move("west");
        nonStone.move("south");

        Stone stone11 = (Stone) nonStone.getNorth();
        Stone stone8 = (Stone) nonStone.getEast();
        Stone stone15 = (Stone) nonStone.getSouth();
        Stone stone10 = (Stone) nonStone.getWest();

        assertEquals(11, stone11.getValue());
        assertEquals(8, stone8.getValue());
        assertEquals(15, stone15.getValue());
        assertEquals(10, stone10.getValue());
    }

    @Test
    public void TestMoveSouthOtherNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("north");
        nonStone.move("north");
        nonStone.move("west");
        nonStone.move("south");

        Stone stone11 = (Stone) nonStone.getNorth();
        Stone stone3 = (Stone) stone11.getNorth();
        Stone stone6 = (Stone) stone11.getWest();
        Stone stone7 = (Stone) stone11.getEast();

        assertEquals(3, stone3.getValue());
        assertEquals(6, stone6.getValue());
        assertEquals(7, stone7.getValue());
        TestNeighbours(nonStone);
        TestNeighbours(stone11);

    }

    @Test
    public void TestMoveEastOwnNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("west");
        nonStone.move("west");
        nonStone.move("north");
        nonStone.move("east");

        Stone stone7 = (Stone) nonStone.getNorth();
        Stone stone12 = (Stone) nonStone.getEast();
        Stone stone14 = (Stone) nonStone.getSouth();
        Stone stone11 = (Stone) nonStone.getWest();

        assertEquals(7, stone7.getValue());
        assertEquals(12, stone12.getValue());
        assertEquals(14, stone14.getValue());
        assertEquals(11, stone11.getValue());
    }

    @Test
    public void TestMoveEastOtherNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("west");
        nonStone.move("west");
        nonStone.move("north");
        nonStone.move("east");

        Stone stone11 = (Stone) nonStone.getWest();
        Stone stone6 = (Stone) stone11.getNorth();
        Stone stone10 = (Stone) stone11.getSouth();
        Stone stone9 = (Stone) stone11.getWest();
        
        assertEquals(6, stone6.getValue());
        assertEquals(9, stone9.getValue());
        assertEquals(10, stone10.getValue());
        TestNeighbours(nonStone);
        TestNeighbours(stone11);
    }

    @Test
    public void TestGenerateRandomPuzzle(){
        NonStone nonStone = new NonStone();
        nonStone.generatePuzzle(25);
    }

    private void TestNeighbours(SuperStone stone){
        if(stone.getNorth() != null)
            assertEquals(stone, stone.getNorth().getSouth(), "north failed");
        if(stone.getWest() != null)
            assertEquals(stone, stone.getWest().getEast(), "west failed");
        if(stone.getEast() != null)
            assertEquals(stone, stone.getEast().getWest(), "east failed");
        if(stone.getSouth() != null)
            assertEquals(stone, stone.getSouth().getNorth(), "south failed");
    }
}