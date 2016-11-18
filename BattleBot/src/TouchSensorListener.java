public interface TouchSensorListener extends EventListener {
	
	/**
	 * Runs logic on the listener whenever the
	 * touch sensor changes.
	 * @param e - The TouchSensorEvent that was raised
	 */
	void onTouchEvent(TouchSensorEvent e);
}
