
public class CoinDetector extends AbstractSensor {
	private ILightSensor sensor;
	
	/**
	 * Constructs a new CoinDetector with the given information.
	 * @param s - The sensor from which to read values
	 */
	public CoinDetector(ILightSensor s) {
		// Set the value of the hardware sensor
		sensor = s;
	}
	
	/**
	 * Overrides the {@link AbstractSensor#registerListener(EventListener)} method.
	 * @param listener - The listener to register
	 */
	@Override 
	public void registerListener(EventListener listener) {
		// If the given listener is an instance of CoinListener
		if(listener instanceof LightSensorListener) 
			// Add the listener to the list of listeners
			super.registerListener(listener);
	}
	/**
	 * Implementation of the {@link AbstractSensor#detectChange()} method.
	 */
	@Override
	public void detectChange() {
		// Create a new LightSensorEvent with the information from the sensor
		LightSensorEvent e = new LightSensorEvent(sensor.getLightValue());
		// Send the event to the listeners
		notifyListeners(e);
	}

	/**
	 * Implementation of the {@link AbstractSensor#notifyListeners(Event)} method.
	 * @param e - The event that was raised
	 */
	@Override
	protected void notifyListeners(Event e) {
		// For each listener in the list of registered listeners
		for(EventListener l : listeners) {
			// Call the listening method
			((LightSensorListener) l).onLightEvent((LightSensorEvent) e);
		}
	}

	
}
