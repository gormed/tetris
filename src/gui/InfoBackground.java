package gui;

import java.awt.Image;

import framework.objects.Picture;
public class InfoBackground extends Picture{

	public InfoBackground(int xPos, int yPos,String imagePath) {
		super(xPos,yPos,imagePath);
		this.makeVisible();
	}

}
