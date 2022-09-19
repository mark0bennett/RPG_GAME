package rpggame.enemy;

import java.util.Random;

import rpggame.person.Person;
import rpggame.weapon.Weapon;
import rpggame.weapon.WeaponType;

public class Enemy extends Person {

	private String name;
	private int strength;
	private int intelligence;
	private int agility;
	private Weapon weapon;

	public Enemy(String name, int strength, int agility, int intelligence, Weapon weapon) {
		this.name = name;
		this.strength = strength;
		this.agility = agility;
		this.intelligence = intelligence;
		this.weapon = weapon;
	}

	@Override
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
		int[] attackDamage = { 0, 0 };

		if (this.weapon.getWeaponType() == WeaponType.AGILITY) {
			attackDamage[0] = this.agility + this.weapon.getDamage();
			attackDamage[1] = random.nextInt(this.intelligence);
		} else if (this.weapon.getWeaponType() == WeaponType.INTELLIGENCE) {
			attackDamage[0] = this.intelligence + this.weapon.getDamage();
			attackDamage[1] = random.nextInt(this.agility);
		}
		return attackDamage;
	}

	public Weapon dropWeapon() {
		return this.weapon;
	}

	public String getName() {
		return this.name;
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

	@Override
	public String toString() {
		return this.name + " - " + "Strength: " + strength + " Agility: " + agility + " Intelligence: " + intelligence
				+ ", Weapon: " + this.weapon.getName();
	}

}
