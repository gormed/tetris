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
 * File: BaseObject.java
 * Type: objects.BaseObject
 * 
 * Documentation created: 18.01.2012 - 14:15:36 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package objects;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import logic.FieldCollision;

import framework.core.Time;
import framework.core.UpdateObject;
import framework.objects.CanvasObject;
import framework.objects.Square;

/**
 * The Class BaseObject.
 */
abstract public class BaseObject extends UpdateObject {

	/** The raster. */
	protected boolean[][] raster;

	/** The blocks. */
	protected FramedRect[][] blocks;

	/** The block type. */
	protected BlockType blockType;
	
	/** The position. */
	protected Point position;

	/**
	 * Instantiates a new base object.
	 */
	protected BaseObject() {
		super();
		raster = new boolean[4][4];
		blocks = new FramedRect[4][4];
		position = new Point();
	}

	/**
	 * Creates the raster.
	 */
	protected abstract void createRaster();

	/**
	 * Gets the block type.
	 * 
	 * @return the block type
	 */
	public BlockType getBlockType() {
		return blockType;
	}

	/**
	 * Creates the blocks.
	 */
	protected void createBlocks() {
		for (int i = 0; i < 4; i++) {
			
			
			for (int j = 0; j < 4; j++) {
				if (raster[i][j] == true) {
					blocks[i][j] = new FramedRect(i * 20, j * 20, 20, 3,
							Color.BLUE, Color.CYAN, new Point(i, j));
					blocks[i][j].makeVisible();
				}
			}
		}
	}

	/**
	 * Sets the position.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void setPosition(int x, int y) {
		position = new Point(x, y);
		setBlockPositions();
	}
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Point getPosition() {
		return new Point(position.x, position.y);
	}
	
	public boolean[][] getSubBlockArray() {
		return raster.clone();
	}
	
	/**
	 * Change color.
	 *
	 * @param border the border
	 * @param inner the inner
	 */
	public void changeColor(Color border, Color inner) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (raster[i][j] == true) {
					blocks[i][j].outer.changeColor(border);
					blocks[i][j].inner.changeColor(inner);
				}
			}
		}
	}
	
	/**
	 * Sets the block positions.
	 */
	protected void setBlockPositions() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (raster[i][j] == true) {
					blocks[i][j].setPosition(position.x * 20, position.y * 20);
				}
			}
		}
	}
	
	/**
	 * Rotates the block.
	 *
	 * @param dir the direction to rotate (left = -1, right = 1)
	 * @param value the times to rotate. 1 times means 90 degree, 2 = 180 degree, and so on
	 */
	public abstract void rotate(int dir, int value);
	
	/**
	 * Checks the blocks collision.
	 *
	 * @param collision the collision
	 * @return true, if colliding
	 */
	public int checkCollision(FieldCollision collision, Point position) {
		return collision.checkCollision(raster, position);

	}
	
	/**
	 * The Class FramedRect.
	 */
	class FramedRect extends CanvasObject {

		/** The border. */
		private int border;

		/** The outer square, this is the border. */
		private Square outer;

		/** The inner square is inside the border. */
		private Square inner;

		/** The BaseObject-array x coord. */
		private int arrayX;

		/** The The BaseObject-array y coord. */
		private int arrayY;

		/**
		 * Instantiates a new framed rectangle.
		 *
		 * @param xPos the x pos
		 * @param yPos the y pos
		 * @param size the size
		 * @param border the border
		 * @param outer the outer
		 * @param inner the inner
		 * @param arrayIDX the array idx
		 */
		public FramedRect(int xPos, int yPos, int size, int border,
				Color outer, Color inner, Point arrayIDX) {
			super(xPos, yPos);
			this.border = border;
			this.color = outer;
			arrayX = arrayIDX.x;
			arrayY = arrayIDX.y;

			this.outer = new Square(xPos, yPos, size, outer);
			this.outer.makeVisible();

			this.inner = new Square(xPos + border, yPos + border, size
					- (2 * border), inner);
			this.inner.makeVisible();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see framework.objects.CanvasObject#setPosition(int, int)
		 */
		public void setPosition(int x, int y) {
			this.xPosition = arrayX * 20 + x;
			this.yPosition = arrayY * 20 + y;
			outer.setPosition(xPosition, yPosition);
			inner.setPosition(xPosition + border, yPosition + border);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see framework.objects.CanvasObject#draw()
		 */
		@Override
		public void draw() {
			if (isVisible()) {
				outer.draw();
				inner.draw();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see framework.objects.CanvasObject#erase()
		 */
		@Override
		protected void erase() {
			if (isVisible()) {
				outer.dispose();
				inner.dispose();
			}
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

	}
}
