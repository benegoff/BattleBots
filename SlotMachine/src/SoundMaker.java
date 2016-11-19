import lejos.nxt.Sound;

public class SoundMaker {

	private static final String WINNING_PATH = "";
	private static final String LOSING_PATH = "";
	
	/**
	 * Plays the winning song.
	 * @return The number of milliseconds for which the song will play
	 */
	public long playWinningSound() {
		// Get the file for the winning file
		java.io.File winningFile = new java.io.File(WINNING_PATH);
		// Play the winning music
		return Sound.playSample(winningFile, 100);
	}
	
	/**
	 * Plays the losing song.
	 * @return The number of milliseconds for which the song will play
	 */
	public long playLosingSound() {
		// Get the file for the losing file
		java.io.File losingFile = new java.io.File(LOSING_PATH);
		// Play the losing sound
		return Sound.playSample(losingFile, 100);
	}
}
