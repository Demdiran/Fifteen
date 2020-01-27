package nl.sogyo.fifteen;

public class OutOfBoardException extends RuntimeException{
    public OutOfBoardException(String message){
        super(message);
    }
}