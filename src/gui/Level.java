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
 * File: Level.java
 * Type: gui.Level
 * 
 * Documentation created: 28.01.2012 - 20:27:59 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package gui;

import java.awt.Color;
import java.awt.Font;

import logic.GameStepper;

import framework.core.Application;
import framework.objects.Text;

/**
 * The Class Level.
 */
public class Level {

	/** The Constant XPOS. */
	static final int XPOS = 310;

	/** The Constant YPOS. */
	static final int YPOS = 475;

	/** The Constant FONT. */
	static final Font FONT = new Font("Tahoma", Font.BOLD, 28);

	/** The level label. */
	private Text levelLabel;

	/** The lines label. */
	private Text linesLabel;

	/** The lines. */
	private String lines = "Lines: ";

	/**
	 * Instantiates a new level.
	 */
	public Level() {
		levelLabel = new Text(XPOS, YPOS, "0", FONT, Color.black);
		linesLabel = new Text(XPOS - 15, YPOS + 20, "", new Font("Tahoma",
				Font.BOLD, 12), Color.black);
		levelLabel.makeVisible();
		linesLabel.makeVisible();

		GameStepper.getInstance().setLevelLabel(this);
		Application.getInstance().removeUpdateObject(levelLabel);
		Application.getInstance().removeUpdateObject(linesLabel);
	}

	/**
	 * Sets the level.
	 * 
	 * @param level
	 *            the new level
	 */
	public void setLevel(int level) {
		levelLabel.changeText("" + level);
	}

	/**
	 * Sets the lines.
	 * 
	 * @param lines
	 *            the new lines
	 */
	public void setLines(int lines, int currentLevel) {
		String s = this.lines + lines + "/"
				+ GameStepper.LINES_FOR_NEXT_LEVEL[currentLevel];
		linesLabel.setPosition(XPOS - (s.length() * 4) / 2, YPOS + 20);
		linesLabel.changeText(s);
	}

}
