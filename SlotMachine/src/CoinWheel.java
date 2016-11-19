import lejos.nxt.NXTRegulatedMotor;

public class CoinWheel {
	
	private NXTRegulatedMotor motor;
	
	/**
	 * Constructs a new CoinWheel
	 * @param m - The motor that controls the coin wheel
	 */
	public CoinWheel(NXTRegulatedMotor m) {
		// Set the value of the motor
		motor = m;
	}
	
	/**
	 * Rotates the coin wheel by 90 degrees.
	 */
	public void rotate() {
		motor.rotate(90);
	}
}
