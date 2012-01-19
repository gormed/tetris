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
 * Documentation created: 19.01.2012 - 15:32:13 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package logic;

import gui.Score;

/**
 * The Class GameScore.
 */
public class GameScore {

	private static final int BLOCK_SCORE = 5;
	
	
	private static final int MAX_TIME = 30;
	
	private static final int POINTS_PER_LINE = 250;
	
	private static final int LINE_MULTIPLICATOR = 2;

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
	private int timeBlockCreated;

	/** The time block inactive. */
	private int timeBlockInactive;

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
	void setTimeBlockCreated(int time) {
		timeBlockCreated = time;
	}

	/**
	 * Sets the time block inactive.
	 * 
	 * @param time
	 *            the new time block inactive
	 */
	void setTimeBlockInactive(int time) {
		timeBlockInactive = time;
		calculateSubScore();
		calculateScore();
	}

	/**
	 * Calculate sub score.
	 */
	private void calculateSubScore() {
		int diff = timeBlockInactive - timeBlockCreated;
		lastSubScore = (MAX_TIME - diff) * BLOCK_SCORE;
	}

	/**
	 * Calculate score.
	 */
	private void calculateScore() {
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

	/**
	 * Lines removed.
	 *
	 * @param i the i
	 */
	void linesRemoved(int i) {
		if (i > 4 || i < 1)
			return;
		else {
			lastSubScore = 250 + 250 * LINE_MULTIPLICATOR * (i-1);
			calculateScore();
		}
	}

}
