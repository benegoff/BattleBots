

import lejos.nxt.BasicMotorPort;
import lejos.nxt.MotorPort;

public class Drivetrain {
	
	public static final int POWER = 90;
	public static final int ROTATE_POWER = 85;
	
	private MotorPort leftWheel;
	private MotorPort rightWheel;
	
	private int currentPower;
	private int currentRotatePower;
	
	/**
	 * Constructs a new Drivetrain with the given information.
	 * @param left - The MotorPort that connects to the left wheel motor
	 * @param right - The MotorPort that connects to the right wheel motor
	 */
	public Drivetrain(MotorPort left, MotorPort right) {
		// Set the values of the left and right wheels
		leftWheel = left;
		rightWheel = right;
		// Set the current power values
		currentPower = POWER;
		currentRotatePower = ROTATE_POWER;
	}
	
	/**
	 * Sets power with which the robot will move forward or backward.
	 * @param power - The power value in the range 0 - 100, inclusive
	 */
	public void setCurrentPower(int power) {
		currentPower = clampValue(power, 0, 100);
	}
	
	/**
	 * Sets the value of the current rotation power.
	 * @param power - The power value in the range 0 - 100, inclusive
	 */
	public void setCurrentRotationPower(int power) {
		currentRotatePower = clampValue(power, 0, 100);
	}
	
	/**
	 * Clamps a value to within the range.
	 * @param value - The value to clamp
	 * @param min - The minimum value
	 * @param max - The maximum value
	 * @return The clamped value
	 */
	protected int clampValue(int value, int min, int max) {
		return (value < min) ? min : (value > max) ? max : value;
	}

	/**
	 * Tells the wheels to rotate and move the robot backward.
	 */
	public void moveBackward() {
		// Set both wheels to rotate forward
		leftWheel.controlMotor(currentPower, BasicMotorPort.FORWARD);
		rightWheel.controlMotor(currentPower, BasicMotorPort.FORWARD);
	}
	
	/**
	 * Tells the wheels to rotate and move the robot forward.
	 */
	public void moveForward() {
		// Set both wheels to rotate backward
		leftWheel.controlMotor(currentPower, BasicMotorPort.BACKWARD);
		rightWheel.controlMotor(currentPower, BasicMotorPort.BACKWARD);
	}
	
	/**
	 * Tells the wheels to rotate such that the robot rotates.
	 */
	public void rotateLeft() {
		// Set the left wheel to rotate forward
		leftWheel.controlMotor(currentRotatePower, BasicMotorPort.FORWARD);
		// Set the right wheel to rotate backward
		rightWheel.controlMotor(currentRotatePower, BasicMotorPort.BACKWARD);
	}
	
	public void rotateRight() {
		// Set the left wheel to rotate backward
		leftWheel.controlMotor(currentRotatePower, BasicMotorPort.BACKWARD);
		// Set the right wheel to rotate forward
		rightWheel.controlMotor(currentRotatePower, BasicMotorPort.FORWARD);
	}
	
	/**
	 * Tells the wheels to stop moving so that the robot stops moving.
	 */
	public void stopMovement() {
		// Set both wheels to stop moving
		leftWheel.controlMotor(currentPower, BasicMotorPort.STOP);
		rightWheel.controlMotor(currentPower, BasicMotorPort.STOP);
	}
}
