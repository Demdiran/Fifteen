package nl.sogyo.fifteen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class FifteenSolverTest{
    @Test
    public void TestSolver(){
        Stone stone1 = new Stone();
        stone1.generateNewPuzzle(25);
        int[] solution = FifteenSolver.solveFifteenPuzzle(stone1);
        boolean isSolver = stone1.trySolution(solution);
        assert(isSolver);
    }
}