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
 * Documentation created: 18.01.2012 - 16:46:57 by Hans
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
public class Score extends Text {

	/** The Constant XPOS. */
	public static final int XPOS = 250;
	
	/** The Constant YPOS. */
	public static final int YPOS = 300;
	
	/** The Constant title. */
	public static final String title = "Total: ";
	
	/** The score. */
	private GameScore score;
	
	/**
	 * Instantiates a new score.
	 *
	 * @param score the score
	 * @param font the font
	 */
	public Score(GameScore score, Font font) {
		super(XPOS, YPOS, title + score.getGameScore(), font, Color.BLACK);
		this.score = score;
		score.setScoreLabel(this);
		makeVisible();
	}
	
	/**
	 * Refresh score.
	 */
	public void refreshScore() {
		changeText(title + score.getGameScore());
	}

}
