package mqtt.publisher;

/**
 * A container class that contains all
 * default values used within this application.
 *
 * @author Michael Bredel
 */
public abstract class Defaults {

    /** The default port of the UDP socket server. */
    public static final int PORT = 6543;
    /** The exit code if the procedure succeeded. */
    public static final int EXIT_CODE_SUCCESS = 0;
    /** The exit code of the procedure failed. */
    public static final int EXIT_CODE_ERROR = 1;

    /** The default message sent to the server. */
    public static final String MESSAGE = "";
    /** The default host to connect to. */
    public static final String DST_HOST = "localhost";
    
    public static final String SENSOR_TYPE = "0";

    /**
     * Private constructor to hide the implicit public one
     * and to void any instantiation.
     */
    private Defaults() {
        throw new IllegalStateException("Utility class");
    }

}
