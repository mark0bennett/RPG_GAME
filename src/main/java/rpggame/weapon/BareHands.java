package rpggame.weapon;

public class BareHands implements Weapon {

	private int damage = 1;
	private int agilityRequired = 1;
	private int intelligenceRequired = 1;
	private WeaponType weaponType = WeaponType.AGILITY;

	public int getDamage() {
		return damage;
	}

	public int getAgilityRequired() {
		return agilityRequired;
	}
	
	public int getIntelligenceRequired() {
		return this.intelligenceRequired;
	}

	public WeaponType getWeaponType() {
		return this.weaponType;
	}

	@Override
	public String toString() {
		return "Bare Hands Dmg 1";
	}

}
