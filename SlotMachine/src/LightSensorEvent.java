
public class LightSensorEvent extends Event {

	private final int lightValue;
	
	/**
	 * Constructs a new LightSensorEvent.
	 * @param v - The light value read in from the sensor
	 */
	public LightSensorEvent(int v) {
		// Set the value of isCoinPresent
		lightValue = v;
	}
	
	/**
	 * Returns the light value read in from the sensor.
	 * @return The value of the light reading
	 */
	public int getLightValue() {
		return lightValue;
	}
}
