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
}