import java.util.Random;

import lejos.nxt.SensorPort;

public class SlotMachine {

	private Lever slotLever;
	
	private CoinWheel coinHolder;
	private CoinDoor coinDoor;
	private CoinSlot coinSlot;
	
	private boolean gameHasNotBeenPlayed;
	
	/**
	 * Constructs a new slot machine.
	 */
	public SlotMachine() {
		// Set the value of gameHasNotBeenPlayed
		gameHasNotBeenPlayed = true;
		// Construct the CoinWheel
		coinHolder = new CoinWheel();
		// Construct the coin Door
		coinDoor = new CoinDoor();
		// Construct the lever
		slotLever = new Lever(SensorPort.S2);
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
	
	public void playGame() {
		// Generate a number
		// If the number is even,
			// Play winning song
			// Dispense all coins
		// Otherwise,
			// play losing song
			// Rotate coin wheel
	}
	
	public void returnCoin() {
		// Rotate coin wheel
		// Open coin door
	}
	
	private void dispenseAllCoins() {
		// Dispense all 4 coins in the system.
	}
	
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
