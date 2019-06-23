package mqtt.publisher;

import java.util.List;

public class CliParameters {
	 /** The one and only instance of CLI parameters. */
    private static CliParameters instance;
    /** The address of the broker. */
    private String brokerAddress = "m24.cloudmqtt.com";
    /** The port of the broker. */
    private String brokerPort = "10162";
    /** The port of the protocol. */
    private String brokerProtocol = "tcp";
    /** The topic the MQTT client subscribes to. */
    private String topic = "mqtttopic1";
    /** The message that is published. */
    private String message = "";
    
    //////
    private String temparatur = "0";
    private String windGeschwindigkeit = "0";
    private String regen = "0";
    private String luftfeuchtigkeit = "0";
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
    
    //
    // Getter and Setter
    //
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTemparatur() {
		return temparatur;
	}
	public void setTemparatur(String temparatur) {
		this.temparatur = temparatur;
	}
	public String getWindGeschwindigkeit() {
		return windGeschwindigkeit;
	}
	public void setWindGeschwindigkeit(String windGeschwindigkeit) {
		this.windGeschwindigkeit = windGeschwindigkeit;
	}
	public String getRegen() {
		return regen;
	}
	public void setRegen(String regen) {
		this.regen = regen;
	}
	public String getLuftfeuchtigkeit() {
		return luftfeuchtigkeit;
	}
	public void setLuftfeuchtigkeit(String luftfeuchtigkeit) {
		this.luftfeuchtigkeit = luftfeuchtigkeit;
	}
	public static void setInstance(CliParameters instance) {
		CliParameters.instance = instance;
	}
	public void setMessage(List<String> args) {
        StringBuilder sb = new StringBuilder();
        for (String arg: args) {
            sb.append(arg);
            sb.append(" ");
        }
        this.message = sb.toString().trim();
    }
	/**
     * A private constructor to avoid
     * instantiation.
     */
    private CliParameters() {}
}
