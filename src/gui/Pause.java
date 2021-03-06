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
 * File: Pause.java
 * Type: gui.Pause
 * 
 * Documentation created: 29.01.2012 - 23:07:25 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package gui;

import java.awt.Color;
import java.awt.Font;

import framework.core.Application;
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
		Application.getInstance().removeUpdateObject(pauseLabel);
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
	
	/**
	 * Dispose.
	 */
	public void dispose() {
		makeInvisible();
		pauseLabel.dispose();
	}

}
