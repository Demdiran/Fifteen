package nl.sogyo.fifteen;

public class OutOfBoardException extends Exception{
    public OutOfBoardException(String message){
        super(message);
    }
}