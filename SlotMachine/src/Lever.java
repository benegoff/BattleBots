import java.util.ArrayList;
import java.util.List;

import lejos.nxt.SensorPort;

public class Lever implements TouchListener{
	
	private TouchDetector leverButton;
	
	private boolean isLeverPulled;
	private List<LeverListener> listeners;
	
	/**
	 * Constructs a new Level object.
	 * @param p - The SensorPort to which the lever button is connected
	 */
	public Lever(SensorPort p) {
		// Set the value of isLeverPulled
		isLeverPulled = false;
		// Construct the list of listeners
		listeners = new ArrayList<LeverListener>();
		// Create a TouchDetector for the lever button
		leverButton = new TouchDetector(new TouchSensorWrapper(p));
		// Register with the lever button
		leverButton.registerListener(this);
	}
	
	/**
	 * Registers the given listener.
	 * @param listener - The listener to register
	 */
	public void registerListener(LeverListener listener) {
		// Add the listener to the list of listeners
		listeners.add(listener);
	}
	
	/**
	 * Unregisters the given listener.
	 * @param listener - The listener to unregister
	 */
	public void unregisterListener(LeverListener listener) {
		// Remove the listener from the list of listeners
		listeners.remove(listener);
	}
	
	/**
	 * Listens for touch events from the lever button.
	 * @param e - The event that was raised
	 */
	@Override
	public void onTouchEvent(TouchEvent e) {
		// If the event is different from the stored state,
		if(e.getIsPressed() != isLeverPulled) {
			// Update the value of isLeverPulled
			isLeverPulled = e.getIsPressed();
			// Send the event to the listeners
			notifyListeners(isLeverPulled);
		}
	}
	
	/**
	 * Notifies the listeners that a change has occurred in the lever.
	 * @param state - If true, then the lever is pulled, otherwise false.
	 */
	private void notifyListeners(boolean state) {
		// For each listener in the list of register listeners...
		for(LeverListener l : listeners) {
			// Call the listening method
			l.onLeverEvent(state);
		}
	}
}
