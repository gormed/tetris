/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * TetrisApplet Project (c) 2012 - 2011 by Hans Ferchland & Hady Khalifa
 * 
 * 
 * TetrisApplet is a towerdefense like game for android devices...
 * 
 * 
 * TetrisApplet rights are by its owners/creators (Hans Ferchland & Hady Khalifa). 
 * You have no right to edit, publish and/or deliver the code or android 
 * application in any way! If that is done by someone, please report it!
 * 
 * Email us: hans.ferchland@gmx.de
 * 
 * Project: TetrisApplet
 * File: TetrisApplet.java
 * Type: core.TetrisApplet
 * 
 * Documentation created: 27.01.2012 - 16:54:46 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package core;

import framework.core.JITApplet;
import gui.InfoBackground;
import gui.Level;
import gui.Score;

import logic.GameScore;
import logic.GameStepper;
import logic.MusicPlayer;

/**
 * The Class TetrisApplet.
 */
public class TetrisApplet extends JITApplet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2770508781564588611L;

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
	
	/* (non-Javadoc)
	 * @see framework.core.JITApplet#init()
	 */
	@Override
	public void init() {
		super.init();
		
		application.setDimensions(400, 600);
		application.setTitle("Tetris");
		
		stepper = GameStepper.getInstance();
		score = GameScore.getInstance();
		player = MusicPlayer.getInstance();
		loadContent();
	}
	
	/* (non-Javadoc)
	 * @see framework.core.JITApplet#start()
	 */
	@Override
	public void start() {
		stepper.startGame();
		super.start();
		
	}
	
	@Override
	public void stop() {
		MusicPlayer.getInstance().terminate();
		super.stop();
	}
	
	/**
	 * Load content.
	 */
	public static void loadContent() {

		guiInfoBackground = new InfoBackground(239,0,"resource/tetrisscore.png");
		guiScore = new Score(score);
		guiLevel = new Level();
		MusicPlayer.getInstance().playBackgroundSound();
		
		
	}
}
