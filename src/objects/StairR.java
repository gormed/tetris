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
 * File: StairR.java
 * Type: objects.StairR
 * 
 * Documentation created: 18.01.2012 - 21:25:40 by khalifa
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package objects;

import java.awt.Color;
import java.awt.event.MouseEvent;

import framework.core.Time;

/**
 * The Class Long.
 */
public class StairR extends BaseObject {

	/**
	 * Instantiates a new long.
	 */
	public StairR() {
		super();
		blockType = BlockType.StairR;
		createRaster();
		changeColor(Color.lightGray, Color.white);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.BaseObject#createRaster()
	 */
	@Override
	protected void createRaster() {
		raster[0][1] = true;
		raster[1][1] = true;
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

	/* (non-Javadoc)
	 * @see objects.BaseObject#createTempRaster()
	 */
	protected void createTempRaster() {
		boolean[][] newRaster = new boolean[4][4];
		switch (direction) {
		case 0:
		case 2:
			newRaster[1][1] = true;
			newRaster[1][2] = true;
			newRaster[2][0] = true;
			newRaster[2][1] = true;
			break;

		case 1:
		case 3:
			newRaster[0][1] = true;
			newRaster[1][1] = true;
			newRaster[1][2] = true;
			newRaster[2][2] = true;
			break;
		}
		tempRaster = newRaster;

	}

}