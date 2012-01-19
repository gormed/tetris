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
 * File: InfoBackground.java
 * Type: gui.InfoBackground
 * 
 * Documentation created: 19.01.2012 - 16:34:22 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package gui;

import framework.objects.Picture;

/**
 * The Class InfoBackground.
 */
public class InfoBackground extends Picture{

	/**
	 * Instantiates a new info background.
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 * @param imagePath the image path
	 */
	public InfoBackground(int xPos, int yPos,String imagePath) {
		super(xPos,yPos,imagePath);
		this.makeVisible();
	}

}
