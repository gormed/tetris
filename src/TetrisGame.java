import core.Application;


public class TetrisGame {

	public static void main(String[] args) {
		Application app = Application.getInstance();
		
		app.setDimensions(400, 600);
		app.setTitle("Tetris");
		
		loadContent();
		
		app.start();
	}
	
	public static void loadContent() {
		
	}
}
