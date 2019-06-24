package mqtt.subscriber;

public class Defaults {
	/** The default port of the UDP socket server. */
    public static final int PORT = 6543;
    /** The default UDP buffer size. */
    public static final int BUFFER_SIZE = 256;
    /** The exit code if the procedure succeeded. */
    public static final int EXIT_CODE_SUCCESS = 0;
    /** The exit code of the procedure failed. */
    public static final int EXIT_CODE_ERROR = 1;

    /**
     * Private constructor to hide the implicit public one
     * and to void any instantiation.
     */
    private Defaults() {
        throw new IllegalStateException("Utility class");
    }
}
