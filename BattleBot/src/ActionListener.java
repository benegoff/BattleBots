

/**
 * Created by Denver on 11/8/2016.
 */
public interface ActionListener extends EventListener {

	/**
	 * Runs logic on the listener whenever the
	 * current action changes.
	 * @param e - The ActionEvent that was raised
	 */
    void onActionEvent(ActionEvent action);
}
