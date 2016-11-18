import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class UltrasonicWrapper implements ISightSensor {

	private UltrasonicSensor sensor;
	
	/**
	 * Constructs a new UltrasonicWrapper
	 * @param p - The SensorPort into which the Ultrasonic sensor is plugged
	 */
	public UltrasonicWrapper(SensorPort p) {
		sensor = new UltrasonicSensor(p);
		// Activate the sensor
		p.activate();
		// Set the sensor mode
		sensor.continuous();
	}
	
	/**
	 * Returns the distance read from the sensor.
	 * @return The distance read from the sensor
	 */
	public int getDistance() {
		return sensor.getDistance();
	}
}
