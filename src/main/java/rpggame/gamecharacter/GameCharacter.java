package rpggame.gamecharacter;

import java.util.Random;

import rpggame.weapon.Weapon;
import rpggame.weapon.WeaponType;

public abstract class GameCharacter {

	private String name;
	private int strength;
	private int agility;
	private int intelligence;
	private Weapon weapon;

	public GameCharacter(String name, int strength, int agility, int intelligence, Weapon weapon) {
		this.name = name;
		this.strength = strength;
		this.agility = agility;
		this.intelligence = intelligence;
		this.weapon = weapon;
	}

	public AttackDamage attack() {
		int baseAttackDamage = 0;
		int critAttackDamage = 0;
		Random random = new Random();

		if (this.weapon.getWeaponType() == WeaponType.AGILITY) {
			baseAttackDamage = this.agility + this.weapon.getDamage();
			critAttackDamage = random.nextInt(this.intelligence);
		} else if (this.weapon.getWeaponType() == WeaponType.INTELLIGENCE) {
			baseAttackDamage = this.intelligence + this.weapon.getDamage();
			critAttackDamage = random.nextInt(this.agility);
		}

		return new AttackDamage(baseAttackDamage, critAttackDamage);

	}

	public String getName() {
		return this.name;
	}

	public int getStrength() {
		return this.strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getAgility() {
		return this.agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

}
