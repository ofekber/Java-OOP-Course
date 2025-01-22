public class NoSuchElement extends RuntimeException {
    public NoSuchElement(){}
    public NoSuchElement(String message){
        super(message);
    }
    public NoSuchElement(String message , Throwable cause){
        super(message, cause);
    }
}
