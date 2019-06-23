package mqtt.publisher;

public class Constants {
	 /** The exit code if the procedure succeeded. */
    public static final int EXIT_CODE_SUCCESS = 0;
    /** The exit code of the procedure failed. */
    public static final int EXIT_CODE_ERROR = 1;
    /** The at-most-once QoS parameter of MQTT: */
    public static final int QOS_AT_MOST_ONCE = 0;
    /** The at-least-once QoS parameter of MQTT: */
    public static final int QOS_AT_LEAST_ONCE = 1;
    /** The exactly-once QoS parameter of MQTT: */
    public static final int QOS_EXACTLY_ONCE = 2;
}
