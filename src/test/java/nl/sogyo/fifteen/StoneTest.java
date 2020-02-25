package nl.sogyo.fifteen;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StoneTest{
    @Test
    public void TestStoneHasEast(){
        BoardHole nonStone = new BoardHole();
        BoardStone stone11 = (BoardStone) nonStone.getNorthNeighbour().getWestNeighbour();
        BoardStone stone12 = (BoardStone) stone11.getEastNeighbour();
        int value12 = stone12.getValue();
        assertEquals(12, value12);
    }

    @Test
    public void TestStoneHasEast2(){
        BoardHole nonStone = new BoardHole();
        BoardStone stone8 = (BoardStone) nonStone.getStepsNorth(2);
        BoardStone stone5 = (BoardStone) stone8.getStepsWest(3);
        BoardStone stone6 = (BoardStone) stone5.getEastNeighbour();
        int value6 = stone6.getValue();
        assertEquals(6, value6);
    }

    @Test
    public void TestStoneHasSouth(){
        BoardHole nonStone = new BoardHole();
        BoardStone stone11 = (BoardStone) nonStone.getNorthNeighbour().getWestNeighbour();
        BoardStone stone15 = (BoardStone) stone11.getSouthNeighbour();
        int value15 = stone15.getValue();
        assertEquals(15, value15);
    }

    @Test
    public void TestCreationConsistency(){
        BoardHole nonStone = new BoardHole();
        BoardStone stone11_1 = (BoardStone) nonStone.getWestNeighbour().getNorthNeighbour();
        BoardStone stone11_2 = (BoardStone) nonStone.getNorthNeighbour().getWestNeighbour();
        assertEquals(stone11_1, stone11_2);        
    }
}