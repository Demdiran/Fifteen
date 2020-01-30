package nl.sogyo.fifteen;

class InvalidMoveException extends Exception{
    InvalidMoveException(String text){
        super(text);
    }
}