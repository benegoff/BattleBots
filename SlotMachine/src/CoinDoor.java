import lejos.nxt.NXTRegulatedMotor;

public class CoinDoor {

	private NXTRegulatedMotor motor;
	private boolean isOpen;
	
	/**
	 * Constructs a new CoinDoor with the given information.
	 * @param m - The motor that controls the coin door
	 */
	public CoinDoor(NXTRegulatedMotor m) {
		// Set the value of the motor
		motor = m;
		// Set the value of isOpen
		isOpen = false;
	}
	
	/**
	 * Opens the coin door.
	 */
	public void openDoor() {
		// If the coin door is closed,
		if(!isOpen) {
			// Rotate the coin door motor by 90 degrees
			motor.rotate(90);
			// Set the value of isOpen to true
			isOpen = true;
		}
	}
	
	/**
	 * Closes the coin door.
	 */
	public void closeDoor() {
		// If the coin door is open,
		if(isOpen) {
			// Rotate the coin door motor by -90 degrees
			motor.rotate(-90);
			// Set the value of isOpen to false
			isOpen = false;
		}
	}
}
