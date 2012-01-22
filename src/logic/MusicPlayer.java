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
 * File: MusicPlayer.java
 * Type: logic.MusicPlayer
 * 
 * Documentation created: 22.01.2012 - 18:24:17 by Hans
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

	/** The Constant BACKGROUND. */
	static final int BACKGROUND = 6;

	/** The Constant DOWN. */
	static final int DOWN = 7;
	
	/** The Constant MOVE. */
	static final int MOVE = 8;
	
	/** The Constant TURN. */
	static final int TURN = 9;
	
	/** The back ground clip. */
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
	 * @param numOfLines the num of lines
	 * @param volume the volume
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

	/**
	 * Play background sound.
	 */
	public void playBackgroundSound() {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(files
					.get(BACKGROUND));
			backGroundClip = AudioSystem.getClip();
			backGroundClip.open(audioIn);
			//reduce volume
			FloatControl gainControl = (FloatControl) backGroundClip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);
			backGroundClip.loop(42);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Start bg sound.
	 */
	public void startBGSound(){
		FloatControl gainControl = (FloatControl) backGroundClip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-10.0f);
	}
	
	/**
	 * Stop bg sound.
	 */
	public void stopBGSound(){
		FloatControl gainControl = (FloatControl) backGroundClip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-40.0f);
	}


}