package mqtt.subscriber;

public class CliParameters {
	 /** The one and only instance of CLI parameters. */
    private static CliParameters instance;
    /**
     * The static getter for the CLI parameters instance.
     *
     * @return The CLI parameters instance.
     */
    public static CliParameters getInstance() {
        if (instance == null)
            instance = new CliParameters();
        return instance;
    }
    /** The address of the broker. */
    private String brokerAddress = "m24.cloudmqtt.com";
    /** The port of the broker. */
    private String brokerPort = "10162";
    /** The port of the protocol. */
    private String brokerProtocol = "tcp";
    /**
     * Get set methode
     * @return
     */
    public String getBrokerAddress() {
		return brokerAddress;
	}
	public void setBrokerAddress(String brokerAddress) {
		this.brokerAddress = brokerAddress;
	}
	public String getBrokerPort() {
		return brokerPort;
	}
	public void setBrokerPort(String brokerPort) {
		this.brokerPort = brokerPort;
	}
	public String getBrokerProtocol() {
		return brokerProtocol;
	}
	public void setBrokerProtocol(String brokerProtocol) {
		this.brokerProtocol = brokerProtocol;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public static void setInstance(CliParameters instance) {
		CliParameters.instance = instance;
	}
	/** The topic the MQTT client subscribes to. */
    private String topic = "mqtttopic1";
   
    /**
     * A private constructor to avoid
     * instantiation.
     */
    private CliParameters() {}

}
