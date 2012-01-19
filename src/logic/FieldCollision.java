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
 * Documentation created: 19.01.2012 - 16:34:21 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package logic;

import java.awt.Point;
import java.util.ArrayList;

import objects.BaseObject;
import objects.BaseObject.FramedRect;

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
		graphicsArray = new FramedRect[GAME_WIDTH][GAME_HEIGHT];
	}

	/** The Constant GAME_WIDTH. */
	public static final int GAME_WIDTH = 12;

	/** The Constant GAME_HEIGHT. */
	public static final int GAME_HEIGHT = 30;

	/** The Constant GAME_OVER_LINE. */
	public static final int GAME_OVER_LINE = 4;

	/** The game array. */
	private int[][] gameArray;

	/** The graphics array. */
	private FramedRect[][] graphicsArray;

	/**
	 * Sets the collision field x,y pair to a given value.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param value
	 *            the value
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
	 * Resets the game field array.
	 */
	void resetField() {
		gameArray = new int[GAME_WIDTH][GAME_HEIGHT];
		// TODO: dispose all Rects in the graphics array
		graphicsArray = new FramedRect[GAME_WIDTH][GAME_HEIGHT];
	}

	// ========================================================================
	// Block Collision Checks
	// ========================================================================

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
	 * @param obj
	 *            the obj
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

		FramedRect[] inactiveSubBlocks = obj.makeInactive();
		int i = 0;

		for (FramedRect f : inactiveSubBlocks) {
			p = obj.getSubBlockPosition(i++);
			graphicsArray[p.x][p.y] = f;
			f.setArrayCoord(p.x, p.y);
		}
	}

	// ========================================================================
	// Line Removal
	// ========================================================================

	/**
	 * Checks the lines of the game-field and removes full lines from the array.
	 * 
	 * @return the number of removed lines
	 */
	int checkLines() {

		boolean lineFull = false;
		ArrayList<Integer> linesFull = new ArrayList<Integer>();
		// int[] linesFull = new int[4];

		int linesRemoved = 0;
		int linesFilled = 0;

		for (int j = GAME_HEIGHT - 1; j > 4; j--) {
			for (int i = 0; i < GAME_WIDTH; i++) {
				if (gameArray[i][j] == 1) {
					lineFull = true;
				} else {
					lineFull = false;
					break;
				}
			}
			if (lineFull) {
				
				for (int i = 0; i < GAME_WIDTH; i++) {
					gameArray[i][j] = 0;
					graphicsArray[i][j].makeInvisible();
					graphicsArray[i][j].dispose();
					graphicsArray[i][j] = null;
				}
				
				linesFull.add(linesFilled, j);
				linesFilled++;
			}
		}

		linesRemoved = linesFilled;

		for (linesFilled = 0; linesFilled < linesRemoved; linesFilled++) {
			for (int j = linesFull.get(linesFilled) + linesFilled; j >= GAME_OVER_LINE; j--) {
				for (int i = 0; (i < GAME_WIDTH && j > 0 && j < GAME_HEIGHT); i++) {
					gameArray[i][j] = gameArray[i][j - 1];
					gameArray[i][j - 1] = 0;
					if (graphicsArray[i][j - 1] != null) {
						graphicsArray[i][j] = graphicsArray[i][j - 1];
						graphicsArray[i][j].setArrayCoord(i, j);
						graphicsArray[i][j].setPosition(0, 0);
						graphicsArray[i][j - 1] = null;
					}
				}
			}
			@SuppressWarnings("unused")
			int k = 0;
		}
		return linesFull.size();
	}

	// ========================================================================
	// Loose Condition
	// ========================================================================

	/**
	 * Checks if is game over.
	 *
	 * @return true, if is game over
	 */
	boolean isGameOver() {

		for (int i = 0; i < GAME_WIDTH; i++) {
			if (gameArray[i][GAME_OVER_LINE] == 1) {
				MusicPlayer.getInstance().playSound(MusicPlayer.GAMEOVER, 0.0f);
				return true;
			}
		}
		return false;
	}

}
