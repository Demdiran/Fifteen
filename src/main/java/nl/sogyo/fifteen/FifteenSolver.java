package nl.sogyo.fifteen;

import java.util.*;
class FifteenSolver{
    private int heuristic;
    private String move;
    private FifteenSolver parent;
    private ArrayList<FifteenSolver> children;
    private BoardHole board;

    public static ArrayList<String> solveFifteenPuzzle(BoardHole board){
        FifteenSolver rootNode = new FifteenSolver(board);
        boolean foundSolution = false;
        if(rootNode.heuristic == 0){
            foundSolution = true;
        }

        while(!foundSolution){
            try {
                foundSolution = rootNode.solveStep();                
            } catch (InvalidMoveException e) {
                e.printStackTrace();
                foundSolution = true;
            }
        }
        ArrayList<String> result = rootNode.getSolution();
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

    private FifteenSolver(BoardHole board){
        this.board = board;
        this.heuristic = board.calculateHeuristic();
        this.move = "";
    }

    private FifteenSolver(BoardHole board, int heuristic, FifteenSolver parent, String move){
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
            if(lastmove != "south" && board.getNorthNeighbour() != null){
                BoardStone tempStone = (BoardStone) board.getNorthNeighbour();
                int heuristicBefore = tempStone.calculateOwnHeuristic();
                board.move("north");
                int heuristicAfter = tempStone.calculateOwnHeuristic();
                board.move("south");
                int difference = heuristicAfter - heuristicBefore;
                FifteenSolver child = new FifteenSolver(board, this.heuristic + difference, this, "north");
                children.add(child);
                if(difference == -1){
                    boolean foundSolution = child.solveStep();
                    if(foundSolution){
                        if(this.parent != null){
                            this.moveBack();
                        }
                        this.heuristic = 0;
                        return true;
                    }
                }
            }
            if(lastmove != "west" && board.getEastNeighbour() != null){
                BoardStone tempStone = (BoardStone) board.getEastNeighbour();
                int heuristicBefore = tempStone.calculateOwnHeuristic();
                board.move("east");
                int heuristicAfter = tempStone.calculateOwnHeuristic();
                board.move("west");
                int difference = heuristicAfter - heuristicBefore;
                FifteenSolver child = new FifteenSolver(board, this.heuristic + difference, this, "east");
                children.add(child);
                if(difference == -1){
                    boolean foundSolution = child.solveStep();
                    if(foundSolution){
                        if(this.parent != null){
                            this.moveBack();
                        }
                        this.heuristic = 0;
                        return true;
                    }
                }
            }
            if(lastmove != "north" && board.getSouthNeighbour() != null){
                BoardStone tempStone = (BoardStone) board.getSouthNeighbour();
                int heuristicBefore = tempStone.calculateOwnHeuristic();
                board.move("south");
                int heuristicAfter = tempStone.calculateOwnHeuristic();
                board.move("north");
                int difference = heuristicAfter - heuristicBefore;
                FifteenSolver child = new FifteenSolver(board, this.heuristic + difference, this, "south");
                children.add(child);
                if(difference == -1){
                    boolean foundSolution = child.solveStep();
                    if(foundSolution){
                        if(this.parent != null){
                            this.moveBack();
                        }
                        this.heuristic = 0;
                        return true;
                    }
                }           
            }
            if(lastmove != "east" && board.getWestNeighbour() != null){
                BoardStone tempStone = (BoardStone) board.getWestNeighbour();
                int heuristicBefore = tempStone.calculateOwnHeuristic();
                board.move("west");
                int heuristicAfter = tempStone.calculateOwnHeuristic();
                board.move("east");
                int difference = heuristicAfter - heuristicBefore;
                FifteenSolver child = new FifteenSolver(board, this.heuristic + difference, this, "west");
                children.add(child);
                if(difference == -1){
                    boolean foundSolution = child.solveStep();
                    if(foundSolution){
                        if(this.parent != null){
                            this.moveBack();
                        }
                        this.heuristic = 0;
                        return true;
                    }
                } 
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