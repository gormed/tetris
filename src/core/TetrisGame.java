
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
 * File: TetrisGame.java
 * Type: core.TetrisGame
 * 
 * Documentation created: 19.01.2012 - 16:34:23 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package core;

import framework.core.Application;
import framework.events.WindowControl;
import gui.InfoBackground;
import gui.Score;

import java.awt.Font;
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
	
	private static MusicPlayer player;
	
	/** The gui score. */
	@SuppressWarnings("unused")
	private static Score guiScore;
	
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
				if (Application.getInstance().isRunning())
					Application.getInstance().terminate();
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

		Font f = new Font("Tahoma", Font.BOLD, 14);
		guiInfoBackground = new InfoBackground(239,0,"resource/tetrisscore.png");
		guiScore = new Score(score, f);

		stepper.start();
		
	}
}
