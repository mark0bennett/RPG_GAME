package rpggame.weapon;

public class BareHands extends Weapon {

	private String name;
	private int damage = 1;
	private int agilityRequired = 1;
	private int intelligenceRequired = 1;
	private WeaponType weaponType = WeaponType.AGILITY;
	private int price;

	public BareHands() {
		super("Bare Hands (Dmg 1)", 1, 1, 1, WeaponType.AGILITY, 0);
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
