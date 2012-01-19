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
 * File: GameOver.java
 * Type: gui.GameOver
 * 
 * Documentation created: 19.01.2012 - 16:34:22 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package gui;

import java.awt.Color;
import java.awt.Font;

import framework.objects.Text;

/**
 * The Class GameOver.
 */
public class GameOver {
	
	/** The Constant XPOS. */
	static final int XPOS = Pause.XPOS-40;
	
	/** The Constant YPOS. */
	static final int YPOS = Pause.YPOS;
	
	/** The Constant FONT. */
	static final Font FONT = Pause.FONT;

	/** The game over label. */
	public Text gameOverLabel;
	
	/** The retry label. */
	public Text retryLabel;
	
	/**
	 * Instantiates a new game over.
	 */
	public GameOver() {
		gameOverLabel = new Text(XPOS, YPOS, "GAME OVER", FONT, Color.red);
		retryLabel = new Text(30, YPOS + 40, "Press 'Enter' to try again!", new Font("Tahoma", Font.CENTER_BASELINE, 14), Color.black);
	}
	
	/**
	 * Make visible.
	 */
	public void makeVisible() {
		gameOverLabel.makeVisible();
		retryLabel.makeVisible();
	}

	/**
	 * Make invisible.
	 */
	public void makeInvisible() {
		gameOverLabel.makeInvisible();
		retryLabel.makeInvisible();
	}
	
}
