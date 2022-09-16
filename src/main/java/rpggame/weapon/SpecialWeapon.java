package rpggame.weapon;

public class SpecialWeapon implements Weapon {

	// for reading in using a csv file
	private String name;
	private int damage;
	private int agilityRequired;
	private int intelligenceRequired;
	private WeaponType weaponType;
	private int price;

	public SpecialWeapon(String name, int damage, int agilityRequired, int intelligenceRequired, WeaponType weaponType,
			int price) {
		this.name = name;
		this.damage = damage;
		this.agilityRequired = agilityRequired;
		this.intelligenceRequired = intelligenceRequired;
		this.weaponType = weaponType;
		this.price = price;
	}

	@Override
	public int getDamage() {
		return this.damage;
	}

	@Override
	public int getAgilityRequired() {
		return this.agilityRequired;
	}

	@Override
	public int getIntelligenceRequired() {
		return this.intelligenceRequired;
	}

	@Override
	public int getPrice() {
		return this.price;
	}

	@Override
	public WeaponType getWeaponType() {
		return this.weaponType;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
