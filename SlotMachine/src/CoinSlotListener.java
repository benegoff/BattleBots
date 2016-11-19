
public interface CoinSlotListener {

	/**
	 * Called when a state change occurs in a CoinSlot. 
	 * @param isCoinPresent - If true, then a coin is present. Otherwise false.
	 */
	void onCoinEvent(boolean isCoinPresent);
}
