
public interface LeverListener {

	/**
	 * Called when a change occurs in the state of a lever.
	 * @param isPulled - Whether or not the lever is pulled
	 */
	void onLeverEvent(boolean isPulled);
}
