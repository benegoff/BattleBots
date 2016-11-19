
public interface ResetButtonListener {

	/**
	 * Called when the state of the reset button changes.
	 * @param isPushed - If true, the reset button is pushed, otherwise is false
	 */
	void onResetButtonEvent(boolean isPushed);
}
