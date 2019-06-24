package mqtt.subscriber;


public class Main {
	public static void main(String[] args) {
		// Parse the command line.
        CliProcessor.getInstance().parseCliOptions(args);
        // Start the MQTT subscriber.
        Subscriber subscriber = new Subscriber();
        subscriber.run();
	}

}
