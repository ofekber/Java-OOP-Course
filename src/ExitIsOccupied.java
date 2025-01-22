public class ExitIsOccupied extends RuntimeException {
    public ExitIsOccupied(){}
    public ExitIsOccupied(String message){
        super(message);
    }
    public ExitIsOccupied(String message, Throwable cause){
        super(message, cause);
    }
}
