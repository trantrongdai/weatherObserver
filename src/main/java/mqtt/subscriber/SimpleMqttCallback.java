package mqtt.subscriber;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hda.fbi.vs.weatherstation.WetterStation;

public class SimpleMqttCallback implements MqttCallback{
	 /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMqttCallback.class);

	@Override
	public void connectionLost(Throwable cause) {
		LOGGER.error("Connection to MQTT broker lost!");
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
		// TODO Auto-generated method stub
		  String playloadString =  new String(mqttMessage.getPayload());
		  //System.out.println("[+] Mqtt Subscriber : " + playloadString);
		  //String []value = playloadString.split(" ");
		  /// Code Hier
		  WetterStation.sensorToList(playloadString);
		  
		  LOGGER.info("Message received: "+ playloadString );
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken mqttDeliveryToken) {
		try {
            LOGGER.info("Delivery completed: "+ mqttDeliveryToken.getMessage() );
        } catch (MqttException e) {
            LOGGER.error("Failed to get delivery token message: " + e.getMessage());
        }
	}

}
