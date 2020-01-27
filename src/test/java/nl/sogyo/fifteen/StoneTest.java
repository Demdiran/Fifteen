package nl.sogyo.fifteen;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class StoneTest{

    @Test
    public void TestValue(){
        Stone s = new Stone();
        int v = s.getValue();
        assert(v == 1);
    }

//East neighbour generation--------------------

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

//South neighbour generation------------------

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

//Column connection------------------------------

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

//North connection-------------------------------

    @Test
    public void TestNorthNeighbour(){
        Stone s1 = new Stone();
        Stone s6 = s1.getFromCoordinate(1, 1);
        Stone s2 = s6.getNorth();
        assert(s2.getValue() == 2);
    }

//West connection-------------------------------------
}