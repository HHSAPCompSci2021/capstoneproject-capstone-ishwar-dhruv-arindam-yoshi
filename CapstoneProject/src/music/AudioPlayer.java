package music;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*; 

public class AudioPlayer {
	File file; 
	AudioInputStream audioStream; 
	Clip clip; 
	
	public boolean hasMusicStarted = false; 
	
	public AudioPlayer(File file) {
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
	}
	
	public AudioPlayer(String s) {
		File music = new File(s); 
		this.file = music; 
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
	}
	
	public void play() {
		try {
			clip.open(audioStream);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void pause() {
		clip.stop();
	}
	
	public void reset() {
		clip.setMicrosecondPosition(0);
	}
}
