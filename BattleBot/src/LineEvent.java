public class LineEvent {

	private boolean isOnLine;
	
	/**
	 * Constructs a new LineEvent with the given information.
	 * @param foundLine - The value of whether or not the robot has found a line
	 */
	public LineEvent(boolean foundLine) {
		isOnLine = foundLine;
	}
	
	/**
	 * Access method for the event data of whether the robot is on a line.
	 * @return Returns the value of isOnLine
	 */
	public boolean isOnLine() {
		return isOnLine;
	}
	
}
