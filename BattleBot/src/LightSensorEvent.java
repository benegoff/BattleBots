public class LightSensorEvent extends Event {

	private int lightValue; 
	
	/**
	 * Constructs a new LightSensorEvent with the given data.
	 * @param value - The value of the light reading
	 */
	public LightSensorEvent(int value) {
		// Set the value of the lightValue
		lightValue = value;
	}
	
	/**
	 * Access method for the event data
	 * @return The value of the light reading
	 */
	public int getLightValue() {
		// Return the value of lightValue
		return lightValue;
	}
}
