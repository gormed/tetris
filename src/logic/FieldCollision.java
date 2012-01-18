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
 * File: FieldCollision.java
 * Type: logic.FieldCollision
 * 
 * Documentation created: 18.01.2012 - 16:13:32 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package logic;

import java.awt.Point;

import objects.BaseObject;

/**
 * The Class FieldCollision.
 */
public class FieldCollision {

	/** The instance. */
	private static FieldCollision instance;

	/**
	 * Gets the single instance of FieldCollision.
	 * 
	 * @return single instance of FieldCollision
	 */
	public static FieldCollision getInstance() {
		if (instance != null)
			return instance;
		else
			return instance = new FieldCollision();
	}

	/**
	 * Instantiates a new field collision.
	 */
	private FieldCollision() {
		gameArray = new int[GAME_WIDTH][GAME_HEIGHT];
	}

	/** The Constant GAME_WIDTH. */
	private static final int GAME_WIDTH = 20;

	/** The Constant GAME_HEIGHT. */
	private static final int GAME_HEIGHT = 30;

	/** The game array. */
	private int[][] gameArray;

	/**
	 * Sets the collision field x,y pair to a given value.
	 *
	 * @param x the x
	 * @param y the y
	 * @param value the value
	 */
	void setCollisionField(int x, int y, int value) {
		gameArray[x][y] = value;
	}

	/**
	 * Gets the collision field value on given x,y pair.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return the value
	 */
	int getCollisionField(int x, int y) {
		try {
			return gameArray[x][y];
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Check collision for a specific block.
	 * 
	 * @param block
	 *            the block
	 * @param position
	 *            the position
	 * @return the int
	 */
	public int checkCollision(boolean[][] block, Point position) {

		Point[] blockPos = new Point[4];
		int u = 0;

		int checkValue = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (block[i][j] == true) {
					checkValue = checkSubBlockCollision(position.x + i,
							position.y + j);

					if (checkValue == 0) {
						blockPos[u++] = new Point(position.x + i, position.y
								+ j);
					} else {
						return checkValue;
					}
				}
			}
		}

		if (checkValue == 0 && u == 4) {
			for (u = 0; u < 4; u++)
				gameArray[blockPos[u].x][blockPos[u].y] = 2;
		}

		return 0;
	}

	/**
	 * Check sub block collision.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @return the int
	 */
	private int checkSubBlockCollision(int x, int y) {
		if ((x < 0) || (x >= GAME_WIDTH))
			return 2;
		if ((y < 0) || (y >= GAME_HEIGHT))
			return 1;
		else if (getCollisionField(x, y) == 1)
			return 1;
		else
			return 0;
	}

	/**
	 * Adds the inactive block.
	 *
	 * @param obj the obj
	 */
	void addInactiveBlock(BaseObject obj) {
		Point p = obj.getPosition();

		boolean[][] subBlocks = obj.getSubBlockArray();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (subBlocks[i][j] == true) {
					gameArray[p.x + i][p.y + j] = 1;
				}
			}
		}
	}

}
