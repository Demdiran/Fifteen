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
}