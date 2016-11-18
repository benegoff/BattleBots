public interface LightSensorListener extends EventListener {

	/**
	 * Runs logic on the listener whenever the
	 * light sensor changes.
	 * @param e - The LightSensorEvent that was raised
	 */
	void onLightSensorEvent(LightSensorEvent e);
	
}
