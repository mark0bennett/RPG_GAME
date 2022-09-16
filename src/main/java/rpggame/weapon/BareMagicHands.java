package rpggame.weapon;

public class BareMagicHands implements Weapon {

	private int damage = 1;
	private int agilityRequired = 1;
	private int intelligenceRequired = 1;
	private WeaponType weaponType = WeaponType.INTELLIGENCE;

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
		return "Bare Magic Hands Dmg 1";
	}

	@Override
	public int getPrice() {
		return 0;
	}

}
