package back.com.example.demo.excepation;

public class UploadFileException extends RuntimeException{
    public UploadFileException(String message){
        super(message);
    }

    public UploadFileException(String message, Throwable cause){
        super(message, cause);
    }
}
