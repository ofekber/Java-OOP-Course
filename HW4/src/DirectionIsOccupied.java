public class DirectionIsOccupied extends RuntimeException{
    public DirectionIsOccupied(){}
    public DirectionIsOccupied(String message){
        super(message);
    }
    public DirectionIsOccupied(String message, Throwable cause){
        super(message, cause);
    }
}
