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
 * File: GameOver.java
 * Type: gui.GameOver
 * 
 * Documentation created: 22.01.2012 - 18:24:17 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package gui;

import java.awt.Color;
import java.awt.Font;

import framework.core.Application;
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
		Application.getInstance().removeUpdateObject(gameOverLabel);
		Application.getInstance().removeUpdateObject(retryLabel);
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
