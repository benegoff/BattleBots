import java.util.Random;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.util.Delay;

public class SlotMachine implements LeverListener, ResetButtonListener, CoinSlotListener {

	private static final int MAX_COIN_LOAD = 4;
	
	private Lever slotLever;
	private ResetButton resetButton;
	private CoinSlot coinSlot;
	
	private CoinWheel coinWheel;
	private CoinDoor coinDoor;
	private SoundMaker soundProducer;
	
	private SlotMachineState currentState;
	
	/**
	 * Constructs a new slot machine.
	 */
	public SlotMachine() {
		// Set the slot machine's initial state
		currentState = SlotMachineState.NO_COIN;
		// Construct the components
		constructComponents();
		// Register with event providers
		registerEvents();
	}

	/**
	 * Constructs the needed components of the slot machine.
	 */
	private void constructComponents() {
		// Construct the coin door
		coinDoor = new CoinDoor(Motor.A);
		// Construct the Coin wheel
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
	
	/**
	 * Registers the SlotMachine with the event providers.
	 */
	private void registerEvents() {
		// Register with the coin slot
		coinSlot.registerListenr(this);
		// Register with the lever
		slotLever.registerListener(this);
		// Register with the reset button
		resetButton.registerListener(this);
	}
	
	/**
	 * The starting method of the slot machine.
	 */
	public void start() {
		// While the game has not been played...
		while(currentState != SlotMachineState.GAME_OVER) {
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
		if(currentState == SlotMachineState.COIN_IN_SLOT) {
			// Change the state of the slot machine to PLAYING_GAME
			currentState = SlotMachineState.PLAYING_GAME;
			// Play the game
			playGame();
		}
	}
	
	/**
	 * Called when the state of the reset button changes.
	 * @param isPushed - If true, the reset button is pushed, otherwise is false
	 */
	@Override
	public void onResetButtonEvent(boolean isPushed) {
		// If a coin is in the coin slot
		if(currentState == SlotMachineState.COIN_IN_SLOT) {
			// Change the state of the slot machine to RETURN_COIN
			currentState = SlotMachineState.RETURNING_COIN;
			// Dispense a single coin
			dispenseCoin();
			// Change the state of the slot machine to NO_COIN
			currentState = SlotMachineState.NO_COIN;
		}
	}
	
	/**
	 * Called when the state of the coin slot changes.
	 * @param isCoinPreset - If true, then a coin is in the slot, otherwise is false
	 */
	@Override
	public void onCoinEvent(boolean isCoinPresent) {
		// If the slot machine is waiting for a coin,
		if(currentState == SlotMachineState.NO_COIN) {
			// If there is now a coin in the slot,
			if(isCoinPresent) {
				// Change the state of the slot machine to COIN_IN_SLOT
				currentState = SlotMachineState.COIN_IN_SLOT;				
			}
		} else { // Otherwise there is a coin in the slot already,
			// If there is no longer a coin in the slot and the game is not over,
			if(!isCoinPresent && currentState != SlotMachineState.GAME_OVER) {
				// Change the state of the slot machine to NO_COIN
				currentState = SlotMachineState.NO_COIN;
			}
		}
	}
	
	/* ===== SLOT MACHINE METHODS ===== */
	
	/**
	 * Called when the lever is pulled when a coin is in the coin slot.
	 * This method plays a single round of the game
	 */
	public void playGame() {
		// Get the current time
		long currentTime = System.currentTimeMillis();
		long timeTilOver; 
		
		// Generate a number
		int num = generateNumber();
		// Display the number
		System.out.println(num);
		
		// If the number is even,
		if(isNumberEven(num)) {
			// Play winning song
			timeTilOver = soundProducer.playWinningSound();
			// Dispense all coins
			dispenseAllCoins();
		} else { // Otherwise, 
			// Play losing song
			timeTilOver = soundProducer.playLosingSound();
			// Rotate coin wheel
			coinWheel.rotate();
		}
		
		// Set the current slot machine state to ENDING
		currentState = SlotMachineState.ENDING;
		try {
			// Wait for the song to end
			Thread.sleep((currentTime + timeTilOver) - System.currentTimeMillis());
			// Set the current state to GAME_OVER
			currentState = SlotMachineState.GAME_OVER;
		} catch(InterruptedException e) {
			System.out.println("Interrupted!");
		}
	}
	
	/**
	 * Dispenses all coins that can be stored in the system.
	 */
	private void dispenseAllCoins() {
		// For each coin that the system can hold...
		for(int i = 0; i < MAX_COIN_LOAD; i++) {
			// Dispense the coin
			dispenseCoin();
		}
	}
	
	/**
	 * Dispenses a single coin from the system.
	 */
	private void dispenseCoin() {
		// Rotate the coin wheel
		coinWheel.rotate();
		// Open the coin door
		coinDoor.openDoor();
		// Wait for 1 second
		Delay.msDelay(1500);
		// Close the coin door
		coinDoor.closeDoor();
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
