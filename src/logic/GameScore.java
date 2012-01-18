/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Tetris Project (c) 2011 - 2012 by Hans Ferchland & Hady Khalifa
 * 
 * 
 * Tetris is a tetris clone in java using the JIT Framework
 * 
 * 
 * Tetris rights are by its owners/creators 
 * (Hans Ferchland & Hady Khalifa). You have no right to 
 * publish and/or deliver the code or application in any way!
 * 
 * If that is done by someone, please report it!
 * 
 * Email us: hans.ferchland@gmx.de
 * 
 * Project: Tetris
 * File: GameScore.java
 * Type: logic.GameScore
 * 
 * Documentation created: 18.01.2012 - 16:57:07 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package logic;

import gui.Score;

/**
 * The Class GameScore.
 */
public class GameScore {

	private static final int BLOCK_SCORE = 5;

	/** The instance. */
	private static GameScore instance;

	/**
	 * Gets the single instance of FieldCollision.
	 * 
	 * @return single instance of FieldCollision
	 */
	public static GameScore getInstance() {
		if (instance != null)
			return instance;
		else
			return instance = new GameScore();
	}

	/**
	 * Instantiates a new game score.
	 */
	private GameScore() {
		totalScore = 0;
		lastSubScore = 0;
	}

	/** The total score. */
	private int totalScore;

	/** The last sub score. */
	private int lastSubScore;

	/** The time block created. */
	private long timeBlockCreated;

	/** The time block inactive. */
	private long timeBlockInactive;

	/** The score label. */
	private Score scoreLabel;

	/**
	 * Sets the score label.
	 * 
	 * @param scoreLabel
	 *            the new score label
	 */
	public void setScoreLabel(Score scoreLabel) {
		this.scoreLabel = scoreLabel;
	}

	/**
	 * Sets the time block created.
	 * 
	 * @param time
	 *            the new time block created
	 */
	void setTimeBlockCreated(long time) {
		timeBlockCreated = time;
	}

	/**
	 * Sets the time block inactive.
	 * 
	 * @param time
	 *            the new time block inactive
	 */
	void setTimeBlockInactive(long time) {
		timeBlockInactive = time;
		calculateScore();
	}

	/**
	 * Calculate sub score.
	 */
	private void calculateSubScore() {
		long diff = timeBlockInactive - timeBlockCreated;
		float sec = diff * 0.001f;
		float max = GameStepper.getInstance().getPeriod() * 0.001f
				* FieldCollision.GAME_HEIGHT;
		lastSubScore = (int) (max - sec) * BLOCK_SCORE;
	}

	/**
	 * Calculate score.
	 */
	private void calculateScore() {
		calculateSubScore();
		totalScore += lastSubScore;
		scoreLabel.refreshScore();
	}

	/**
	 * Gets the game score.
	 * 
	 * @return the game score
	 */
	public int getGameScore() {
		return totalScore;
	}

	/**
	 * Gets the sub score.
	 * 
	 * @return the sub score
	 */
	public int getSubScore() {
		return lastSubScore;
	}

}
