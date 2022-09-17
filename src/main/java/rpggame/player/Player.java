package rpggame.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rpggame.utils.EnemyFactory;
import rpggame.weapon.Weapon;
import rpggame.weapon.WeaponType;

public class Player {

	private int strength;
	private int intelligence;
	private int agility;
	private String name;
	private Weapon weapon;
	private List<Weapon> backPack;
	private int nixonBucks;

	public Player(String name, int strength, int agility, int intelligence) {
		this.name = name;
		this.strength = strength;
		this.agility = agility;
		this.intelligence = intelligence;
		//made the list public and static in EnemyFactory
		this.weapon = EnemyFactory.weaponList.get(0);
		this.backPack = new ArrayList<Weapon>();
		this.backPack.add(this.weapon);
		this.backPack.add(EnemyFactory.weaponList.get(1));
		this.nixonBucks = 0;
	}

	public int getAttackDamage() {
		int attackDamage = 0;
		if (this.weapon.getWeaponType() == WeaponType.AGILITY) {
			attackDamage = this.agility + this.weapon.getDamage();
		} else if (this.weapon.getWeaponType() == WeaponType.INTELLIGENCE) {
			attackDamage = this.intelligence + this.weapon.getDamage();
		}
		return attackDamage;
	}

	public int[] getAttackCritDamage() {
		Random random = new Random();
		int[] attackDamage = new int[2];

		if (this.weapon.getWeaponType() == WeaponType.AGILITY) {
			attackDamage[0] = this.agility + this.weapon.getDamage();
			attackDamage[1] = random.nextInt(this.intelligence);
		} else if (this.weapon.getWeaponType() == WeaponType.INTELLIGENCE) {
			attackDamage[0] = this.intelligence + this.weapon.getDamage();
			attackDamage[1] = random.nextInt(this.agility);
		}
		return attackDamage;
	}

	// TODO: delete weapon from backpack
	// TODO: sell weapon in backpack

	// TODO: STILL DOUBLE ADDING WEAPONS WHEN THEY ARE ALREADY IN BACKPACK - doing
	// it manually for now - need Comparator or Comparable for Weapons
	// TODO: when we add a weapon add it so its in order of damage
	public boolean checkForWeaponInBackpack(Weapon weapon) {
		boolean alreadyInBackpack = false;

		for (Weapon weaponInList : this.backPack) {
			if (weaponInList.toString().equals(weapon.toString())) {
				alreadyInBackpack = true;
			}
		}
		return alreadyInBackpack;
	}

	public void addWeaponToBackpack(Weapon weapon) {
		this.backPack.add(weapon);
	}

	public void printBackpack() {
		// show weapons in backpack
		System.out.println("Backpack Contents");
		for (int i = 0; i < this.backPack.size(); i++) {
			System.out.println((i + 1) + ": " + this.backPack.get(i));
		}
	}

	// TODO: Sort the backpack with Dmg when printed out, highest to lowest
	public List<Weapon> getBackPack() {
		return backPack;
	}

	public int getCurrentTotalStatPoints() {
		int currentStatPoints = this.strength + this.agility + this.intelligence;
		return currentStatPoints;
	}

	public int getNixonBucks() {
		return nixonBucks;
	}

	public void setNixonBucks(int nixonBucks) {
		this.nixonBucks = nixonBucks;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	@Override
	public String toString() {
		return name + " - Strength: " + strength + ", Agility: " + agility + ", Intelligence: " + intelligence
				+ ", Weapon: " + this.weapon.getName() + ", NixonBucks: " + this.nixonBucks;
	}

}
