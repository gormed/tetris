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
 * File: Score.java
 * Type: gui.Score
 * 
 * Documentation created: 22.01.2012 - 18:24:17 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package gui;

import java.awt.Color;
import java.awt.Font;

import logic.GameScore;

import framework.core.Application;
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
	
	/** The Constant FONT. */
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
	 * @param score the score
	 */
	public Score(GameScore score) {

		scoreLabel = new Text(XPOS, YPOS, "0", FONT, Color.BLACK);
		scoreLabel.makeVisible();
		this.score = score;
		score.setScoreLabel(this);

		subScoreLabel = new Text(XPOS, YPOS + 30, "", FONT, Color.DARK_GRAY);
		subScoreLabel.makeVisible();
		
		Application.getInstance().removeUpdateObject(scoreLabel);
		Application.getInstance().removeUpdateObject(subScoreLabel);
	}

	/**
	 * Refresh score.
	 */
	public void refreshScore() {
		scoreLabel.changeText(String.valueOf(score.getGameScore()));
		subScoreLabel.changeText("+" + String.valueOf(score.getSubScore()));
	}
}
