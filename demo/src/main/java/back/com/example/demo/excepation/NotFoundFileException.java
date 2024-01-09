package back.com.example.demo.excepation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundFileException extends RuntimeException{
    public NotFoundFileException(String message){
        super(message);
    }

    public NotFoundFileException(String message, Throwable cause){
        super(message, cause);
    }
}
