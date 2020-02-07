package nl.sogyo.fifteen;

import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.*;
import java.util.ArrayList;

public class FifteenSolverTest{
    @Test
    public void TestSolver()throws InvalidMoveException{
        BoardHole boardAccess = new BoardHole();
        boardAccess.generateRandomPuzzle(50);
        ArrayList<String> solution = FifteenSolver.solveFifteenPuzzle(boardAccess);
        boolean isSolver = boardAccess.testSolution(solution);
        assert(isSolver);
    }
}