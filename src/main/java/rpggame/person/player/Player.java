package rpggame.person.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import rpggame.person.Person;
import rpggame.story.StoryTeller;
import rpggame.utils.EnemyFactory;
import rpggame.weapon.DamageComparator;
import rpggame.weapon.Weapon;
import rpggame.weapon.WeaponType;

public class Player implements Person {

	private String name;
	private int strength;
	private int intelligence;
	private int agility;
	private Weapon weapon;
	private List<Weapon> backPack;
	private int nixonBucks;

	public Player(String name, int strength, int agility, int intelligence) {
		this.name = name;
		this.strength = strength;
		this.agility = agility;
		this.intelligence = intelligence;
		// made the list public and static in EnemyFactory
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

	public void sellWeapon(int index) {
		// adds the damage of the weapon to NixonBucks when sold
		this.nixonBucks = this.nixonBucks + this.backPack.get(index).getDamage();
		// then removes weapon from backpack
		this.backPack.remove(index);
	}

	public boolean checkForWeaponInBackpack(Weapon weapon) {
		boolean alreadyInBackpack = false;
		for (Weapon weaponInList : this.backPack) {
			if (weaponInList.equals(weapon)) {
				alreadyInBackpack = true;
			}
		}
		return alreadyInBackpack;
	}

	public void addWeaponToBackpack(Weapon weapon) {
		this.backPack.add(weapon);
		Collections.sort(this.backPack, new DamageComparator());
	}

	public void printBackpack() {
		// show weapons in backpack
		StoryTeller.printLineBreak();
		System.out.println("Backpack Contents");
		for (int i = 0; i < this.backPack.size(); i++) {
			System.out.println((i + 1) + ": " + this.backPack.get(i));
		}
		StoryTeller.printLineBreak();
	}

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
		return this.strength;
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
