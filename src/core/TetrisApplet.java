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
 * File: TetrisApplet.java
 * Type: core.TetrisApplet
 * 
 * Documentation created: 29.01.2012 - 23:07:24 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package core;

import java.awt.Color;
import java.awt.Font;

import framework.core.Application;
import framework.core.JITApplet;
import framework.objects.Text;
import gui.InfoBackground;
import gui.Level;
import gui.Score;

import logic.GameScore;
import logic.GameStepper;
import logic.MusicPlayer;

/**
 * The Class TetrisApplet is the main class if the game is running as an applet
 * in the browser.
 * <p>
 * If the applet is started in the browser only this class will act as the main class,
 * but not <code>TetrisGame</code>.
 * </p>
 */
public class TetrisApplet extends JITApplet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2770508781564588611L;

	/** The game stepper, main logic class. */
	private static GameStepper stepper;

	/** The score, score logic class. */
	private static GameScore score;

	/** The player for audio files. */
	private static MusicPlayer player;

	/** The gui for the score. */
	private static Score guiScore;

	/** The gui for the level. */
	private static Level guiLevel;

	/** The gui for the background. */
	private static InfoBackground guiInfoBackground;

	/** The wait text at begin. */
	private static Text wait;

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.core.JITApplet#init()
	 */
	@Override
	public void init() {
		application = Application.getInstance();
		application.initialize(this);

		wait = new Text(100, 100, "Please wait while loading...", new Font(
				"Tahoma", Font.BOLD, 12), Color.black);
		wait.makeVisible();

		loadContent();

		stepper.startGame();

		application.start();
		setVisible(true);
		this.transferFocus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.core.JITApplet#start()
	 */
	@Override
	public void start() {
		if (wait != null) {
			wait.makeInvisible();
			wait.dispose();
			wait = null;
		}
		super.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.core.JITApplet#stop()
	 */
	@Override
	public void stop() {
		stepper.pause();
		super.stop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.applet.Applet#destroy()
	 */
	@Override
	public void destroy() {
		guiInfoBackground.dispose();
		guiInfoBackground = null;
		guiScore.dispose();
		guiScore = null;
		guiLevel.dispose();
		guiLevel = null;
		stepper.exit();
		stepper = null;
		score = null;
		player.terminate();
		player = null;
		application.terminate();
		application = null;
		super.destroy();
	}

	/**
	 * Load content of the game.
	 */
	public static void loadContent() {
		stepper = GameStepper.getInstance();
		score = GameScore.getInstance();
		player = MusicPlayer.getInstance();
		guiInfoBackground = new InfoBackground(239, 0,
				"resource/tetrisscore.png");
		guiScore = new Score(score);
		guiLevel = new Level();
		MusicPlayer.getInstance().playBackgroundSound(-10);

	}
}
