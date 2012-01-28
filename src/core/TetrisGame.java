
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
 * File: TetrisGame.java
 * Type: core.TetrisGame
 * 
 * Documentation created: 22.01.2012 - 18:24:17 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package core;

import framework.core.Application;
import framework.events.WindowControl;
import gui.InfoBackground;
import gui.Level;
import gui.Score;

import java.awt.event.WindowEvent;

import logic.GameScore;
import logic.GameStepper;
import logic.MusicPlayer;

/**
 * The Class TetrisGame.
 */
public class TetrisGame {
	
	/** The stepper. */
	private static GameStepper stepper;
	
	/** The score. */
	private static GameScore score;
	
	/** The player. */
	@SuppressWarnings("unused")
	private static MusicPlayer player;
	
	/** The gui score. */
	@SuppressWarnings("unused")
	private static Score guiScore;
	
	/** The gui level. */
	@SuppressWarnings("unused")
	private static Level guiLevel;
	
	/** The gui info background. */
	@SuppressWarnings("unused")
	private static InfoBackground guiInfoBackground;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Application app = Application.getInstance();
		app.initialize(null);
		
		app.addWindowControl(new WindowControl() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * events.WindowControl#windowOpened(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowOpened(WindowEvent event) {
				// do nothing, you could load some resources here or sth like
				// that
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * events.WindowControl#windowIconified(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowIconified(WindowEvent event) {
				Application.getInstance().pause();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * events.WindowControl#windowDeiconified(java.awt.event.WindowEvent
			 * )
			 */
			@Override
			public void windowDeiconified(WindowEvent event) {
				Application.getInstance().resume();

			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * events.WindowControl#windowDeactivated(java.awt.event.WindowEvent
			 * )
			 */
			@Override
			public void windowDeactivated(WindowEvent event) {
				Application.getInstance().pause();
				GameStepper.getInstance().pause();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * events.WindowControl#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent event) {
				if (Application.getInstance().isRunning()) {
					Application.getInstance().terminate();
					MusicPlayer.getInstance().terminate();
				}
					
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * events.WindowControl#windowClosed(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosed(WindowEvent event) {
				// do nothing
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * events.WindowControl#windowActivated(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowActivated(WindowEvent event) {
				Application.getInstance().resume();
			}
		});
		
		app.setDimensions(400, 600);
		app.setTitle("Tetris");
		
		stepper = GameStepper.getInstance();
		score = GameScore.getInstance();
		player = MusicPlayer.getInstance();
		
		loadContent();
		
		app.start();
	}
	
	/**
	 * Load content.
	 */
	public static void loadContent() {

		guiInfoBackground = new InfoBackground(239,0,"resource/tetrisscore.png");
		guiScore = new Score(score);
		guiLevel = new Level();
		MusicPlayer.getInstance().playBackgroundSound();
		stepper.startGame();
		
	}
}
