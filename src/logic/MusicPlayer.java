package logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {

	private static MusicPlayer instance;

	private static ArrayList<File> files;

	static final int GAMEOVER = 5;
	
	static final int LEVELUP = 0;

	/**
	 * Gets the single instance of MusicPlayer.
	 * 
	 * @return single instance of MusicPlayer
	 */
	public static MusicPlayer getInstance() {
		if (instance != null)
			return instance;
		else
			return instance = new MusicPlayer();
	}

	/**
	 * Instantiates a new game stepper.
	 */
	private MusicPlayer() {
		files = new ArrayList<File>();
		files.add(LEVELUP, new File("resource/media/levelup.wav"));
		files.add(1, new File("resource/media/zap.wav")); 
		files.add(2, new File("resource/media/doublekill.wav"));
		files.add(3, new File("resource/media/triplekill.wav"));
		files.add(4, new File("resource/media/multikill.wav"));
		files.add(GAMEOVER, new File("resource/media/gameover.wav"));
																		
	}

	public void playSound(int numOfLines) {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(files.get(numOfLines));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		}

	}

}