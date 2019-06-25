package fr.eni.sortircom.dal.exception;

/**
 *
 * @author ehourman2019
 *
 */
@SuppressWarnings("serial")
public class DALException extends java.lang.Exception {
    /**
     * Empty constructor
     */
    public DALException() {
    }

    /**
     * Constructor with message
     *
     * @param message
     */
    public DALException(String message) {
        super(message);
    }

    /**
     * Constructor with message and throwable exception
     *
     * @param message
     * @param exception
     */
    public DALException(String message, Throwable exception) {
        super(message, exception);
    }


    /**
     * Get message exception to string
     */
    @Override
    public String getMessage() {
        StringBuffer sb = new StringBuffer("Couche DAL - ");
        sb.append(super.getMessage());

        return sb.toString();
    }
}