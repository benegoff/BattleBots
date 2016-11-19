import java.util.Random;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;

public class SlotMachine implements LeverListener, ResetButtonListener, CoinSlotListener {

	private Lever slotLever;
	private ResetButton resetButton;
	private CoinSlot coinSlot;
	
	private CoinWheel coinWheel;
	private CoinDoor coinDoor;
	private SoundMaker soundProducer;
	
	private boolean gameHasNotBeenPlayed;
	
	/**
	 * Constructs a new slot machine.
	 */
	public SlotMachine() {
		// Set the value of gameHasNotBeenPlayed
		gameHasNotBeenPlayed = true;
		// Construct the components
		constructComponents();
		// Register with event providers
		registerEvents();
	}

	/**
	 * Constructs the needed components of the slot machine.
	 */
	private void constructComponents() {
		// Construct the coin Door
		coinDoor = new CoinDoor(Motor.A);
		// Construct the CoinWheel
		coinWheel = new CoinWheel(Motor.B);
		// Construct the coin slot
		coinSlot = new CoinSlot(SensorPort.S1);
		// Construct the lever
		slotLever = new Lever(SensorPort.S2);
		// Construct the rest button
		resetButton = new ResetButton(SensorPort.S3);
		// Create the sound maker
		soundProducer = new SoundMaker();
	}
	
	private void registerEvents() {
		// Register with the coin slot
		coinSlot.registerListenr(this);
		// Register with the lever
		slotLever.registerListener(this);
		// Register with the reset button
		resetButton.registerListener(this);
	}
	
	/**
	 * The starting method of the slot machine
	 */
	public void start() {
		// While the game has not been played...
		while(gameHasNotBeenPlayed) {
			// Yield the thread
			Thread.yield();
		}
	}
	
	/* ===== LISTENERS ===== */
	
	/**
	 * Called when the state of the lever changes.
	 * @param isPulled - If true, the lever is pulled, otherwise is false
	 */
	@Override
	public void onLeverEvent(boolean isPulled) {
		// If a coin is in the coin slot
			// Play the game
	}
	
	/**
	 * Called when the state of the reset button changes.
	 * @param isPushed - If true, the reset button is pushed, otherwise is false
	 */
	@Override
	public void onResetButtonEvent(boolean isPushed) {
		// If a coin is in the coin slot
			// Dispense a single coin
	}
	
	/**
	 * Called when the state of the coin slot changes.
	 * @param isCoinPreset - If true, then a coin is in the slot, otherwise is false
	 */
	@Override
	public void onCoinEvent(boolean isCoinPresent) {
		
	}
	
	/* ===== SLOT MACHINE METHODS ===== */
	
	/**
	 * Called when the lever is pulled when a coin is in the coin slot.
	 * This method plays a single round of the game
	 */
	public void playGame() {
		// Generate a number
		int num = generateNumber();
		// If the number is even,
		if(isNumberEven(num)) {
			// Play winning song
			// Dispense all coins
		} else { // Otherwise, 
			// play losing song
			// Rotate coin wheel
		}
	}
	
	/**
	 * Dispenses all coins that can be stored in the system.
	 */
	private void dispenseAllCoins() {
		// Dispense all 4 coins in the system.
	}
	
	/**
	 * Dispenses a single coin from the system.
	 */
	private void dispenseCoin() {
		// Rotate the coin wheel
		// Open the coin door
		// Wait for 1 second
		// Close the coin door
	}
	
	/**
	 * Generates a random number between 0 and 9, inclusive.
	 * @return The randomly generated number
	 */
	private int generateNumber() {
		// Create a new Random class
		Random r = new Random();
		// Return the next integer between 0 and 9
		return r.nextInt(10);
	}
	
	/**
	 * Determines if a given number is even or odd.
	 * @param number - The number to check
	 */
	private boolean isNumberEven(int number) {
		// Return whether or not the given number is even
		return number % 2 == 0;
	}
}
