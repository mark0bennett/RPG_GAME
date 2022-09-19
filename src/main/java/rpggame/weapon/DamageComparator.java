package rpggame.weapon;

import java.util.Comparator;

public class DamageComparator implements Comparator<Weapon> {

	// for ordering weapons in backpack in order of damage, when adding to backpack
	@Override
	public int compare(Weapon o1, Weapon o2) {
		return o1.getDamage() - o2.getDamage();
	}

}
