package exceptions;

import java.io.Serializable;

/**
 * <p>Класс-исключение для работы с DAO, которое выбрасывается в определенных случаях на сервере.
 * Наследуется от <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/Exception.html">{@link java.lang.Exception}</a>
 * и реализует интерфейс <a href="https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">{@link Serializable}</a>.
 *
 * @author Polischuk Alexander
 * @version 1.0
 */
public class MyDAOException extends Exception implements Serializable{

    public MyDAOException(String s) {
        super(s);
    }

}
