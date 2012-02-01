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
 * Documentation created: 01.02.2012 - 00:05:49 by Hans
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package logic;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
import framework.core.Time;
import framework.events.KeyboardControl;
import framework.objects.base.AbstractPicture;

/**
 * The Class MusicPlayer.
 */
public class MusicPlayer implements KeyboardControl {

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

	/** The game sounds. */
	private ArrayList<Clip> gameSounds;

	/** The is applet flag. */
	private boolean isApplet = false;

	/** The mute button. */
	private AbstractPicture muteButton;

	/** The unmute button. */
	private AbstractPicture noMuteButton;

	/** The global mute flag. */
	private boolean globalMute = false;

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
		gameSounds = new ArrayList<Clip>();

		muteButton = new AbstractPicture(5, 5, "resource/mute.png") {

			@Override
			public void update(Time time) {
				Application.getInstance().removeUpdateObject(this);
			}

			@Override
			public void onRelease(MouseEvent event) {
				toggleMute();
			}

			@Override
			public void onClick(MouseEvent event) {
			}
		};

		noMuteButton = new AbstractPicture(5, 5, "resource/nomute.png") {

			@Override
			public void update(Time time) {
				Application.getInstance().removeUpdateObject(this);
			}

			@Override
			public void onRelease(MouseEvent event) {
				toggleMute();
			}

			@Override
			public void onClick(MouseEvent event) {
			}
		};
		Application.getInstance().removeMouseControl(muteButton);
		noMuteButton.makeVisible();

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
		loadAllClips();
		Application.getInstance().addKeyboardControl(this);
	}

	/**
	 * Loads all clips.
	 */
	private void loadAllClips() {
		loadClip(LEVELUP, 0);
		loadClip(1, -5);
		loadClip(2, -5);
		loadClip(3, -5);
		loadClip(4, -5);
		loadClip(GAMEOVER, 0);
		loadClip(BACKGROUND, -10);
		loadClip(DOWN, -10);
		loadClip(MOVE, -12);
		loadClip(TURN, -5);
	}

	/**
	 * Load a single clip.
	 * 
	 * @param id
	 *            the id
	 * @param volume
	 *            the volume
	 */
	private void loadClip(int id, float volume) {
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
			gameSounds.add(id, clip);
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
	 */
	public void playSound(int id) {
		if (id < gameSounds.size()) {
			Clip sound = gameSounds.get(id);
			if (sound != null && !globalMute) {
				sound.stop();
				sound.setFramePosition(0);
				sound.start();
			}
		}
	}

	/**
	 * Play background sound.
	 * 
	 * @param volume
	 *            the volume
	 */
	public void playBackgroundSound(float volume) {
		if (BACKGROUND < gameSounds.size()) {
			Clip sound = gameSounds.get(BACKGROUND);
			if (sound != null && !globalMute) {
				FloatControl gainControl = (FloatControl) sound
						.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(volume);
				sound.start();
				sound.loop(42);
			}
		}
	}

	/**
	 * Stop bg sound.
	 */
	public void stopBackgroundSound() {
		if (BACKGROUND < gameSounds.size()) {
			Clip sound = gameSounds.get(BACKGROUND);
			if (sound != null) {
				sound.stop();
			}
		}
	}

	/**
	 * Disposes the music player, all resources are freed and the instance is
	 * nulled.
	 */
	public void terminate() {
		Application.getInstance().removeKeyboardControl(this);
		muteButton.dispose();
		noMuteButton.dispose();

		instance = null;
		for (Clip c : gameSounds) {
			try {
				c.stop();
				c.close();
			} catch (SecurityException e) {
				e.printStackTrace();
				System.err.println("Error closing clip!");
			}

		}
		gameSounds.clear();
		gameSounds = null;
		files.clear();
		files = null;
		urls.clear();
		urls = null;
	}

	/**
	 * Sets the mute.
	 * 
	 * @param value
	 *            the new mute
	 */
	private void setMute(boolean value) {
		globalMute = value;
		if (globalMute) {
			stopBackgroundSound();
			muteButton.makeVisible();
			noMuteButton.makeInvisible();
			Application.getInstance().removeMouseControl(noMuteButton);
			Application.getInstance().addMouseControl(muteButton);
		} else {
			playBackgroundSound(-10);
			muteButton.makeInvisible();
			noMuteButton.makeVisible();
			Application.getInstance().removeMouseControl(muteButton);
			Application.getInstance().addMouseControl(noMuteButton);
		}
	}

	/**
	 * Toggle mute.
	 */
	private void toggleMute() {
		setMute(!globalMute);
	}

	// =======================================================================
	// Keyboard-control for sound
	// =======================================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.events.KeyboardControl#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent event) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * framework.events.KeyboardControl#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_M) {
			toggleMute();
		}
		event.consume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.events.KeyboardControl#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent event) {

	}

}