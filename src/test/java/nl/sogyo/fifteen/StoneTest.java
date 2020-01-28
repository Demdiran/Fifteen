package nl.sogyo.fifteen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class StoneTest{

    @Test
    public void TestValue(){
        Stone s = new Stone();
        int v = s.getValue();
        assert(v == 1);
    }

    @Test
    public void TestGetFromCoordinate1(){
        Stone stone1 = new Stone();
        Stone stone10 = stone1.getFromCoordinate(1, 2);
        assertEquals(10, stone10.getValue());
    }

//East neighbour generation------------------------------------------------------

    @Test
    public void TestEastNeighbour(){
        Stone s1 = new Stone();
        Stone s2 = s1.getEast();
        int v2 = s2.getValue();
        assert(v2 == 2);
    }

    @Test
    public void TestEastTwoStepsOver(){
        Stone s1 = new Stone();
        Stone s2 = s1.getEast();
        Stone s3 = s2.getEast();
        int v3 = s3.getValue();
        assert(v3 == 3);
    }

    @Test
    public void TestEastThreeStepsOver(){
        Stone s1 = new Stone();
        Stone s2 = s1.getEast();
        Stone s3 = s2.getEast();
        Stone s4 = s3.getEast();
        int v4 = s4.getValue();
        assert(v4 == 4);
    }
    @Test
    public void TestGetXStepsEast1(){
        Stone s1 = new Stone();
        Stone s3 = s1.getStepsEast(2);
        Stone s2 = s1.getEast();
        Stone result = s2.getEast();
        assert(s3 == result);
    }

    @Test
    public void TestGetXStepsEast2(){
        Stone s1 = new Stone();
        Stone s4 = s1.getStepsEast(3);
        Stone s2 = s1.getEast();
        Stone s3 = s2.getEast();
        Stone result = s3.getEast();
        assert(s4 == result);
    }

    @Test
    public void TestGetXStepsEastOutOfBounds(){
        try {
            Stone s1 = new Stone();
            Stone se = s1.getStepsEast(4);
            fail("Went out of bounds, but did not trigger exception.");
            
        } catch (OutOfBoardException e) {
        } catch(Exception e){
            fail("Did not throw the right exception.");
        }

    }

//South neighbour generation------------------------------------------------------

    @Test
    public void TestSouthNeighbour(){
        Stone s1 = new Stone();
        Stone s5 = s1.getSouth();
        int v5 = s5.getValue();
        assert(v5 == 5);
    }

    @Test
    public void TestSouthTwoStepsOver(){
        Stone s1 = new Stone();
        Stone s5 = s1.getSouth();
        Stone s9 = s5.getSouth();
        int v9 = s9.getValue();
        assert(v9 == 9);
    }

    @Test
    public void TestSouthThreeStepsOver(){
        Stone s1 = new Stone();
        Stone s5 = s1.getSouth();
        Stone s9 = s5.getSouth();
        Stone s13 = s9.getSouth();
        int v13 = s13.getValue();
        assert(v13 == 13);
    }
    @Test
    public void TestGetXStepsSouth1(){
        Stone s1 = new Stone();
        Stone s9 = s1.getStepsSouth(2);
        Stone s5 = s1.getSouth();
        Stone result = s5.getSouth();
        assert(s9 == result);
    }

    @Test
    public void TestGetXStepsSouth2(){
        Stone s1 = new Stone();
        Stone s13 = s1.getStepsSouth(3);
        Stone s5 = s1.getSouth();
        Stone s9 = s5.getSouth();
        Stone result = s9.getSouth();
        assert(s13 == result);
    }

    @Test
    public void TestGetXStepsSouthOutOfBounds(){
        try {
            Stone s1 = new Stone();
            Stone ss = s1.getStepsSouth(4);
            fail("Went out of bounds, but did not trigger exception.");
            
        } catch (OutOfBoardException e) {
        } catch(Exception e){
            fail("Did not throw the right exception.");
        }

    }

//Column connection------------------------------------------------------

    @Test
    public void TestStone11IsStone11(){
        Stone s1 = new Stone();
        Stone s11_1 = s1.getStepsEast(2).getStepsSouth(2);
        Stone s11_2 = s1.getStepsSouth(2).getStepsEast(2);
        assert(s11_1 == s11_2);
    }

    @Test
    public void TestGetCoordinate1(){
        Stone s1 = new Stone();
        Stone s6 = s1.getFromCoordinate(1,1);
        Stone s6_2 = s1.getEast().getSouth();
        assert(s6 == s6_2);
    }

    @Test
    public void TestGetCoordinate2(){
        Stone s1 = new Stone();
        Stone s7 = s1.getFromCoordinate(2,1);
        Stone s7_2 = s1.getEast().getSouth().getEast();
        assert(s7 == s7_2);
    }

//North neighbour connection------------------------------------------------------

    @Test
    public void TestNorthNeighbour1(){
        Stone stone1 = new Stone();
        Stone stone5 = stone1.getSouth();
        Stone stone1_2 = stone5.getNorth();
        assert(stone1 == stone1_2);
    }

    @Test
    public void TestNorthNeighbour2(){
        Stone stone1 = new Stone();
        Stone stone5 = stone1.getSouth();
        Stone stone9 = stone1.getStepsSouth(2);
        Stone stone5_2 = stone9.getNorth();
        assert(stone5 == stone5_2);
    }

    @Test
    public void TestNorthNeighbour3(){
        Stone stone1 = new Stone();
        Stone stone6 = stone1.getFromCoordinate(1, 1);
        Stone stone2 = stone1.getEast();
        Stone stone2_2 = stone6.getNorth();
        assert(stone2 == stone2_2);
    }

//West neighbour connection------------------------------------------------------

    @Test
    public void TestWestNeighbour1(){
        Stone stone1 = new Stone();
        Stone stone2 = stone1.getEast();
        Stone stone1_2 = stone2.getWest();
        assert(stone1 == stone1_2);
    }

    @Test
    public void TestWestNeighbour2(){
        Stone stone1 = new Stone();
        Stone stone2 = stone1.getEast();
        Stone stone3 = stone2.getEast();
        Stone stone2_2 = stone3.getWest();
        assert(stone2 == stone2_2);
    }

    @Test
    public void TestWestNeighbour3(){
        Stone stone1 = new Stone();
        Stone stone6 = stone1.getFromCoordinate(1, 1);
        Stone stone5 = stone1.getSouth();
        Stone stone5_2 = stone6.getWest();
        assert(stone5 == stone5_2);
    }

//Mixed neighbour tests------------------------------------------------------

    @Test
    public void TestNeighbourMixed1(){
        Stone stone1 = new Stone();
        Stone stone15 = stone1.getFromCoordinate(2, 3);
        Stone stone12 = stone1.getFromCoordinate(3, 2);
        Stone stone16_1 = stone15.getEast();
        Stone stone16_2 = stone12.getSouth();
        assert(stone16_1 == stone16_2);
    }
    @Test
    public void TestNeighbourMixed2(){
        Stone stone1 = new Stone();
        Stone stone15 = stone1.getFromCoordinate(2, 3);
        Stone stone12 = stone1.getFromCoordinate(3, 2);
        Stone stone11_1 = stone15.getNorth();
        Stone stone11_2 = stone12.getWest();
        assert(stone11_1 == stone11_2);
    }

//Doing a move------------------------------------------------------

    @Test
    public void TestValidMoveMovedStone1(){
        Stone stone1 = new Stone();
        stone1.doMove(2,3);
        Stone bottomRight = stone1.getFromCoordinate(3, 3);
        assertEquals(15, bottomRight.getValue());       
    }
    
    @Test
    public void TestValidMoveMovedStone2(){
        Stone stone1 = new Stone();
        stone1.doMove(3,2);
        Stone bottomRight = stone1.getFromCoordinate(3, 3);
        assert(bottomRight.getValue() == 12);       
    }
    
    @Test
    public void TestValidMoveMovedStone3(){
        Stone stone1 = new Stone();
        stone1.doMove(2,3);
        stone1.doMove(3, 3);
        Stone stone2_3 = stone1.getFromCoordinate(2, 3);
        assert(stone2_3.getValue() == 15);
    }
    
    @Test
    public void TestValidMoveMovedStone4(){
        Stone stone1 = new Stone();
        stone1.doMove(3,2);
        stone1.doMove(3, 3);
        Stone stone3_2 = stone1.getFromCoordinate(3, 2);
        assert(stone3_2.getValue() == 12);
    }

//Origin has moved-------------------------------------------

    @Test
    public void TestOriginHasMovedEast(){
        Stone stone1 = new Stone();
        stone1.doMove(3, 2);
        stone1.doMove(3, 1);
        stone1.doMove(3, 0);
        stone1.doMove(2, 0);
        stone1.doMove(1, 0);
        stone1.doMove(0, 0);
        Stone stone6 = stone1.getFromCoordinate(1, 1);
        assertEquals(6, stone6.getValue());
    }

    @Test
    public void TestOriginHasMovedSouth(){
        Stone stone1 = new Stone();
        stone1.doMove(2, 3);
        stone1.doMove(1, 3);
        stone1.doMove(0, 3);
        stone1.doMove(0, 2);
        stone1.doMove(0, 1);
        stone1.doMove(0, 0);
        Stone stone6 = stone1.getFromCoordinate(1, 1);
        assertEquals(6, stone6.getValue());
    }

    @Test
    public void TestGetNorthOfOrigin(){
        Stone stone1 = new Stone();
        stone1.doMove(2, 3);
        stone1.doMove(1, 3);
        stone1.doMove(0, 3);
        stone1.doMove(0, 2);
        stone1.doMove(0, 1);
        stone1.doMove(0, 0);
        Stone stone16 = stone1.getFromCoordinate(0, 0);
        assertEquals(16, stone16.getValue());
    }

    @Test
    public void TestGetWestOfOrigin(){
        Stone stone1 = new Stone();
        stone1.doMove(3, 2);
        stone1.doMove(3, 1);
        stone1.doMove(3, 0);
        stone1.doMove(2, 0);
        stone1.doMove(1, 0);
        stone1.doMove(0, 0);
        Stone stone16 = stone1.getFromCoordinate(0, 0);
        assertEquals(16, stone16.getValue());
    }

//Printing the board-----------------------

    @Test
    public void TestBoardPrint1(){
        Stone stone1 = new Stone();
        String boardString = stone1.printBoard();
        String boardStringExpected = "|-----------|\n\r| 1| 2| 3| 4|\n\r|-----------|\n\r| 5| 6| 7| 8|\n\r|-----------|\n\r| 9|10|11|12|\n\r|-----------|\n\r|13|14|15|  |\n\r|-----------|";
        assertEquals(boardStringExpected, boardString);
    }

    @Test
    public void TestBoardPrintAfterMove1(){
        Stone stone1 = new Stone();
        stone1.doMove(3, 2);
        stone1.doMove(2, 2);
        String boardString = stone1.printBoard();
        String boardStringExpected = "|-----------|\n\r| 1| 2| 3| 4|\n\r|-----------|\n\r| 5| 6| 7| 8|\n\r|-----------|\n\r| 9|10|  |11|\n\r|-----------|\n\r|13|14|15|12|\n\r|-----------|";
        assertEquals(boardStringExpected, boardString);
    }

//Testing if puzzle is solved------------------------------

    @Test
    public void TestPuzzleSolvedTrue(){
        Stone stone1 = new Stone();
        boolean solved = stone1.isSolved();
        assert(solved);
    }

    @Test
    public void TestPuzzleSolvedFalse1(){
        Stone stone1 = new Stone();
        stone1.doMove(3, 2);
        boolean solved = stone1.isSolved();
        assert(!solved);
    }

    @Test
    public void TestPuzzleSolvedFalse2(){
        Stone stone1 = new Stone();
        stone1.doMove(3, 2);
        stone1.doMove(2, 2);
        stone1.doMove(2, 3);
        stone1.doMove(3, 3);
        boolean solved = stone1.isSolved();
        assert(!solved);
    }
}