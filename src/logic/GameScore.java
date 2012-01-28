/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Tetris Project (c) 2011 - 2012 by Hans Ferchland & Hady Khalifa
 * 
 * 
 * Tetris is a tetris clone in java using the JIT Framework.
 * The project was created for educational purposes and may be used under the GNU 
 * Public license only.
 *
 * If you modify it please let other people have part of it!
 *
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * GNU Public License
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License 3 as published by
 * the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 
 * Email us: hans.ferchland@gmx.de
 * 
 * Project: Tetris
 * File: GameScore.java
 * Type: logic.GameScore
 * 
 * Documentation created: 28.01.2012 - 20:21:55 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package logic;

import gui.Score;

/**
 * The Class GameScore.
 */
public class GameScore {

	/** The Constant BLOCK_SCORE. */
	private static final int BLOCK_SCORE = 5;
	
	/** The Constant MAX_TIME. */
	private static final int MAX_TIME = 30;
	
	/** The Constant POINTS_PER_LINE. */
	private static final int POINTS_PER_LINE = 150;
	
	/** The Constant LINE_MULTIPLICATOR. */
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
	
	/** The total lines removed. */
	private int totalLinesRemoved = 0;

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
			int level = GameStepper.getInstance().getLevel();
			lastSubScore = POINTS_PER_LINE + POINTS_PER_LINE * LINE_MULTIPLICATOR * (i-1) * level;
			calculateScore();
			MusicPlayer.getInstance().playSound(i, 0.0f);
			totalLinesRemoved += i;
		}
	}
	
	/**
	 * Gets the total lines removed.
	 *
	 * @return the total lines removed
	 */
	int getTotalLinesRemoved() {
		return totalLinesRemoved;
	}
	
	/**
	 * Resets the game score.
	 */
	void resetGameScore() {
		totalLinesRemoved = 0;
		lastSubScore = 0;
		totalScore = 0;
		scoreLabel.refreshScore();
	}

	/**
	 * Preset game score.
	 *
	 * @param linesRemoved the lines removed
	 */
	void presetGameScore(int linesRemoved) {
		totalLinesRemoved = linesRemoved;
	}
	
}
