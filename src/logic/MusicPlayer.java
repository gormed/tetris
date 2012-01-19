package logic;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.Sequence;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {

	private static MusicPlayer instance;

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

	}

	public void playSoundByLineNum(int numOfLines) {
		File soundFile;
		switch (numOfLines) {
		case 1:
			soundFile = new File("resource/media/multikill.wav"); // TODO neuer
																	// sound
																	// einbetten
			break;
		case 2:
			soundFile = new File("resource/media/doublekill.wav");
			break;
		case 3:
			soundFile = new File("resource/media/triplekill.wav");
			break;
		case 4:
			soundFile = new File("resource/media/multikill.wav");
			break;
		default:
			soundFile = new File("resource/media/doublekill.wav");
			break;
		}
		try {
			AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void playSoundGameOver() {
		File soundFile;

		soundFile = new File("resource/media/doublekill.wav"); // TODO Sound einfuegen
		try {
			AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}