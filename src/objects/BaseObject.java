package objects;

import java.awt.Color;
import java.awt.event.MouseEvent;

import framework.core.Application;
import framework.core.Time;
import framework.core.UpdateObject;
import framework.objects.CanvasObject;
import framework.objects.Square;

public abstract class BaseObject extends UpdateObject {

	protected boolean[][] raster;

	protected FramedRect[][] blocks;
	
	protected BlockType blockType;

	public BaseObject() {
		super();
		raster = new boolean[2][4];
		blocks = new FramedRect[2][4];
	}

	protected abstract void createRaster();

	protected void drawBlock(int i, int j) {
		blocks[i][j].draw();
	}
	
	public BlockType getBlockType() {
		return blockType;
	}

	class FramedRect extends CanvasObject {

		private int border;

		private Square outer;
		private Square inner;

		public FramedRect(int xPos, int yPos, int size, int border,
				Color outer, Color inner) {
			super(xPos, yPos);
			this.border = border;
			this.color = outer;

			this.outer = new Square(xPos, yPos, size, outer);
			this.outer.makeVisible();

			this.inner = new Square(xPos + border, yPos + border, size
					- (2 * border), inner);
			this.inner.makeVisible();
		}

		@Override
		public void draw() {
			if (isVisible()) {
				outer.draw();
				inner.draw();
			}
		}

		@Override
		protected void erase() {
			if (isVisible()) {
				outer.dispose();
				inner.dispose();
			}
		}

		@Override
		public void update(Time time) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onClick(MouseEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onRelease(MouseEvent event) {
			// TODO Auto-generated method stub

		}

	}
}
