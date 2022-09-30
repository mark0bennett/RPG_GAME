package rpggame.utils;

import java.io.BufferedReader;
import java.io.FileReader;

public class AsciiArtReader {

	public static void readArt(String fileName) {
		String line = "";
		try (BufferedReader bufferedReader = new BufferedReader(
				new FileReader(
						"D:\\Java\\Spring Tools Suite\\Projects\\RPG_GAME\\src\\main\\resources\\asciiart\\"
								+ fileName))) {
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			System.out.println("\nError reading file...path not valid, or invalid ascii format\n");
		}
	}

}
