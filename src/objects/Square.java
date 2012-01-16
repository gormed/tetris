package objects;

import java.awt.Color;
import java.awt.event.MouseEvent;

import framework.core.Time;

public class Square extends BaseObject {

	public Square() {
		super();
		blockType = BlockType.Square;
		createRaster();
	}

	@Override
	protected void createRaster() {
		raster[0][2] = true;
		raster[1][2] = true;
		raster[0][3] = true;
		raster[1][3] = true;
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (raster[i][j] == true) {
					blocks[i][j] = new FramedRect(i * 20, j * 20, 20, 3, Color.BLUE, Color.CYAN);
					blocks[i][j].makeVisible();
				}
			}
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
