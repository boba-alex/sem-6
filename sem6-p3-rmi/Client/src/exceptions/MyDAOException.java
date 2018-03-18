package exceptions;

import java.io.Serializable;

/**
 * Created by User on 13.02.2018.
 */
public class MyDAOException extends Exception implements Serializable{

    public MyDAOException(String s) {
        super(s);
    }

}
