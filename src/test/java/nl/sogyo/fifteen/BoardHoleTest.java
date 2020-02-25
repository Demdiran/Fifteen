package nl.sogyo.fifteen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class BoardHoleTest{

    @Test
    public void TestNonStoneHasNorth(){
        BoardHole nonStone = new BoardHole();
        BoardStone north = (BoardStone) nonStone.getNorthNeighbour();
        assert(north != null);
    }

    @Test
    public void TestGetNorthConsistent(){
        BoardHole nonStone = new BoardHole();
        BoardStone north1 = (BoardStone) nonStone.getNorthNeighbour();
        BoardStone north2 = (BoardStone) nonStone.getNorthNeighbour();
        assertEquals(north1, north2);
    }

    @Test
    public void Test3North(){
        BoardHole nonStone = new BoardHole();
        BoardStone north3 = (BoardStone) nonStone.getStepsNorth(3);
        int value4 = north3.getValue();
        assertEquals(4, value4);
    }

    @Test
    public void TestNonStoneHasEast(){
        BoardHole nonStone = new BoardHole();
        BoardStone east = (BoardStone) nonStone.getEastNeighbour();
        assert(east == null);
    }

    @Test
    public void TestGetEastConsistent(){
        BoardHole nonStone = new BoardHole();
        BoardStone east1 = (BoardStone) nonStone.getEastNeighbour();
        BoardStone east2 = (BoardStone) nonStone.getEastNeighbour();
        assertEquals(east1, east2);
    }

    @Test
    public void TestNonStoneHasSouth(){
        BoardHole nonStone = new BoardHole();
        BoardStone south = (BoardStone) nonStone.getSouthNeighbour();
        assert(south == null);
    }

    @Test
    public void TestGetSouthConsistent(){
        BoardHole nonStone = new BoardHole();
        BoardStone south1 = (BoardStone) nonStone.getSouthNeighbour();
        BoardStone south2 = (BoardStone) nonStone.getSouthNeighbour();
        assertEquals(south1, south2);
    }

    @Test
    public void TestNonStoneHasWest(){
        BoardHole nonStone = new BoardHole();
        BoardStone west = (BoardStone) nonStone.getWestNeighbour();
        assert(west != null);
    }

    @Test
    public void TestGetWestConsistent(){
        BoardHole nonStone = new BoardHole();
        BoardStone west1 = (BoardStone) nonStone.getWestNeighbour();
        BoardStone west2 = (BoardStone) nonStone.getWestNeighbour();
        assertEquals(west1, west2);
    }

    @Test
    public void TestWestValue(){
        BoardHole nonStone = new BoardHole();
        BoardStone west = (BoardStone) nonStone.getWestNeighbour();
        assertEquals(15, west.getValue());
    }

    @Test
    public void TestWestHasWest(){
        BoardHole nonStone = new BoardHole();
        BoardStone west = (BoardStone) nonStone.getWestNeighbour();
        BoardStone westwest = (BoardStone) west.getWestNeighbour();
        assert(westwest != null);
        assert(westwest != west);
    }

    @Test
    public void TestWestWestValue(){
        BoardHole nonStone = new BoardHole();
        BoardStone westwest = (BoardStone) nonStone.getWestNeighbour().getWestNeighbour();
        int wwvalue = westwest.getValue();
        assertEquals(14, wwvalue);
    }

    @Test
    public void Test3West(){
        BoardHole nonStone = new BoardHole();
        BoardStone stone13 = (BoardStone) nonStone.getStepsWest(3);
        int value13 = stone13.getValue();
        assertEquals(13, value13);
    }

    @Test
    public void TestMoveNorthOwnNeighbours()throws InvalidMoveException{
        BoardHole nonStone = new BoardHole();
        nonStone.move("north");
        BoardStone stone12 = (BoardStone) nonStone.getSouthNeighbour();
        BoardStone stone11 = (BoardStone) nonStone.getWestNeighbour();
        BoardStone stone8 = (BoardStone) nonStone.getNorthNeighbour();
        BoardStone none = (BoardStone) nonStone.getEastNeighbour();

        assertEquals(12, stone12.getValue());
        assertEquals(11, stone11.getValue());
        assertEquals(8, stone8.getValue());
        assertEquals(null, none);
    }
    @Test
    public void TestMoveNorthOtherNeighbours()throws InvalidMoveException{
        BoardHole nonStone = new BoardHole();
        nonStone.move("north");
        BoardStone stone12 = (BoardStone) nonStone.getSouthNeighbour();
        BoardStone stone15 = (BoardStone) stone12.getWestNeighbour();
        
        TestNeighbours(nonStone);
        TestNeighbours(stone12);
        assertEquals(15, stone15.getValue());
    }

    @Test
    public void TestMoveWestOwnNeighbours()throws InvalidMoveException{
        BoardHole nonStone = new BoardHole();
        nonStone.move("west");
        BoardStone none = (BoardStone) nonStone.getSouthNeighbour();
        BoardStone stone14 = (BoardStone) nonStone.getWestNeighbour();
        BoardStone stone11 = (BoardStone) nonStone.getNorthNeighbour();
        BoardStone stone15 = (BoardStone) nonStone.getEastNeighbour();
        assertEquals(15, stone15.getValue());
        assertEquals(11, stone11.getValue());
        assertEquals(14, stone14.getValue());
        assertEquals(null, none);
    }

    @Test
    public void TestMoveWestOtherNeighbours()throws InvalidMoveException{
        BoardHole nonStone = new BoardHole();
        nonStone.move("west");
        BoardStone stone15 = (BoardStone) nonStone.getEastNeighbour();
        BoardStone stone12 = (BoardStone) stone15.getNorthNeighbour();
        
        assertEquals(12, stone12.getValue());
        assertEquals(15, stone15.getValue());
        TestNeighbours(nonStone);
        TestNeighbours(stone15);
    }

    @Test
    public void TestMoveWestNorthNorthOwnNeighbours()throws InvalidMoveException{
        BoardHole nonStone = new BoardHole();
        nonStone.move("west");
        nonStone.move("north");
        nonStone.move("north");

        BoardStone stone7 = (BoardStone) nonStone.getSouthNeighbour();
        BoardStone stone6 = (BoardStone) nonStone.getWestNeighbour();
        BoardStone stone8 = (BoardStone) nonStone.getEastNeighbour();
        BoardStone stone3 = (BoardStone) nonStone.getNorthNeighbour();

        assertEquals(7, stone7.getValue());
        assertEquals(6, stone6.getValue());
        assertEquals(8, stone8.getValue());
        assertEquals(3, stone3.getValue());
    }

    @Test
    public void TestMoveWestNorthNorthOtherNeighbours()throws InvalidMoveException{
        BoardHole nonStone = new BoardHole();
        nonStone.move("west");
        nonStone.move("north");
        nonStone.move("north");

        BoardStone stone7 = (BoardStone) nonStone.getSouthNeighbour();
        BoardStone stone12 = (BoardStone) stone7.getEastNeighbour();
        BoardStone stone10 = (BoardStone) stone7.getWestNeighbour();
        BoardStone stone11 = (BoardStone) stone7.getSouthNeighbour();

        assertEquals(12, stone12.getValue());
        assertEquals(11, stone11.getValue());
        assertEquals(10, stone10.getValue());
        TestNeighbours(nonStone);
        TestNeighbours(stone7);
    }

    @Test
    public void TestMoveNorthWestWestOwnNeighbours()throws InvalidMoveException{
        BoardHole nonStone = new BoardHole();
        nonStone.move("north");
        nonStone.move("west");
        nonStone.move("west");
        
        BoardStone stone14 = (BoardStone) nonStone.getSouthNeighbour();
        BoardStone stone9 = (BoardStone) nonStone.getWestNeighbour();
        BoardStone stone10 = (BoardStone) nonStone.getEastNeighbour();
        BoardStone stone6 = (BoardStone) nonStone.getNorthNeighbour();

        assertEquals(6, stone6.getValue());
        assertEquals(10, stone10.getValue());
        assertEquals(9, stone9.getValue());
        assertEquals(14, stone14.getValue());
    }

    @Test
    public void TestMoveNorthWestWestOtherNeighbours()throws InvalidMoveException{
        BoardHole nonStone = new BoardHole();
        nonStone.move("north");
        nonStone.move("west");
        nonStone.move("west");

        BoardStone stone10 = (BoardStone) nonStone.getEastNeighbour();
        BoardStone stone7 = (BoardStone) stone10.getNorthNeighbour();
        BoardStone stone11 = (BoardStone) stone10.getEastNeighbour();
        BoardStone stone15 = (BoardStone) stone10.getSouthNeighbour();

        assertEquals(7, stone7.getValue());
        assertEquals(11, stone11.getValue());
        assertEquals(15, stone15.getValue());
        TestNeighbours(nonStone);
        TestNeighbours(stone10);
    }

    @Test
    public void TestMoveNorthThrice()throws InvalidMoveException{
        BoardHole nonStone = new BoardHole();
        nonStone.move("north");
        nonStone.move("north");
        nonStone.move("north");

        BoardStone stone3 = (BoardStone) nonStone.getWestNeighbour();
        BoardStone stone4 = (BoardStone) nonStone.getSouthNeighbour();

        assertEquals(3, stone3.getValue());
        assertEquals(4, stone4.getValue());
    }

    @Test
    public void TestMoveSouthOwnNeighbours()throws InvalidMoveException{
        BoardHole nonStone = new BoardHole();
        nonStone.move("north");
        nonStone.move("north");
        nonStone.move("west");
        nonStone.move("south");

        BoardStone stone11 = (BoardStone) nonStone.getNorthNeighbour();
        BoardStone stone8 = (BoardStone) nonStone.getEastNeighbour();
        BoardStone stone15 = (BoardStone) nonStone.getSouthNeighbour();
        BoardStone stone10 = (BoardStone) nonStone.getWestNeighbour();

        assertEquals(11, stone11.getValue());
        assertEquals(8, stone8.getValue());
        assertEquals(15, stone15.getValue());
        assertEquals(10, stone10.getValue());
    }

    @Test
    public void TestMoveSouthOtherNeighbours()throws InvalidMoveException{
        BoardHole nonStone = new BoardHole();
        nonStone.move("north");
        nonStone.move("north");
        nonStone.move("west");
        nonStone.move("south");

        BoardStone stone11 = (BoardStone) nonStone.getNorthNeighbour();
        BoardStone stone3 = (BoardStone) stone11.getNorthNeighbour();
        BoardStone stone6 = (BoardStone) stone11.getWestNeighbour();
        BoardStone stone7 = (BoardStone) stone11.getEastNeighbour();

        assertEquals(3, stone3.getValue());
        assertEquals(6, stone6.getValue());
        assertEquals(7, stone7.getValue());
        TestNeighbours(nonStone);
        TestNeighbours(stone11);

    }

    @Test
    public void TestMoveEastOwnNeighbours()throws InvalidMoveException{
        BoardHole nonStone = new BoardHole();
        nonStone.move("west");
        nonStone.move("west");
        nonStone.move("north");
        nonStone.move("east");

        BoardStone stone7 = (BoardStone) nonStone.getNorthNeighbour();
        BoardStone stone12 = (BoardStone) nonStone.getEastNeighbour();
        BoardStone stone14 = (BoardStone) nonStone.getSouthNeighbour();
        BoardStone stone11 = (BoardStone) nonStone.getWestNeighbour();

        assertEquals(7, stone7.getValue());
        assertEquals(12, stone12.getValue());
        assertEquals(14, stone14.getValue());
        assertEquals(11, stone11.getValue());
    }

    @Test
    public void TestMoveEastOtherNeighbours()throws InvalidMoveException{
        BoardHole nonStone = new BoardHole();
        nonStone.move("west");
        nonStone.move("west");
        nonStone.move("north");
        nonStone.move("east");

        BoardStone stone11 = (BoardStone) nonStone.getWestNeighbour();
        BoardStone stone6 = (BoardStone) stone11.getNorthNeighbour();
        BoardStone stone10 = (BoardStone) stone11.getSouthNeighbour();
        BoardStone stone9 = (BoardStone) stone11.getWestNeighbour();
        
        assertEquals(6, stone6.getValue());
        assertEquals(9, stone9.getValue());
        assertEquals(10, stone10.getValue());
        TestNeighbours(nonStone);
        TestNeighbours(stone11);
    }

    @Test
    public void TestGenerateRandomPuzzle(){
        BoardHole nonStone = new BoardHole();
        nonStone.generateRandomPuzzle(25);
    }

    @Test
    public void TestIsSolvedTrue(){
        BoardHole nonStone = new BoardHole();
        assert(nonStone.isSolved());
    }

    @Test
    public void TestIsSolvedFalse()throws InvalidMoveException{
        BoardHole board = new BoardHole();
        board.move("north");
        board.move("west");
        board.move("west");
        board.move("north");
        board.move("east");
        board.move("south");
        board.move("east");
        board.move("south");
        assert(!board.isSolved());
    }

    @Test
    public void TestHeuristic1(){
        BoardHole nonStone = new BoardHole();
        int heuristic = nonStone.calculateHeuristic();
        assertEquals(0, heuristic);
    }

    @Test
    public void TestHeuristic2()throws InvalidMoveException{
        BoardHole nonStone = new BoardHole();
        nonStone.move("north");
        nonStone.move("north");
        nonStone.move("west");
        nonStone.move("north");
        nonStone.move("west");
        nonStone.move("south");
        int heuristic = nonStone.calculateHeuristic();
        assertEquals(6, heuristic);
    }

    private void TestNeighbours(BoardPart stone){
        if(stone.getNorthNeighbour() != null)
            assertEquals(stone, stone.getNorthNeighbour().getSouthNeighbour(), "north failed");
        if(stone.getWestNeighbour() != null)
            assertEquals(stone, stone.getWestNeighbour().getEastNeighbour(), "west failed");
        if(stone.getEastNeighbour() != null)
            assertEquals(stone, stone.getEastNeighbour().getWestNeighbour(), "east failed");
        if(stone.getSouthNeighbour() != null)
            assertEquals(stone, stone.getSouthNeighbour().getNorthNeighbour(), "south failed");
    }
}