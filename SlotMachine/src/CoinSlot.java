import java.util.ArrayList;
import java.util.List;

import lejos.nxt.SensorPort;

public class CoinSlot implements LightSensorListener {

	private static final int COIN_THRESHOLD = 65;
	
	private CoinDetector detector;
	private boolean isCoinInSlot;
	private List<CoinSlotListener> listeners;
	
	/**
	 * Constructs a new CoinSlot with the given information.
	 * @param p - The sensor port to which the light sensor is connected
	 */
	public CoinSlot(SensorPort p) {
		// Set the value of isCoinInSlot
		isCoinInSlot = false;
		// Create the list of listeners
		listeners = new ArrayList<CoinSlotListener>();
		// Create the coin detector
		detector = new CoinDetector(new LightSensorWrapper(p));
		// Register with the coin detector
		detector.registerListener(this);
	}
	
	/**
	 * Registers the given listener to the list of listeners
	 * @param listener - The listener to register
	 */
	public void registerListenr(CoinSlotListener listener) {
		// Add the listener to the list of registered listeners
		listeners.add(listener);
	}
	
	/**
	 * Unregisters the given listener from the list of listeners.
	 * @param listener - The listener to unregister
	 */
	public void unregisterListener(CoinSlotListener listener) {
		// Remove the listener from the list of registered listeners
		listeners.remove(listener);
	}
	
	/**
	 * Implementation of the {@link LightSensorListener#onLightEvent(LightSensorEvent)} method.
	 * @param e - The event that was raised
	 */
	@Override
	public void onLightEvent(LightSensorEvent e) {
		// If the event data is above a certain threshold,
		if(e.getLightValue() > COIN_THRESHOLD) {
			// If a coin was not already in the slot
			if(!isCoinInSlot) {
				// Send the event to the listeners
				notifyListeners(true);
			}
			// Set the value of isCoinInSlot to true
			isCoinInSlot = true;
		} else { // Otherwise,
			// If a coin was in the slot,
			if(isCoinInSlot) {
				// Send an event to the listeners
				notifyListeners(false);
			}
			// Set the value of isCoinInSlot to false
			isCoinInSlot = false;
		}
	}
	
	/**
	 * Called when a state change occurs in the CoinSlot. Sends an event
	 * to the listeners informing them of the state change.
	 * @param state - If true, a coin is in the slot, if false, a coin is not in the slot
	 */
	private void notifyListeners(boolean state) {
		// For each listener in the list of registered listeners...
		for(CoinSlotListener l : listeners) {
			// Call the listening method
			l.onCoinEvent(state);
		}
	}
}
