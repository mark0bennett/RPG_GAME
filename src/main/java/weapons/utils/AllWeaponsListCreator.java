package weapons.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import weapons.Weapon;
import weapons.WeaponObject;
import weapons.WeaponType;

public class AllWeaponsListCreator {

	public static List<Weapon> createListAllWeaponsFromCsvFile() {
		String line = "";
		String splitBy = ",";
		List<Weapon> readWeaponsList = new ArrayList<>();
		try {
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader bufferedReader = new BufferedReader(
					new FileReader(
							"D:\\Java\\Spring Tools Suite\\Projects\\RPG_GAME\\src\\main\\java\\weapons\\AllWeapons.csv"));
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
						weaponType);

				readWeaponsList.add(newWeapon);
			}

			bufferedReader.close();

		} catch (Exception e) {
			System.out.println("\nError reading file...path not valid, or invalid csv format\n");
		}
		return readWeaponsList;
	}

}
