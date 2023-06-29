package two.exceptoins;

public class TooMuchSaleException extends RuntimeException{
    public TooMuchSaleException(String message) {
        super(message);
    }
}
