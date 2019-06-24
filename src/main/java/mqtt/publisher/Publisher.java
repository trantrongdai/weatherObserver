package mqtt.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
/**
 * The MQTT publisher that connects to a
 * broker and publishes messages on a
 * specific topic.
 *
 * @author Manh Ton
 */
public class Publisher {
	 /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);
    /** The global CLI parameters that have been parsed in Main. */
    private CliParameters cliParameters;
    /** The broker URL. */
    private String broker;

    /**
     * Default constructor that initializes
     * various class attributes.
     */
    public Publisher() {
    	//System.out.println("[+] Publisher ... ");
        // Get the CLI parameters.
        cliParameters = CliParameters.getInstance();

        // Create the broker string from command line arguments.
        broker =
                cliParameters.getBrokerProtocol() + "://" +
                cliParameters.getBrokerAddress() + ":" +
                cliParameters.getBrokerPort();
       // System.out.println("[+] Broker : " + broker.toString());

    }
    /**
     * Runs the MQTT client and publishes a message.
     */
    public void run() {

        // Create some MQTT connection options.
        MqttConnectOptions mqttConnectOpts = new MqttConnectOptions();
        mqttConnectOpts.setUserName("eidbstfz");
        mqttConnectOpts.setPassword("ww8KnW7gvZTv".toCharArray());
        mqttConnectOpts.setCleanSession(true);
        //System.out.println("[+] Publisher message: 1 ");
        
        try {
        	//System.out.println("[+] Publisher message: 2 ");
            MqttClient client = new MqttClient(broker, MqttClient.generateClientId());

            // Connect to the MQTT broker using the connection options.
            client.connect(mqttConnectOpts);
            LOGGER.info("Connected to MQTT broker: " + client.getServerURI());

             while(true){
              // Create the message and set a quality-of-service parameter.
                MqttMessage temperature = new MqttMessage((CliParameters.getInstance().getTemparatur() + (int)(Math.random() * 100) + " °C" )
                                .getBytes());
                temperature.setQos(Constants.QOS_EXACTLY_ONCE);
                // Publish the temperature
                client.publish(cliParameters.getTopic(), temperature);
                // 
                MqttMessage luftfeuchtigkeit = new MqttMessage((CliParameters.getInstance().getLuftfeuchtigkeit() + (int)(Math.random() * 100) + " %" )
                        .getBytes());
                luftfeuchtigkeit.setQos(Constants.QOS_EXACTLY_ONCE);
                // Publish the temperature
                client.publish(cliParameters.getTopic(), luftfeuchtigkeit);
                MqttMessage windgeschwindigkeit = new MqttMessage((CliParameters.getInstance().getWindGeschwindigkeit() + (int)(Math.random() * 100) + " km/h" )
                        .getBytes());
                windgeschwindigkeit.setQos(Constants.QOS_EXACTLY_ONCE);
                // Publish the temperature
                client.publish(cliParameters.getTopic(), windgeschwindigkeit);
                MqttMessage regen = new MqttMessage((CliParameters.getInstance().getRegen() + (int)(Math.random() * 100) + " ml/min")
                        .getBytes());
                regen.setQos(Constants.QOS_EXACTLY_ONCE);
                // Publish the temperature
                client.publish(cliParameters.getTopic(), regen);
                LOGGER.info("Published message: " + temperature);
                LOGGER.info("Published message: " + luftfeuchtigkeit);
                LOGGER.info("Published message: " + windgeschwindigkeit);
                LOGGER.info("Published message: " + regen);
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException ex) {
                    java.util.logging.Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  

            // Disconnect from the MQTT broker.
            //client.disconnect();
            //LOGGER.info("Disconnected from MQTT broker.");

            // Exit the app explicitly.
            //System.exit(Constants.EXIT_CODE_SUCCESS);

        } catch (MqttException e) {
            LOGGER.error("An error occurred: " + e.getMessage());
        }

    }
}
