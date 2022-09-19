package rpggame.person;

public class Person {

	private String name;
	private int strength;
	private int intelligence;
	private int agility;

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public String getName() {
		return name;
	}

	public int getAttackDamage() {
		return 0;
	}

	public int[] getAttackCritDamage() {
		return new int[] { 0, 0 };
	}

}
