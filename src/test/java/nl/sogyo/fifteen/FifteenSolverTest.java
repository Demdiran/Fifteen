package nl.sogyo.fifteen;

import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.*;
import java.util.ArrayList;

public class FifteenSolverTest{
    @Test
    public void TestSolver()throws InvalidMoveException{
        NonStone nonStone = new NonStone();
        nonStone.generatePuzzle(50);
        ArrayList<String> solution = FifteenSolver.solveFifteenPuzzle(nonStone);
        boolean isSolver = nonStone.trySolution(solution);
        assert(isSolver);
    }
}