import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for sensors.
 */
public abstract class AbstractSensor implements Runnable {

	private Thread sensorThread; 
	protected List<EventListener> listeners;
	
	/**
	 * Creates a new AbstractSensors.
	 */
	public AbstractSensor() {
		// Create the array of event listeners
		listeners = new ArrayList<EventListener>();
		// Create the sensor thread
		sensorThread = new Thread(this);
		// Mark the thread as a daemon thread
		sensorThread.setDaemon(true);
		// Start the thread
		sensorThread.start();
	}
	
	
	/**
	 * Adds the given listener to the list of registered listeners.
	 * @param listener - The listener to register
	 */
	public void registerListener(EventListener listener) {
		// Add the listener to the list of listeners
		listeners.add(listener);
	}
	
	/**
	 * Unregisters the given listener from the list of listeners.
	 * @param listener - The listener to unregister
	 */
	public void unregisterListener(EventListener listener) {
		// Remove the listener from the list of listeners
		listeners.remove(listener);
	}
	
	/**
	 * Implementation of the {@link Runnable#run()} method.
	 */
	public void run() {
		// While the system is running...
		while(true) {
			// Detect the change in the sensor
			detectChange();
			// Yield the thread
			Thread.yield();
		}
	}
	
	/**
	 * Detects a change and notifies the listeners of the change.
	 */
	public abstract void detectChange();
	
	/**
	 * Notifies the listeners that an event has occurred.
	 * @param e - The event that was raised
	 */
	protected abstract void notifyListeners(Event e);
}
