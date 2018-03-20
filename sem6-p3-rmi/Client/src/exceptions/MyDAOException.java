package exceptions;

import java.io.Serializable;

/**
 * <p>Класс-исключение для работы с DAO, которое выбрасывается в определенных случаях на сервере.
 * Наследуется от {@link Exception}
 * и реализует интерфейс {@link Serializable}.
 *
 * @author Polischuk Alexander
 * @version 1.0
 */
public class MyDAOException extends Exception implements Serializable {

    public MyDAOException(String s) {
        super(s);
    }

}
