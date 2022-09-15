package rpggame.weapon;

import java.util.Objects;

public class WeaponObject implements Weapon {

	// for reading in using a csv file
	private String name;
	private int damage;
	private int agilityRequired;
	private int intelligenceRequired;
	private WeaponType weaponType;

	public WeaponObject(String name, int damage, int agilityRequired, int intelligenceRequired, WeaponType weaponType) {
		this.name = name;
		this.damage = damage;
		this.agilityRequired = agilityRequired;
		this.intelligenceRequired = intelligenceRequired;
		this.weaponType = weaponType;
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
	public WeaponType getWeaponType() {
		return this.weaponType;
	}

	// TODO: this equals method not working? had to do it manually in Combat Class,
	// when adding to backpack
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof WeaponObject))
			return false;
		WeaponObject other = (WeaponObject) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	

}
