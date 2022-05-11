package treino.Exceptions;

import org.springframework.http.HttpStatus;

public class AlreadyExistsInfos extends RuntimeException{
    public AlreadyExistsInfos(String msg){
        super(msg);
    }

    public AlreadyExistsInfos(String msg, HttpStatus status){
        super(msg);
    }
}
