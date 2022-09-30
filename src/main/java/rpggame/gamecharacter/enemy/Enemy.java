package rpggame.gamecharacter.enemy;

import rpggame.gamecharacter.GameCharacter;
import rpggame.weapon.Weapon;

public class Enemy extends GameCharacter {

	public Enemy(String name, int strength, int agility, int intelligence, Weapon weapon) {
		super(name, strength, agility, intelligence, weapon);
	}

	@Override
	public String toString() {
		return super.getName() + " - " + "Strength: " + super.getStrength() + " Agility: " + super.getAgility()
				+ " Intelligence: " + super.getIntelligence() + ", Weapon: " + super.getWeapon().getName();
	}

}
