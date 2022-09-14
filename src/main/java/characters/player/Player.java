package characters.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import weapons.BareHands;
import weapons.Knife;
import weapons.Weapon;
import weapons.WeaponType;

public class Player {

	private int strength;
	private int intelligence;
	private int agility;
	private String name;
	private Weapon weapon;
	private List<Weapon> backPack;

	public Player(String name, int strength, int agility, int intelligence) {
		this.name = name;
		this.strength = strength;
		this.agility = agility;
		this.intelligence = intelligence;
		this.weapon = new BareHands();
		this.backPack = new ArrayList<Weapon>();

		this.backPack.add(this.weapon);
		this.backPack.add(new Knife());
	}

	public int attack() {
		int attackDamage = 0;
		if (this.weapon.getWeaponType() == WeaponType.AGILITY) {
			attackDamage = this.agility + this.weapon.getDamage();
		} else if (this.weapon.getWeaponType() == WeaponType.INTELLIGENCE) {
			attackDamage = this.intelligence + this.weapon.getDamage();
		}
		return attackDamage;
	}

	public int[] attackCrit() {
		Random random = new Random();
		int[] attackDamage = {0,0};

		// note the crit amount when it happens, can be 0 too, can we return an int[] with both numbers?

		// if agi weapon, crit is random between 0 and your int
		// if int weapon crit is random between 0 and your agi
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
	// TODO: sell weapon in backpack for stat points?

	// this is where we could return something so the text is shown from another
	// class
	// TODO: STILL DOUBLE ADDING WEAPONS WHEN THEY ARE ALREADY IN BACKPACK - doing
	// it manually for now
	public void addWeaponToBackpack(Weapon weapon) {
		boolean alreadyInBackpack = false;

		for (Weapon weaponInList : this.backPack) {
			if (weaponInList.toString().equals(weapon.toString())) {
				alreadyInBackpack = true;
			}
		}

		if (!alreadyInBackpack) {
			this.backPack.add(weapon);
			System.out.println("Weapon added to backpack");
		} else {
			System.out.println("You already have this weapon");
		}

		// TODO: yeah equals for WeaponObject not working?
//		if(!this.backPack.contains(weapon)) {
//			this.backPack.add(weapon);
//			System.out.println("Weapon added to your backpack");
//		} else {
//			System.out.println("You already have this weapon");
//		}

	}

	public List<Weapon> getBackPack() {
		return backPack;
	}

	public int getCurrentTotalStatPoints() {
		int currentStatPoints = this.strength + this.agility + this.intelligence;
		return currentStatPoints;
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
				+ ", Weapon: " + weapon;
	}

}
