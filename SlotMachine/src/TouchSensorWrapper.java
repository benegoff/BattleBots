import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class TouchSensorWrapper implements ITouchSensor {

	public TouchSensor hardware;
	
	/**
	 * Creates a new TouchSensorWrapper with the given information.
	 * @param p - The SensorPort to which the touch sensor is connected
	 */
	public TouchSensorWrapper(SensorPort p) {
		// Set the value of the hardware sensor
		hardware = new TouchSensor(p);
	}
	
	/**
	 * Implementation of {@link ITouchSensor#getIsPressed()} method.
	 */
	@Override
	public boolean getIsPressed() {
		return false;
	}

}
