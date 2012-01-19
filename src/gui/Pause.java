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
 * File: Pause.java
 * Type: gui.Pause
 * 
 * Documentation created: 19.01.2012 - 15:56:14 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package gui;

import java.awt.Color;
import java.awt.Font;

import framework.objects.Text;

/**
 * The Class Pause.
 */
public class Pause {

	/** The Constant XPOS. */
	static final int XPOS = 4 * 20;

	/** The Constant YPOS. */
	static final int YPOS = 14 * 20;

	/** The Constant FONT. */
	static final Font FONT = new Font("Tahoma", Font.BOLD, 26);

	/** The pause label. */
	private Text pauseLabel;

	/**
	 * Instantiates a new pause.
	 */
	public Pause() {
		pauseLabel = new Text(XPOS, YPOS, "PAUSE", FONT, Color.BLACK);
	}

	/**
	 * Make visible.
	 */
	public void makeVisible() {
		pauseLabel.makeVisible();
	}

	/**
	 * Make invisible.
	 */
	public void makeInvisible() {
		pauseLabel.makeInvisible();
	}

}
