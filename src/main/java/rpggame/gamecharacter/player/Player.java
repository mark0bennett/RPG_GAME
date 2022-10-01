package rpggame.gamecharacter.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rpggame.gamecharacter.GameCharacter;
import rpggame.utils.EnemyFactory;
import rpggame.weapon.DamageComparator;
import rpggame.weapon.Weapon;
import rpggame.weapon.WeaponType;

public class Player extends GameCharacter {

	private List<Weapon> backPack;
	private int nixonBucks;
	private Progress progress;
	
	public Player(String name, int strength, int agility, int intelligence) {
		super(name, strength, agility, intelligence, EnemyFactory.weaponList.get(0));
		// made the list public and static in EnemyFactory
		this.backPack = new ArrayList<Weapon>();
		this.backPack.add(EnemyFactory.weaponList.get(0));
		this.backPack.add(EnemyFactory.weaponList.get(1));
		this.nixonBucks = 0;
		this.progress = new Progress();
	}

	public void sellWeapon(int index, WeaponType vendorType) {
		// thirty percent of weapon damage
		int thirtyPercent = (this.backPack.get(index).getDamage()) / 3;
		// if vendor type is same as weapon type +30% to sell value, else -30%
		if (this.backPack.get(index).getWeaponType() == vendorType) {
			this.nixonBucks = this.nixonBucks + this.backPack.get(index).getDamage() + thirtyPercent;
		} else {
			this.nixonBucks = this.nixonBucks + this.backPack.get(index).getDamage() - thirtyPercent;
		}
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

	public List<Weapon> getBackPack() {
		return this.backPack;
	}

	public int getCurrentTotalStatPoints() {
		int currentStatPoints = super.getStrength() + super.getAgility() + super.getIntelligence();
		return currentStatPoints;
	}

	public int getNixonBucks() {
		return this.nixonBucks;
	}

	public void setNixonBucks(int nixonBucks) {
		this.nixonBucks = nixonBucks;
	}

	public Progress getProgress() {
		return this.progress;
	}

	@Override
	public String toString() {
		return super.getName() + " - Strength: " + super.getStrength() + ", Agility: " + super.getAgility()
				+ ", Intelligence: " + super.getIntelligence() + ", Weapon: " + super.getWeapon().getName()
				+ ", NixonBucks: " + this.nixonBucks;
	}

}
