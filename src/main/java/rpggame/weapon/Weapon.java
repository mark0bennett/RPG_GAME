package rpggame.weapon;

import java.util.Objects;

public class Weapon {

	// for reading in using a csv file
	private String name;
	private int damage;
	private int agilityRequired;
	private int intelligenceRequired;
	private WeaponType weaponType;
	private int price;

	public Weapon(String name, int damage, int agilityRequired, int intelligenceRequired, WeaponType weaponType,
			int price) {
		this.name = name;
		this.damage = damage;
		this.agilityRequired = agilityRequired;
		this.intelligenceRequired = intelligenceRequired;
		this.weaponType = weaponType;
		this.price = price;
	}

	public int getDamage() {
		return this.damage;
	}

	public int getAgilityRequired() {
		return this.agilityRequired;
	}

	public int getIntelligenceRequired() {
		return this.intelligenceRequired;
	}

	public WeaponType getWeaponType() {
		return this.weaponType;
	}

	public int getPrice() {
		return this.price;
	}

	// TODO: this equals method not working? had to do it manually in Combat Class,
	// when adding to backpack
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Weapon))
			return false;
		Weapon other = (Weapon) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return this.name + "-> " + weaponType.toString().toUpperCase();
	}

}
