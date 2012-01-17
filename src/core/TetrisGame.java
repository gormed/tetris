/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Tetris Project (c) 2012 - 2011 by Hans Ferchland & Hady Khalifa
 * 
 * 
 * Tetris is a tetris clone in java using the JIT Framework
 * 
 * 
 * Tetris rights are by its owners/creators (Hans Ferchland & Hady Khalifa). 
 * You have no right to edit, publish and/or deliver the code or application in any way! If that is done by someone, please report it!
 * 
 * Email us: hans.ferchland@gmx.de
 * 
 * Project: Tetris
 * File: TetrisGame.java
 * Type: core.TetrisGame
 * 
 * Documentation created: 17.01.2012 - 19:29:35 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package core;

import logic.GameStepper;
import objects.Square;
import framework.core.Application;


/**
 * The Class TetrisGame.
 */
public class TetrisGame {
	
	private static GameStepper stepper;

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
		
		loadContent();
		
		app.start();
	}
	
	/**
	 * Load content.
	 */
	public static void loadContent() {
//		Square s = new Square();
//		objects.Long l = new objects.Long();
//		l.setPosition(3, 0);
//		
//		stepper.addGameObject(s);
//		stepper.addGameObject(l);
//		
		stepper.start();
		
	}
}
