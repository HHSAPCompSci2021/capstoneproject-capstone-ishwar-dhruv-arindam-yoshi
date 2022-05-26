package music;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*; 


/**
 * This class represents audio in the program. It plays/pause the soundtracks, and plays soundeffects. 
 * @author Arindam Kulkarni and Dhruv Lohani
 */
public class AudioPlayer {
	/**
	 * file represents the file from which the audio is being played from. 
	 */
	public File file; 
	
	/**
	 * audioStream represents the player which plays the music in the java program. 
	 */
	public AudioInputStream audioStream; 
	
	/**
	 * Clip represents a clip in the java program that plays music. 
	 */
	public Clip clip; 
	
	/**
	 * hasMusicStarted represents whether the music has started playing or not. 
	 */
	public boolean hasMusicStarted; 
	private boolean shouldLoop;
	
	
	/**
	 * Creates an object of the AudioPlayer class.
	 * @param file represents the file from which the music is stored in, and to be played from. 
	 */
	public AudioPlayer(File file, boolean loop) {
		this.file = file; 
		try {
			audioStream = AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}  
		try {
			clip.open(audioStream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		hasMusicStarted = false; 
		shouldLoop = loop;
	}
	
	
	/**
	 * Creates an object of the AudioPlayer class. 
	 * @param s the string which is the path of the file from which the music is going to be played from. 
	 */
	public AudioPlayer(String s, boolean loop) {
		this(new File(s), loop); 
		/*
		this.file = new File(s); 
		try {
			audioStream = AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}  
		*/
	}
	
	/**
	 * Plays the music, and loops it continuously.  
	 */
	public void play() {
		try {
			clip.start();
			if (shouldLoop)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		/*
		try {
			clip.open(audioStream);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
	
	/**
	 * Pauses the music. 
	 */
	public void pause() {
		clip.stop();
	}
	
	/**
	 * Resets the music back to position 0. 
	 */
	public void reset() {
		clip.setMicrosecondPosition(0);
		hasMusicStarted = false;
	}
}
