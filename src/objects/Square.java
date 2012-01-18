/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Tetris Project (c) 2011 - 2012 by Hans Ferchland & Hady Khalifa
 * 
 * 
 * Tetris is a tetris clone in java using the JIT Framework
 * 
 * 
 * Tetris rights are by its owners/creators 
 * (Hans Ferchland & Hady Khalifa). You have no right to edit, 
 * publish and/or deliver the code or application in any way! 
 * 
 * If that is done by someone, please report it!
 * 
 * Email us: hans.ferchland@gmx.de
 * 
 * Project: Tetris
 * File: Square.java
 * Type: objects.Square
 * 
 * Documentation created: 18.01.2012 - 01:19:21 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package objects;

import java.awt.Color;
import java.awt.event.MouseEvent;

import framework.core.Time;

/**
 * The Class Square.
 */

public class Square extends BaseObject {

	/**
	 * Instantiates a new square.
	 */
	public Square() {
		super();
		blockType = BlockType.Square;
		createRaster();
		changeColor(Color.gray, Color.lightGray);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.BaseObject#createRaster()
	 */
	@Override
	protected void createRaster() {
		raster[1][1] = true;
		raster[2][1] = true;
		raster[1][2] = true;
		raster[2][2] = true;

		createBlocks();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.core.UpdateObject#update(framework.core.Time)
	 */
	@Override
	public void update(Time time) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.core.UpdateObject#onClick(java.awt.event.MouseEvent)
	 */
	@Override
	public void onClick(MouseEvent event) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.core.UpdateObject#onRelease(java.awt.event.MouseEvent)
	 */
	@Override
	public void onRelease(MouseEvent event) {

	}

	@Override
	protected void createTempRaster() {
		tempRaster = raster;

	}

}
