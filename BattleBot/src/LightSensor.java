/**
 * Lowest level code above the hardware of the light sensor.
 */
public class LightSensor extends AbstractSensor {
	
	private ILightSensor sensorWrapper;
	/**
	 * Constructs a new LightSensor object.
	 */
	public LightSensor(ILightSensor sensor) {
		super();
		sensorWrapper = sensor;
	}
	
	/**
	 * Implementation of detectChange from AbstractSensor
	 * Light sensor values range from 0 to 1023.
	 */
	@Override
	protected void detectChange(){
		final int SENSOR_MIN = -1;
		final int SENSOR_MAX = 100;
		// Read information from the sensor.
		int value = sensorWrapper.getLightReading();
		// If the value is valid within the sensor's range
		if(value > SENSOR_MIN && value < SENSOR_MAX) {
			// Send the event
			notifyListeners(new LightSensorEvent(value));
		}
	}

	/**
	 * Notifies all listeners that an event has occurred.
	 * @param e - The Event that was raised
	 */
	@Override
	protected void notifyListeners(Event e) {
		// For each listener in the list of registered listeners...
		for(EventListener l : listeners) {
			// I included this check just for the sake of safety
			// If the listener is a LightSesnorListener,
			if(l instanceof LightSensorListener) {
				// Call the listening method
				((LightSensorListener) l).onLightSensorEvent((LightSensorEvent)e);
			}
		}
	}

}