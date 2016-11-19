import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class LightSensorWrapper implements ILightSensor {

	private LightSensor hardware;
	
	/**
	 * Constructs a new LightSensorWrapper.
	 * @param p - The port into which the light sensor is connected
	 */
	public LightSensorWrapper(SensorPort p) {
		// Create the light sensor 
		hardware = new LightSensor(p);
	}
	
	/**
	 * Returns the light value read in from the hardware sensor.
	 */
	public int getLightValue() {
		return hardware.getLightValue();
	}
}
