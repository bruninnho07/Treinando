package treino.Exceptions;

import org.springframework.http.HttpStatus;

public class AgeException extends RuntimeException{
    public AgeException(String msg, HttpStatus status){
        super(msg);
    }

    public AgeException(String msg){
        super(msg);
    }
}
