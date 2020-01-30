package nl.sogyo.fifteen;

import java.util.*;
import org.apache.commons.lang3.*;
class FifteenSolver{
    private int heuristic;
    private int move;
    private FifteenSolver parent;
    private ArrayList<FifteenSolver> children;
    private Stone board;
    private Stone emptyStone;

    public static ArrayList<Integer> solveFifteenPuzzle(Stone board){
        FifteenSolver origin = new FifteenSolver(board);
        boolean foundSolution = false;
        if(origin.heuristic == 0){
            foundSolution = true;
        }

        while(!foundSolution){
            foundSolution = origin.solveStep();
        }
        ArrayList<Integer> result = origin.getSolution();
        result.remove(0);
        return result;
    }

    private ArrayList<Integer> getSolution(){
        if(this.heuristic == -1){
            ArrayList<Integer> solution = new ArrayList<>();
            solution.add(this.move);
            return solution;
        }
        else{
            for(FifteenSolver s : this.children){
                if(s.heuristic < 1){
                    ArrayList<Integer> solution = s.getSolution();
                    solution.add(0, this.move);
                    return solution;
                }
            }
            throw new RuntimeException("Heuristic updating probably went wrong." + this.children.size() + " " + this.heuristic);
        }
    }

    private FifteenSolver(Stone board){
        this.board = board;
        this.heuristic = board.calculateHeuristic();
        this.emptyStone = board.getEmptyStone();
        this.move = emptyStone.getCoordAsInt();
    }

    private FifteenSolver(Stone board, Stone emptyStone, int heuristic, FifteenSolver parent, int move){
        this.board = board;
        this.emptyStone = emptyStone;
        this.heuristic = heuristic;
        this.parent = parent;
        this.move = move;
    }

    private boolean solveStep(){
        if(this.parent != null){
            this.board.doMove(move);
        }
        if(this.heuristic == 0){
            if(this.parent != null){
                this.board.doMove(this.parent.move);
            }
            this.heuristic = -1;
            return true;
        }
        if(children == null){
            children = new ArrayList<>();
            if(emptyStone.getEast() != null){
                int nextMove = emptyStone.getEast().getCoordAsInt();
                int heuristicBefore = emptyStone.getEast().calculateIndividualHeuristic();
                board.doMove(nextMove);
                int heuristicAfter = emptyStone.getWest().calculateIndividualHeuristic();
                board.doMove(nextMove - 1);
                int nextHeuristic = this.heuristic - 1;
                if(heuristicBefore - heuristicAfter != 1){
                    nextHeuristic += 2;
                }
                children.add(new FifteenSolver(board, emptyStone, nextHeuristic, this, nextMove));
            }
            if(emptyStone.getSouth() != null){
                int nextMove = emptyStone.getSouth().getCoordAsInt();
                int heuristicBefore = emptyStone.getSouth().calculateIndividualHeuristic();
                board.doMove(nextMove);
                int heuristicAfter = emptyStone.getNorth().calculateIndividualHeuristic();
                board.doMove(nextMove - 4);
                int nextHeuristic = this.heuristic - 1;
                if(heuristicBefore - heuristicAfter != 1){
                    nextHeuristic += 2;
                }
                children.add(new FifteenSolver(board, emptyStone, nextHeuristic, this, nextMove));
            }
            if(emptyStone.getWest() != null){
                int nextMove = emptyStone.getWest().getCoordAsInt();
                int heuristicBefore = emptyStone.getWest().calculateIndividualHeuristic();
                board.doMove(nextMove);
                int heuristicAfter = emptyStone.getEast().calculateIndividualHeuristic();
                board.doMove(nextMove + 1);
                int nextHeuristic = this.heuristic - 1;
                if(heuristicBefore - heuristicAfter != 1){
                    nextHeuristic += 2;
                }
                children.add(new FifteenSolver(board, emptyStone, nextHeuristic, this, nextMove));
            }
            if(emptyStone.getNorth() != null){
                int nextMove = emptyStone.getNorth().getCoordAsInt();
                int heuristicBefore = emptyStone.getNorth().calculateIndividualHeuristic();
                board.doMove(nextMove);
                int heuristicAfter = emptyStone.getSouth().calculateIndividualHeuristic();
                board.doMove(nextMove + 4);
                int NextHeuristic = this.heuristic - 1;
                if(heuristicBefore - heuristicAfter != 1){
                    NextHeuristic += 2;
                }
                children.add(new FifteenSolver(board, emptyStone, NextHeuristic, this, nextMove));
            }
            this.updateHeuristic();
            Collections.shuffle(this.children);
            if(this.parent != null){
                this.board.doMove(parent.move);
            }
            return false;
        }
        else{

            boolean foundSolution = false;
            for(FifteenSolver s : this.children){
                if(s.heuristic == this.heuristic - 1){
                    foundSolution |= s.solveStep();
                }
            }
            this.updateHeuristic();
            if(this.parent != null){
                this.board.doMove(this.parent.move);
            }
            if(foundSolution){
                this.heuristic = 0;
            }
            return foundSolution;
        }
    }

    private void updateHeuristic(){
        int minimumHeuristicOfNextStep = heuristic + 1;
        for(FifteenSolver s : this.children){
            if(s.heuristic < minimumHeuristicOfNextStep){
                minimumHeuristicOfNextStep = s.heuristic;
            }
        }
        this.heuristic = minimumHeuristicOfNextStep + 1;
    }
}