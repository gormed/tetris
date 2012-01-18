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
 * Documentation created: 18.01.2012 - 16:50:21 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package core;

import java.awt.Font;

import logic.GameScore;
import logic.GameStepper;
import objects.Square;
import framework.core.Application;
import gui.Score;


/**
 * The Class TetrisGame.
 */
public class TetrisGame {
	
	/** The stepper. */
	private static GameStepper stepper;
	
	/** The score. */
	private static GameScore score;
	
	/** The gui score. */
	private static Score guiScore;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Application app = Application.getInstance();
		
		app.setDimensions(400, 600);
		app.setTitle("Tetris");
		
		stepper = GameStepper.getInstance();
		score = GameScore.getInstance();
		
		loadContent();
		
		app.start();
	}
	
	/**
	 * Load content.
	 */
	public static void loadContent() {

		Font f = new Font("Tahoma", Font.BOLD, 14);
		
		guiScore = new Score(score, f);
		
		stepper.start();
		
	}
}
