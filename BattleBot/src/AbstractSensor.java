package app.sensors;

import java.util.ArrayList;
import java.util.List;

import app.events.Event;
import app.interfaces.EventListener;
import app.interfaces.Sensor;
import lejos.nxt.SensorPort;

public abstract class AbstractSensor implements Sensor, Runnable {

	protected List<EventListener> listeners;
	protected Thread sensorThread;
	
	/**
	 * Defines the constructor for an AbstractSensor
	 */
	public AbstractSensor() {
		// Create the array of listeners
		listeners = new ArrayList<EventListener>();
		// Create the sensor thread
		sensorThread = new Thread(this);
		// Mark the thread as a daemon thread
		sensorThread.setDaemon(true);
		// Start hte separate thread
		sensorThread.start();
	}
	
	/**
	 * Implementation of the registerListener method from the
	 * Sensor interface.
	 * @param listener - The listener to register
	 */
	public void registerListener(EventListener listener) {
		// Add the listener to the listeners list
		listeners.add(listener);
	}
	
	/**
	 * Implementation of the unregisterListener method from the
	 * Sensor interface.
	 * @param listener - The listener to unregister
	 */
	public void unregisterListener(EventListener listener) {
		// Remove the given listener from the listeners list
		listeners.remove(listener);
	}

	/**
	 * Reads from the sensor and sends events to the listeners if 
	 * a change occurs.
	 */
	public void run() {
		// While the sensor exists...
		while(true) {
			//	Read from sensor
			detectChange();
			//	Yield to other threads
			Thread.yield();
		}
	}

	/**
	 * Uses the sensor belonging to the inheriting class to determine
	 * if the environment has changed.
	 */
	protected abstract void detectChange();

	/**
	 * Creates the event that will be sent to the event listeners.
	 * @param sensorValue - The value that was read from the sensor
	 */
	protected abstract void createEvent(float sensorValue);
	
	/**
	 * Implementation of the callListeners method from the Sensor interface.
	 * @param e - An Event object holding information for the event
	 */
	protected abstract void notifyListeners(Event e);
}
