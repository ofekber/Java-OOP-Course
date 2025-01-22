public class RoomDoesNotExist extends RuntimeException{
    public RoomDoesNotExist(){}
    public RoomDoesNotExist(String message){
        super(message);
    }
    public RoomDoesNotExist(String message , Throwable cause){
        super(message, cause);
    }
}
