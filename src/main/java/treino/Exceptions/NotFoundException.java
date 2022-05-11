package treino.Exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String msg, HttpStatus status){
        super(msg);
    }
    public NotFoundException(String msg){
        super(msg);
    }
}
