public class TouchEvent {

	private boolean isTouching;
	
	/**
	 * Creates a new TouchEvent with the given data.
	 * @param isPressed - The boolean value for if the event
	 */
	public TouchEvent(boolean isPressed) {
		// Set the value of isTouching
		isTouching = isPressed;
	}
	
	/**
	 * Returns the event data.
	 * @return Returns the value of whether or not the robot is now touching something
	 */
	public boolean isTouching() {
		return isTouching;
	}
}
