public interface SightSensorListener extends EventListener {
	
	/**
	 * Runs logic on the listener whenever the
	 * sight sensor changes.
	 * @param e - The SightSensorEvent that was raised
	 */
	void onSightEvent(SightSensorEvent e);
}
