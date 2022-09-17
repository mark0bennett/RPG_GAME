package rpggame.weapon;

public class BareMagicHands extends Weapon {

	private String name;
	private int damage;
	private int agilityRequired;
	private int intelligenceRequired;
	private WeaponType weaponType;
	private int price;

	public BareMagicHands() {
		super("Bare Magic Hands (Dmg 1)", 1, 1, 1, WeaponType.INTELLIGENCE, 0);
	}

	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}

	public int getAgilityRequired() {
		return agilityRequired;
	}

	public int getIntelligenceRequired() {
		return intelligenceRequired;
	}

	public WeaponType getWeaponType() {
		return weaponType;
	}

	public int getPrice() {
		return price;
	}

}
