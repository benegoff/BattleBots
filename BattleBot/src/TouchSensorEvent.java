public class TouchSensorEvent extends Event{

	private int touchData;
	
	/**
	 * Creates a new TouchSensorEvent with the given data.
	 * @param value - The value that was read from the touch sensor
	 */
	public TouchSensorEvent(int value) {
		// Set the value of rawTouchData
		touchData = value;
	}
	
	/**
	 * Access method for the touch data of the event.
	 * @return The value of rawTouchData
	 */
	public int getRawTouchData() {
		return touchData;
	}
}
