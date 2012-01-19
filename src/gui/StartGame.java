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
 * File: StartGame.java
 * Type: gui.StartGame
 * 
 * Documentation created: 19.01.2012 - 18:13:28 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package gui;

import java.awt.Color;
import java.awt.Font;

import framework.objects.Picture;
import framework.objects.Text;

/**
 * The Class StartGame.
 */
public class StartGame {

	/** The Constant XPOS. */
	static final int XPOS = 1 * 20;

	/** The Constant YPOS. */
	static final int YPOS = 14 * 20;

	/** The Constant FONT. */
	static final Font FONT = new Font("Tahoma", Font.BOLD, 18);
	
	/** The background. */
	private Picture background;
	
	/** The desired level label. */
	private Text desiredLevelLabel;
	
	/**
	 * Instantiates a new start game.
	 */
	public StartGame() {
		background = new Picture(XPOS, YPOS, "resource/startgame.png");
		desiredLevelLabel = new Text(169, 345, "0", FONT, Color.black);
	}
	
	/**
	 * Change display.
	 *
	 * @param level the level
	 */
	public void changeDisplay(int level) {
		desiredLevelLabel.changeText("" + level);
	}

	/**
	 * Make visible.
	 */
	public void makeVisible() {
		background.makeVisible();
		desiredLevelLabel.makeVisible();
	}
	
	/**
	 * Make invisible.
	 */
	public void makeInvisible() {
		background.makeInvisible();
		desiredLevelLabel.makeInvisible();
	}
}
