package rpggame.weapon;

import java.util.Objects;

public class Weapon {

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

	public String getName() {
		return this.name;
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
		return String.format("%-40s -> %-13s agi.Req %s int.Req %s",
				this.name,
				this.weaponType.toString().toUpperCase(),
				this.agilityRequired,
				this.intelligenceRequired);
	}

}
