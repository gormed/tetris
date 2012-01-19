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
 * File: Level.java
 * Type: gui.Level
 * 
 * Documentation created: 19.01.2012 - 17:59:36 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package gui;

import java.awt.Color;
import java.awt.Font;

import logic.GameStepper;

import framework.objects.Text;

/**
 * The Class Level.
 */
public class Level {
	
	/** The Constant XPOS. */
	static final int XPOS = 310;

	/** The Constant YPOS. */
	static final int YPOS = 482;
	
	/** The Constant FONT. */
	static final Font FONT = new Font("Tahoma", Font.BOLD, 28);
	
	/** The level label. */
	private Text levelLabel;

	/**
	 * Instantiates a new level.
	 */
	public Level() {
		levelLabel = new Text(XPOS, YPOS, "0", FONT, Color.black);
		levelLabel.makeVisible();
		
		GameStepper.getInstance().setLevelLabel(this);
	}
	
	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(int level) {
		levelLabel.changeText("" + level);
	}

}
