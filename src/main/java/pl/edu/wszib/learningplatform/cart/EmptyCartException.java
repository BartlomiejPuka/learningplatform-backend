package pl.edu.wszib.learningplatform.cart;

public class EmptyCartException extends RuntimeException {

    public EmptyCartException(String msg) {
        super(msg);
    }
}
