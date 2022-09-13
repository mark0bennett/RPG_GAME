package weapons;

public class Knife implements Weapon {

	private int damage = 2;
	private int agilityRequired = 2;
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
		return "Knife Dmg 2";
	}

}
