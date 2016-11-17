package app.sensors;

import app.events.Event;
import app.events.TouchSensorEvent;
import app.interfaces.EventListener;
import app.interfaces.TouchSensorListener;
import app.tests.ITouchSensor;

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
		// Read in the information from the sensor
		int value = sensorWrapper.isPressed() ? 1 : 0;
		// Create an event to send to the listeners
		createEvent(value);
	}

	/**
	 * Creates an event to send to the event listeners.
	 * @param sensorValue - The value that was read in from the sensor
	 */
	@Override
	protected void createEvent(float sensorValue){
		// Create a TouchSensorEvent with the data
		TouchSensorEvent touchEvent = new TouchSensorEvent((int)sensorValue);
		// Send the event to the listeners
		notifyListeners(touchEvent);
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
			if(l instanceof TouchSensorListener) {
				((TouchSensorListener) l).onTouchEvent((TouchSensorEvent)e);
			}
		}
	}

}
