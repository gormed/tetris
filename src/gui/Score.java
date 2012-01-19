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
 * File: Score.java
 * Type: gui.Score
 * 
 * Documentation created: 19.01.2012 - 16:34:22 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package gui;

import java.awt.Color;
import java.awt.Font;

import logic.GameScore;

import framework.objects.Text;

/**
 * The GUI Class Score.
 * 
 * @author Hans
 */
public class Score {

	/** The Constant XPOS. */
	static final int XPOS = 265;

	/** The Constant YPOS. */
	static final int YPOS = 338;
	
	static final Font FONT = new Font("Tahoma", Font.BOLD, 14);

	/** The Constant title. */
	static final String title = "Total: ";

	/** The score. */
	private GameScore score;

	/** The score label. */
	private Text scoreLabel;
	
	/** The sub score label. */
	private Text subScoreLabel;

	/**
	 * Instantiates a new score.
	 * 
	 * @param score
	 *            the score
	 * @param font
	 *            the font
	 */
	public Score(GameScore score) {

		scoreLabel = new Text(XPOS, YPOS, "0", FONT, Color.BLACK);
		scoreLabel.makeVisible();
		this.score = score;
		score.setScoreLabel(this);

		subScoreLabel = new Text(XPOS, YPOS + 30, "", FONT, Color.DARK_GRAY);
		subScoreLabel.makeVisible();
	}

	/**
	 * Refresh score.
	 */
	public void refreshScore() {
		scoreLabel.changeText(String.valueOf(score.getGameScore()));
		subScoreLabel.changeText("+" + String.valueOf(score.getSubScore()));
	}
}
