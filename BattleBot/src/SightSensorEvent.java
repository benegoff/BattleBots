public class SightSensorEvent extends Event{

	private int distance;
	
	/**
	 * Constructs a new SightSensorEvent with the given information.
	 * @param value - The value that was read from the sensor
	 */
	public SightSensorEvent(int value) {
		// Set the value of the distance
		distance = value;
	}
	
	/**
	 * Access method for the event data.
	 * @return The value for distance that was read from the sensor
	 */
	public int getDistance() {
		// Return the distance value
		return distance;
	}
}
