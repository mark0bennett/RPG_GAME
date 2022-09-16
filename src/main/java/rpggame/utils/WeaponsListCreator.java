package rpggame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import rpggame.weapon.Weapon;
import rpggame.weapon.WeaponObject;
import rpggame.weapon.WeaponType;

public class WeaponsListCreator {

	public static List<Weapon> createListWeaponsFromCsvFile(String fileName) {
		String line = "";
		String splitBy = ",";
		List<Weapon> readWeaponsList = new ArrayList<>();
		try {
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader bufferedReader = new BufferedReader(
					new FileReader(
							"D:\\Java\\Spring Tools Suite\\Projects\\RPG_GAME\\src\\main\\java\\rpggame\\weapon\\"
									+ fileName));
			while ((line = bufferedReader.readLine()) != null)
			// returns a Boolean value
			{
				String[] weapon = line.split(splitBy);
				// use comma as separator
				WeaponType weaponType = WeaponType.INTELLIGENCE;
				if (weapon[4].equals("agility")) {
					weaponType = WeaponType.AGILITY;
				}

				Weapon newWeapon = new WeaponObject(
						weapon[0],
						Integer.parseInt(weapon[1]),
						Integer.parseInt(weapon[2]),
						Integer.parseInt(weapon[3]),
						weaponType,
						Integer.parseInt(weapon[5]));

				readWeaponsList.add(newWeapon);
			}
			bufferedReader.close();

		} catch (Exception e) {
			System.out.println("\nError reading file...path not valid, or invalid csv format\n");
		}
		return readWeaponsList;
	}

}
