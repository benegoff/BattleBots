public class TouchDetector extends AbstractSensor implements Runnable {

	private ITouchSensor sensor;
	
	/**
	 * Constructs a new TouchSensor with the given information.
	 * @param s - The {@link ITouchSensor} to use for the sensor
	 */
	public TouchDetector(ITouchSensor s) {
		// Call the super constructor
		super();
		// Set the value of the sensor
		sensor = s;
	}
	
	/**
	 * Overrides the {@link AbstractSensor#registerListener(EventListener)} method.
	 * @param e - The listener to register
	 */
	@Override
	public void registerListener(EventListener listener) {
		// If the listener is an instance of TouchListener
		if(listener instanceof TouchListener) 
			// Add the listener to the list of listeners
			super.registerListener(listener);
	}
	
	/**
	 * Implementation of the {@link AbstractSensor#detectChange()} method.
	 */
	@Override
	public void detectChange() {
		// Create a new TouchEvent 
		TouchEvent event = new TouchEvent(sensor.getIsPressed());
		// Send the event to the registered listeners
		notifyListeners(event);
	}
	
	/**
	 * Implementation of the {@link AbstractSensor#notifyListeners(Event)} method.
	 * @param e - The event that was raised
	 */
	@Override
	protected void notifyListeners(Event e) {
		// For each listener in the list of registered listeners...
		for(EventListener l : listeners) {
			// Call the listening method
			((TouchListener) l).onTouchEvent((TouchEvent) e);
		}
	}
}
