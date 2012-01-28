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
 * Documentation created: 28.01.2012 - 14:38:47 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package logic;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import framework.core.Application;
import framework.core.JITApplet;
import framework.core.TimedEvent;
import framework.events.TimedControl;

/**
 * The Class MusicPlayer.
 */
public class MusicPlayer implements TimedControl {

	/** The instance. */
	private static MusicPlayer instance;

	/** The files. */
	private static ArrayList<File> files;

	/** The urls. */
	private static ArrayList<URL> urls;

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
	
	private ArrayList<Clip> runningClips;

	/** The is applet flag. */
	private boolean isApplet = false;

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
		urls = new ArrayList<URL>();
		runningClips = new ArrayList<Clip>();
		
		JITApplet applet = Application.getInstance().getApplet();
		if (applet != null) {
			URL codebase = applet.getCodeBase();
			addURL(LEVELUP, "resource/media/levelup.wav", codebase);
			addURL(1, "resource/media/zap.wav", codebase);
			addURL(2, "resource/media/doublekill.wav", codebase);
			addURL(3, "resource/media/triplekill.wav", codebase);
			addURL(4, "resource/media/multikill.wav", codebase);
			addURL(GAMEOVER, "resource/media/gameover.wav", codebase);
			addURL(BACKGROUND, "resource/media/background.wav", codebase);
			addURL(DOWN, "resource/media/down.wav", codebase);
			addURL(MOVE, "resource/media/movehor.wav", codebase);
			addURL(TURN, "resource/media/turn.wav", codebase);
			isApplet = true;
		} else {
			addFile(LEVELUP, "resource/media/levelup.wav");
			addFile(1, "resource/media/zap.wav");
			addFile(2, "resource/media/doublekill.wav");
			addFile(3, "resource/media/triplekill.wav");
			addFile(4, "resource/media/multikill.wav");
			addFile(GAMEOVER, "resource/media/gameover.wav");
			addFile(BACKGROUND, "resource/media/background.wav");
			addFile(DOWN, "resource/media/down.wav");
			addFile(MOVE, "resource/media/movehor.wav");
			addFile(TURN, "resource/media/turn.wav");
			isApplet = false;
		}
		Application.getInstance().addTimedObject(this);
	}

	/**
	 * Adds the file.
	 * 
	 * @param id
	 *            the id
	 * @param path
	 *            the path
	 */
	private void addFile(int id, String path) {
		try {
			files.add(id, new File(path));
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.err.println("Wrong audio-file path or error reading file!");
		}
	}

	/**
	 * Adds the url.
	 * 
	 * @param id
	 *            the id
	 * @param path
	 *            the path
	 * @param codebase
	 *            the codebase
	 */
	private void addURL(int id, String path, URL codebase) {
		try {
			urls.add(id, new URL(codebase, path));
		} catch (MalformedURLException e) {
			System.err.println("Wrong audio-URL!");
			e.printStackTrace();
		}
	}

	/**
	 * Play sound.
	 * 
	 * @param id
	 *            the num of lines
	 * @param volume
	 *            the volume
	 */
	public void playSound(int id, float volume) {
		try {
			AudioInputStream audioIn;
			if (isApplet) {
				audioIn = AudioSystem.getAudioInputStream(urls.get(id));
			} else {
				audioIn = AudioSystem.getAudioInputStream(files.get(id));
			}

			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			FloatControl gainControl = (FloatControl) clip
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);
			clip.start();
			runningClips.add(clip);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			System.err
					.println("A file-line could not be opened due to resource restrictions!");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("I/O error during reading of the stream!");
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
			System.err.println("Error playing sound: " + id + "/"
					+ urls.get(id) != null ? urls.get(id).toString()
					: " URL not found!");
		}

	}

	/**
	 * Play background sound.
	 */
	public void playBackgroundSound() {
		try {
			AudioInputStream audioIn;
			if (isApplet) {
				audioIn = AudioSystem.getAudioInputStream(urls.get(BACKGROUND));
			} else {
				audioIn = AudioSystem
						.getAudioInputStream(files.get(BACKGROUND));
			}

			backGroundClip = AudioSystem.getClip();
			backGroundClip.open(audioIn);
			// reduce volume
			FloatControl gainControl = (FloatControl) backGroundClip
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);
			backGroundClip.loop(42);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			System.err
					.println("A file-line could not be opened due to resource restrictions!");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("I/O error during reading of the stream!");
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
			System.err.println("Error playing sound: " + BACKGROUND + "/"
					+ urls.get(BACKGROUND) != null ? urls.get(BACKGROUND).toString()
					: " URL not found!");
		}
	}

	/**
	 * Start bg sound.
	 */
	public void startBGSound() {
		if (backGroundClip != null) {
			FloatControl gainControl = (FloatControl) backGroundClip
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);
		} else {
			System.err.println("No background clip to start!");
		}
	}

	/**
	 * Stop bg sound.
	 */
	public void stopBGSound() {
		if (backGroundClip != null) {
			FloatControl gainControl = (FloatControl) backGroundClip
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-30.0f);
		} else {
			System.err.println("No background clip to stop!");
		}

	}

	/**
	 * Disposes the music player, all resources are freed and the instance is nulled.
	 */
	public void terminate() {
		Application.getInstance().removeTimedObject(this);
		backGroundClip.close();
		instance = null;
		for (Clip c : runningClips) {
			try {
				c.stop();
				c.close();
			} catch (SecurityException e) {
				e.printStackTrace();
				System.err.println("Error closing clip!");
			}
			
			
		}
		runningClips.clear();
		runningClips = null;
		files.clear();
		files = null;
		urls.clear();
		urls = null;
	}

	/**
	 * On timed event.
	 *
	 * @param t the t
	 */
	@Override
	public void onTimedEvent(TimedEvent t) {
		@SuppressWarnings("unchecked")
		ArrayList<Clip> clipsClone = (ArrayList<Clip>) runningClips.clone();
		
		for (Clip c : clipsClone) {
			if (!c.isRunning()) {
				c.stop();
				c.close();
				runningClips.remove(c);
			}
		}
	}

	/**
	 * Gets the period.
	 *
	 * @return the period
	 */
	@Override
	public long getPeriod() {
		
		return 2000;
	}
	
}