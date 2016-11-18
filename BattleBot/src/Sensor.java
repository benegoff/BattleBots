public interface Sensor {

	/**
	 * Registers the given listener on the sensor.
	 * @param listener - The {@link EventListener} to register
	 */
	void registerListener(EventListener listener);
	
	/**
	 * Unregisters the given listener from the sensor.
	 * @param listener - The {@link EventListener} to unregister
	 */
	void unregisterListener(EventListener listener);
}
