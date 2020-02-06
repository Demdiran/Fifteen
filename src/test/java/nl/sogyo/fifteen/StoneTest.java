package nl.sogyo.fifteen;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StoneTest{
    @Test
    public void TestStoneHasEast(){
        NonStone nonStone = new NonStone();
        Stone stone11 = (Stone) nonStone.getNorth().getWest();
        Stone stone12 = (Stone) stone11.getEast();
        int value12 = stone12.getValue();
        assertEquals(12, value12);
    }

    @Test
    public void TestStoneHasEast2(){
        NonStone nonStone = new NonStone();
        Stone stone8 = (Stone) nonStone.getStepsNorth(2);
        Stone stone5 = (Stone) stone8.getStepsWest(3);
        Stone stone6 = (Stone) stone5.getEast();
        int value6 = stone6.getValue();
        assertEquals(6, value6);
    }

    @Test
    public void TestStoneHasSouth(){
        NonStone nonStone = new NonStone();
        Stone stone11 = (Stone) nonStone.getNorth().getWest();
        Stone stone15 = (Stone) stone11.getSouth();
        int value15 = stone15.getValue();
        assertEquals(15, value15);
    }

    @Test
    public void TestCreationConsistency(){
        NonStone nonStone = new NonStone();
        Stone stone11_1 = (Stone) nonStone.getWest().getNorth();
        Stone stone11_2 = (Stone) nonStone.getNorth().getWest();
        assertEquals(stone11_1, stone11_2);        
    }
}