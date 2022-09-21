package rpggame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import rpggame.story.StoryTellerService;

public class StoryTextReader {

	private static Scanner scanner = new Scanner(System.in);

	public static void read(String fileName) {
		String line = "";
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new FileReader(
							"D:\\Java\\Spring Tools Suite\\Projects\\RPG_GAME\\src\\main\\resources\\story\\"
									+ fileName));
			// can now recognise calls to AsciiArtReader, SoundPlayer and printBreakLine();
			while ((line = bufferedReader.readLine()) != null) {
				if (line.equals("StoryTellerService.printLineBreak();")) {
					StoryTellerService.printLineBreak();
					StoryTellerService.nextLine(scanner);
				} else if (line.substring(0, 14).equals("AsciiArtReader")) {
					String asciiFileName = line.substring(24, (line.length() - 3));
					AsciiArtReader.readArt(asciiFileName);
					StoryTellerService.nextLine(scanner);
				} else if (line.substring(0, 11).equals("SoundPlayer")) {
					String soundFileName = line.substring(23, (line.length() - 3));
					SoundPlayer.playSound(soundFileName);
				} else {
					System.out.println(line);
					StoryTellerService.nextLine(scanner);
				}
			}
			bufferedReader.close();
		} catch (Exception e) {
			// TODO: We are catching a NullPointerException here, so this is just cheating
			// to comment it out
			System.out.println("\nError reading file...path not valid, or invalid format\n");
		}
	}

}
