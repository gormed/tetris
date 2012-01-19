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
 * File: MusicPlayer.java
 * Type: logic.MusicPlayer
 * 
 * Documentation created: 19.01.2012 - 17:56:27 by khalifa
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The Class MusicPlayer.
 */
public class MusicPlayer {

	/** The instance. */
	private static MusicPlayer instance;

	/** The files. */
	private static ArrayList<File> files;

	/** The Constant GAMEOVER. */
	static final int GAMEOVER = 5;

	/** The Constant LEVELUP. */
	static final int LEVELUP = 0;

	static final int BACKGROUND = 6;

	static final int DOWN = 7;
	
	static final int MOVE = 8;
	
	static final int TURN = 9;
	
	private Clip backGroundClip;
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
		files.add(BACKGROUND, new File("resource/media/background.wav"));
		files.add(DOWN, new File("resource/media/down.wav"));
		files.add(MOVE, new File("resource/media/movehor.wav"));
		files.add(TURN, new File("resource/media/turn.wav"));
	}

	/**
	 * Play sound.
	 * 
	 * @param numOfLines
	 *            the num of lines
	 */
	public void playSound(int numOfLines, float volume) {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(files
					.get(numOfLines));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		}

	}

	public void playBackgroundSound() {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(files
					.get(BACKGROUND));
			backGroundClip = AudioSystem.getClip();
			backGroundClip.open(audioIn);
			//reduce volume
			FloatControl gainControl = (FloatControl) backGroundClip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);
			startBGSound();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		}
	}
	public void startBGSound(){
		backGroundClip.start();
		backGroundClip.loop(42);
	}
	public void stopBGSound(){
		backGroundClip.stop();
	}


}