

/**
 * Lowest level code above the hardware of the touch sensor.
 */
public class TouchSensor extends AbstractSensor {
	
	private ITouchSensor sensorWrapper;

	/**
	 * Constructs a new TouchSensor
	 * @param sensor
	 */
	public TouchSensor(ITouchSensor sensor) {
		super();
		sensorWrapper = sensor;
	}

	/**
	 * Reads from from a sensors and determines if an event should be created.
	 * The boolean nature of the touch sensor cannot be filtered here.
	 */
	@Override
	protected void detectChange(){
		// Read from the sensor
		boolean pressed = sensorWrapper.isPressed();
		// Send the event
		notifyListeners(new TouchEvent(pressed));
	}
	/**
	 * Calls the listening method on the registered listeners.
	 * @param e - The event that was raised
	 */
	@Override
	protected void notifyListeners(Event e) {
		// For each listener in the list of registered listeners...
		for(EventListener l : listeners) {
			// If the listener is a TouchSensorListener
			if(l instanceof TouchListener) {
				((TouchListener) l).onTouch((TouchEvent)e);
			}
		}
	}

}
