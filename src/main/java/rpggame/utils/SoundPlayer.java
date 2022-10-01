package rpggame.utils;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {

	private static Clip clip;

	public static void playSound(String fileName) {
		try {
			File file = new File("src/main/resources/sound/" + fileName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		clip.start();
	}

}
