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
 * File: StartGame.java
 * Type: gui.StartGame
 * 
 * Documentation created: 22.01.2012 - 18:24:15 by Hans
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
