/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Tetris Project (c) 2011 - 2012 by Hans Ferchland & Hady Khalifa
 * 
 * 
 * Tetris is a tetris clone in java using the JIT Framework.
 * The project was created for educational purposes and may be used under the GNU 
 * Public license only.
 *
 * If you modify it please let other people have part of it!
 *
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * GNU Public License
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License 3 as published by
 * the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 
 * Email us: hans.ferchland@gmx.de
 * 
 * Project: Tetris
 * File: InfoBackground.java
 * Type: gui.InfoBackground
 * 
 * Documentation created: 29.01.2012 - 23:07:25 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package gui;

import framework.core.Application;
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
		Application.getInstance().removeUpdateObject(this);
		this.makeVisible();
	}
	
	/* (non-Javadoc)
	 * @see framework.objects.Picture#dispose()
	 */
	@Override
	public boolean dispose() {
		this.makeInvisible();
		return super.dispose();
	}

}
