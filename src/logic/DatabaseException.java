package logic;

import java.io.PrintWriter;
import java.io.StringWriter;

public class DatabaseException extends RuntimeException {

    public DatabaseException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "Ошибка выполнения запроса!";
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        getCause().printStackTrace(new PrintWriter(sw));
        return sw.getBuffer().toString();
    }

}
