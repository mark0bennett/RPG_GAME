package rpggame.weapon;

public interface Weapon {

	// TODO: now that all weapons are read in with a csv, they all become
	// WeaponObject - is there a need for this interface???

	int getDamage();

	int getAgilityRequired();

	int getIntelligenceRequired();
	
	int getPrice();

	WeaponType getWeaponType();

}
