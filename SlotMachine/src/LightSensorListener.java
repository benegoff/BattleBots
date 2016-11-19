
public interface LightSensorListener extends EventListener {

	/**
	 * Called when a coin event is raised.
	 * @param e - The CoinEvent that was raised
	 */
	void onLightEvent(LightSensorEvent e);
}
