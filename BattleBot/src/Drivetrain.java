

import lejos.nxt.BasicMotorPort;
import lejos.nxt.MotorPort;

public class Drivetrain {
	
	private static final int POWER = 90;
	private static final int ROTATE_POWER = 85;
	
	private MotorPort leftWheel;
	private MotorPort rightWheel;
	
	/**
	 * Constructs a new Drivetrain with the given information.
	 * @param left - The MotorPort that connects to the left wheel motor
	 * @param right - The MotorPort that connects to the right wheel motor
	 */
	public Drivetrain(MotorPort left, MotorPort right) {
		// Set the values of the left and right wheels
		leftWheel = left;
		rightWheel = right;
	}

	/**
	 * Tells the wheels to rotate and move the robot backward.
	 */
	public void moveBackward() {
		// Set both wheels to rotate forward
		leftWheel.controlMotor(POWER, BasicMotorPort.FORWARD);
		rightWheel.controlMotor(POWER, BasicMotorPort.FORWARD);
	}
	
	/**
	 * Tells the wheels to rotate and move the robot forward.
	 */
	public void moveForward() {
		// Set both wheels to rotate backward
		leftWheel.controlMotor(POWER, BasicMotorPort.BACKWARD);
		rightWheel.controlMotor(POWER, BasicMotorPort.BACKWARD);
	}
	
	/**
	 * Tells the wheels to rotate such that the robot rotates.
	 */
	public void rotate() {
		// Set the left wheel to rotate forward
		leftWheel.controlMotor(ROTATE_POWER, BasicMotorPort.FORWARD);
		// Set the right wheel to rotate backward
		rightWheel.controlMotor(ROTATE_POWER, BasicMotorPort.BACKWARD);
	}
	
	public void rotateRight() {
		// Set the left wheel to rotate backward
		leftWheel.controlMotor(ROTATE_POWER, BasicMotorPort.BACKWARD);
		// Set the right wheel to rotate forward
		rightWheel.controlMotor(ROTATE_POWER, BasicMotorPort.FORWARD);
	}
	
	/**
	 * Tells the wheels to stop moving so that the robot stops moving.
	 */
	public void stopMovement() {
		// Set both wheels to stop moving
		leftWheel.controlMotor(POWER, BasicMotorPort.STOP);
		rightWheel.controlMotor(POWER, BasicMotorPort.STOP);
	}
}
