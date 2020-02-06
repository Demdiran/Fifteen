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
        int value12 = stone12.getValue();
        int value11 = stone11.getValue();
        int value8 = stone8.getValue();
        assertEquals(12, value12);
        assertEquals(11, value11);
        assertEquals(8, value8);
        assertEquals(null, none);
    }
    @Test
    public void TestMoveNorthOtherNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("north");
        Stone stone12 = (Stone) nonStone.getSouth();
        Stone stone11 = (Stone) nonStone.getWest();
        Stone stone8 = (Stone) nonStone.getNorth();
        Stone stone15 = (Stone) stone12.getWest();
        SuperStone this12 = stone12.getNorth();
        SuperStone this11 = stone11.getEast();
        SuperStone this8 = stone8.getSouth();
        int value15 = stone15.getValue();
        assertEquals(nonStone, this12);
        assertEquals(nonStone, this11);
        assertEquals(nonStone, this8);
        assertEquals(15, value15);
    }

    @Test
    public void TestMoveWestOwnNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("west");
        Stone none = (Stone) nonStone.getSouth();
        Stone stone14 = (Stone) nonStone.getWest();
        Stone stone11 = (Stone) nonStone.getNorth();
        Stone stone15 = (Stone) nonStone.getEast();
        int value14 = stone14.getValue();
        int value11 = stone11.getValue();
        int value15 = stone15.getValue();
        assertEquals(15, value15);
        assertEquals(11, value11);
        assertEquals(14, value14);
        assertEquals(null, none);
    }

    @Test
    public void TestMoveWestOtherNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("west");
        Stone stone14 = (Stone) nonStone.getWest();
        Stone stone15 = (Stone) nonStone.getEast();
        Stone stone11 = (Stone) nonStone.getNorth();
        Stone stone12 = (Stone) stone15.getNorth();
        SuperStone this14 = stone14.getEast();
        SuperStone this11 = stone11.getSouth();
        SuperStone this15 = stone15.getWest();
        int value12 = stone12.getValue();
        assertEquals(nonStone, this14);
        assertEquals(nonStone, this11);
        assertEquals(nonStone, this15);
        assertEquals(12, value12);
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
        int value7 = stone7.getValue();
        int value6 = stone6.getValue();
        int value8 = stone8.getValue();
        int value3 = stone3.getValue();
        assertEquals(7, value7);
        assertEquals(6, value6);
        assertEquals(8, value8);
        assertEquals(3, value3);
    }

    @Test
    public void TestMoveWestNorthNorthOtherNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("west");
        nonStone.move("north");
        nonStone.move("north");
        Stone stone7 = (Stone) nonStone.getSouth();
        Stone stone6 = (Stone) nonStone.getWest();
        Stone stone8 = (Stone) nonStone.getEast();
        Stone stone3 = (Stone) nonStone.getNorth();
        Stone stone12 = (Stone) stone7.getEast();
        Stone stone10 = (Stone) stone7.getWest();
        Stone stone11 = (Stone) stone7.getSouth();
        SuperStone this7 = stone7.getNorth();
        SuperStone this6 = stone6.getEast();
        SuperStone this8 = stone8.getWest();
        SuperStone this3 = stone3.getSouth();
        int value12 = stone12.getValue();
        int value10 = stone10.getValue();
        int value11 = stone11.getValue();
        assertEquals(12, value12);
        assertEquals(11, value11);
        assertEquals(10, value10);
        assertEquals(nonStone, this7, "stone7 failed");
        assertEquals(nonStone, this6, "stone6 failed");
        assertEquals(nonStone, this3, "stone3 failed");
        assertEquals(nonStone, this8, "stone8 failed");
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

        int value14 = stone14.getValue();
        int value9 = stone9.getValue();
        int value10 = stone10.getValue();
        int value6 = stone6.getValue();

        assertEquals(6, value6);
        assertEquals(10, value10);
        assertEquals(9, value9);
        assertEquals(14, value14);
    }

    @Test
    public void TestMoveNorthWestWestOtherNeighbours(){
        NonStone nonStone = new NonStone();
        nonStone.move("north");
        nonStone.move("west");
        nonStone.move("west");
        
        Stone stone14 = (Stone) nonStone.getSouth();
        Stone stone9 = (Stone) nonStone.getWest();
        Stone stone10 = (Stone) nonStone.getEast();
        Stone stone6 = (Stone) nonStone.getNorth();

        Stone stone7 = (Stone) stone10.getNorth();
        Stone stone11 = (Stone) stone10.getEast();
        Stone stone15 = (Stone) stone10.getSouth();

        SuperStone this6 = stone6.getSouth();
        SuperStone this9 = stone9.getEast();
        SuperStone this10 = stone10.getWest();
        SuperStone this14 = stone14.getNorth();

        int value7 = stone7.getValue();
        int value11 = stone11.getValue();
        int value15 = stone15.getValue();

        assertEquals(7, value7);
        assertEquals(11, value11);
        assertEquals(15, value15);
        assertEquals(nonStone, this6, "stone6 failed");
        assertEquals(nonStone, this9, "stone9 failed");
        assertEquals(nonStone, this10, "stone10 failed");
        assertEquals(nonStone, this14, "stone14 failed");
    }
}