package nl.sogyo.fifteen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.*;

public class FifteenSolverTest{
    @Test
    public void TestSolver(){
        Stone stone1 = new Stone();
        stone1.generateNewPuzzle(25);
        int[] solution = ArrayUtils.toPrimitive(FifteenSolver.solveFifteenPuzzle(stone1).toArray(new Integer[0]));
        boolean isSolver = stone1.trySolution(solution);
        assert(isSolver);
    }
}