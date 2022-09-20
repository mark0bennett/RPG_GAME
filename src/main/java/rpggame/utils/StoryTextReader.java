package rpggame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import rpggame.story.StoryTeller;

public class StoryTextReader {

	private static Scanner scanner = new Scanner(System.in);

	public static void read(String fileName) {
		String line = "";
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new FileReader(
							"D:\\Java\\Spring Tools Suite\\Projects\\RPG_GAME\\src\\main\\resources\\story\\"
									+ fileName));
			while (!(line = bufferedReader.readLine()).isBlank()) {
				System.out.println(line);
				StoryTeller.nextLine(scanner);
			}
			// same as
//			while (true) {
//				line = bufferedReader.readLine();
//				if (line.isBlank()) {
//					break;
//				} else {
//					System.out.println(line);
//					StoryTeller.nextLine(scanner);
//				}
//			}
			bufferedReader.close();
		} catch (Exception e) {
			System.out.println("\nError reading file...path not valid, or invalid format\n");
		}
	}

}
