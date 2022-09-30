package rpggame.gamecharacter.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rpggame.gamecharacter.GameCharacter;
import rpggame.story.StoryTellerService;
import rpggame.utils.EnemyFactory;
import rpggame.weapon.DamageComparator;
import rpggame.weapon.Weapon;
import rpggame.weapon.WeaponType;

public class Player extends GameCharacter {

	private List<Weapon> backPack;
	private int nixonBucks;

	private boolean churchCompleted;
	private boolean graveyardCompleted;
	private boolean forestCompleted;

	private boolean riverCompleted;
	private boolean appliedChryogenicsCompleted;
	private boolean robotArmsCompleted;

	private boolean momCorpCompleted;
	private boolean slurmFactoryCompleted;
	private boolean fishyJoesCompleted;

	public Player(String name, int strength, int agility, int intelligence) {
		super(name, strength, agility, intelligence, EnemyFactory.weaponList.get(0));
		// made the list public and static in EnemyFactory
		this.backPack = new ArrayList<Weapon>();
		this.backPack.add(EnemyFactory.weaponList.get(0));
		this.backPack.add(EnemyFactory.weaponList.get(1));
		this.nixonBucks = 0;

		this.churchCompleted = false;
		this.graveyardCompleted = false;
		this.forestCompleted = false;

		this.riverCompleted = false;
		this.appliedChryogenicsCompleted = false;
		this.robotArmsCompleted = false;

		this.momCorpCompleted = false;
		this.slurmFactoryCompleted = false;
		this.fishyJoesCompleted = false;
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

	public void printBackpack() {
		// show weapons in backpack
		StoryTellerService.printLineBreak();
		System.out.println("Backpack Contents");
		for (int i = 0; i < this.backPack.size(); i++) {
			System.out.println((i + 1) + ": " + this.backPack.get(i));
		}
		StoryTellerService.printLineBreak();
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

	public boolean isChurchCompleted() {
		return churchCompleted;
	}

	public void setChurchCompleted(boolean churchCompleted) {
		this.churchCompleted = churchCompleted;
	}

	public boolean isGraveyardCompleted() {
		return graveyardCompleted;
	}

	public void setGraveyardCompleted(boolean graveyardCompleted) {
		this.graveyardCompleted = graveyardCompleted;
	}

	public boolean isForestCompleted() {
		return forestCompleted;
	}

	public void setForestCompleted(boolean forestCompleted) {
		this.forestCompleted = forestCompleted;
	}

	public boolean isRiverCompleted() {
		return riverCompleted;
	}

	public void setRiverCompleted(boolean riverCompleted) {
		this.riverCompleted = riverCompleted;
	}

	public boolean isAppliedChryogenicsCompleted() {
		return appliedChryogenicsCompleted;
	}

	public void setAppliedChryogenicsCompleted(boolean appliedChryogenicsCompleted) {
		this.appliedChryogenicsCompleted = appliedChryogenicsCompleted;
	}

	public boolean isRobotArmsCompleted() {
		return robotArmsCompleted;
	}

	public void setRobotArmsCompleted(boolean robotArmsCompleted) {
		this.robotArmsCompleted = robotArmsCompleted;
	}

	public boolean isMomCorpCompleted() {
		return momCorpCompleted;
	}

	public void setMomCorpCompleted(boolean momCorpCompleted) {
		this.momCorpCompleted = momCorpCompleted;
	}

	public boolean isSlurmFactoryCompleted() {
		return slurmFactoryCompleted;
	}

	public void setSlurmFactoryCompleted(boolean slurmFactoryCompleted) {
		this.slurmFactoryCompleted = slurmFactoryCompleted;
	}

	public boolean isFishyJoesCompleted() {
		return fishyJoesCompleted;
	}

	public void setFishyJoesCompleted(boolean fishyJoesCompleted) {
		this.fishyJoesCompleted = fishyJoesCompleted;
	}

	@Override
	public String toString() {
		return super.getName() + " - Strength: " + super.getStrength() + ", Agility: " + super.getAgility()
				+ ", Intelligence: " + super.getIntelligence() + ", Weapon: " + super.getWeapon().getName()
				+ ", NixonBucks: " + this.nixonBucks;
	}

}
