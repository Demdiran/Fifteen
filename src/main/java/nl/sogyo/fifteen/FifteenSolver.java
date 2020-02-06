package nl.sogyo.fifteen;

import java.util.*;
class FifteenSolver{
    private int heuristic;
    private String move;
    private FifteenSolver parent;
    private ArrayList<FifteenSolver> children;
    private NonStone board;

    public static ArrayList<String> solveFifteenPuzzle(NonStone board){
        FifteenSolver origin = new FifteenSolver(board);
        boolean foundSolution = false;
        if(origin.heuristic == 0){
            foundSolution = true;
        }

        while(!foundSolution){
            try {
                foundSolution = origin.solveStep();                
            } catch (InvalidMoveException e) {
                e.printStackTrace();
                foundSolution = true;
            }
        }
        ArrayList<String> result = origin.getSolution();
        result.remove(0);
        return result;
    }

    private ArrayList<String> getSolution(){
        if(this.heuristic == -1){
            ArrayList<String> solution = new ArrayList<>();
            solution.add(this.move);
            return solution;
        }
        else{
            for(FifteenSolver s : this.children){
                if(s.heuristic < 1){
                    ArrayList<String> solution = s.getSolution();
                    solution.add(0, this.move);
                    return solution;
                }
            }
            throw new RuntimeException("Heuristic updating probably went wrong." + this.children.size() + " " + this.heuristic);
        }
    }

    private FifteenSolver(NonStone board){
        this.board = board;
        this.heuristic = board.calculateHeuristic();
        this.move = "";
    }

    private FifteenSolver(NonStone board, int heuristic, FifteenSolver parent, String move){
        //System.out.println("Node with heuristic " + heuristic + ", and move " + move);
        this.board = board;
        this.heuristic = heuristic;
        this.parent = parent;
        this.move = move;
    }

    private boolean solveStep()throws InvalidMoveException{
        if(this.parent != null){
            this.board.move(move);
        }
        if(this.heuristic == 0){
            if(this.parent != null){
                this.moveBack();
            }
            this.heuristic = -1;
            return true;
        }
        if(children == null){
            children = new ArrayList<>();
            String lastmove = "";
            if(this.parent != null){
                lastmove = this.move;
            }
            if(lastmove != "south" && board.getNorth() != null){
                board.move("north");
                children.add(new FifteenSolver(board, board.calculateHeuristic(), this, "north"));
                board.move("south");
            }
            if(lastmove != "west" && board.getEast() != null){
                board.move("east");
                children.add(new FifteenSolver(board, board.calculateHeuristic(), this, "east"));
                board.move("west");                
            }
            if(lastmove != "north" && board.getSouth() != null){
                board.move("south");
                children.add(new FifteenSolver(board, board.calculateHeuristic(), this, "south"));
                board.move("north");                
            }
            if(lastmove != "east" && board.getWest() != null){
                board.move("west");
                children.add(new FifteenSolver(board, board.calculateHeuristic(), this, "west"));
                board.move("east");                
            }
            this.updateHeuristic();
            Collections.shuffle(this.children);
            if(this.parent != null){
                this.moveBack();
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
                this.moveBack();
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

    private void moveBack()throws InvalidMoveException{
        if(this.move == "north")
            this.board.move("south");
        if(this.move == "east")
            this.board.move("west");
        if(this.move == "south")
            this.board.move("north");
        if(this.move == "west")
            this.board.move("east");
    }
}