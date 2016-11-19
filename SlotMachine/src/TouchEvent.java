
public class TouchEvent extends Event {

	private final boolean isPressed;
	
	/**
	 * Constructs a new TouchEvent with the given information.
	 * @param pressed - Whether or not the button is pressed
	 */
	public TouchEvent(boolean pressed) {
		// Set the value of isPressed
		isPressed = pressed;
	}
	
	/**
	 * Returns the value of isPressed that was sent in the event.
	 * @return The value of isPrssed
	 */
	public boolean getIsPressed() {
		return isPressed;
	}
}
