package fr.eni.sortircom.bll.exception;
/**
 *
 * @author ehourman2019
 *
 */
@SuppressWarnings("serial")
public class BLLException extends Exception{

    /**
     * Empty Constructor
     *
     */
    public BLLException() {
    }

    /**
     * Constructor with message
     * @param messaqe
     */
    public BLLException(String messaqe) {
        super(messaqe);
    }

    /**
     * Constructor with message and exception
     * @param message
     * @param exception
     */
    public BLLException(String message, Throwable exception) {
        super(message, exception);
    }

    /**
     * Build the message
     * @return string message
     */
    @Override
    public String getMessage() {
        StringBuffer sb = new StringBuffer("Couche BLL - ");
        sb.append(super.getMessage());
        return sb.toString() ;
    }
}
