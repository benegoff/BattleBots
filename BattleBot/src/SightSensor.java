/**
 * Lowest level code above the hardware of the sight sensor.
 */
public class SightSensor extends AbstractSensor {
	
	private ISightSensor sensorWrapper;
	
	/**
	 * Constructs a new SightSensor with the given ultra sonic sensor
	 * @param sensor - The sensor to use
	 */
	public SightSensor(ISightSensor sensor) {
		// Call the super constructor
		super();
		// Set the value of the sensor wrapper
		sensorWrapper = sensor;
	}

	/**
	 * Reads information from the sensor and uses that information to
	 * create an event to send to the listeners.
	 * The range of the sight sensor is 0 to 255
	 */
	@Override
	protected void detectChange(){
		final int SENSOR_MIN = -1;
		final int SENSOR_MAX = 255;
		// Read the information from the sensor
		int value = sensorWrapper.getDistance();
		// If the value read from the sensor is within the sensors valid range,
		if(value > SENSOR_MIN && value < SENSOR_MAX) {
			// Send the event
			notifyListeners(new SightSensorEvent(value));
		}
	}
	
	/**
	 * Notifies the listeners that an event has occurred.
	 * @param e - The event that was raised
	 */
	@Override
	protected void notifyListeners(Event e) {
		// For each listener in the list of registered listeners...
		for(EventListener l : listeners) {
			// If the listener is a SightSensorListener
			if(l instanceof SightSensorListener) {
				// Call the listening method
				((SightSensorListener)l).onSightEvent((SightSensorEvent)e);
			}
		}
	}
}