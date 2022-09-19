package rpggame.person;

public interface Person {

	public int getStrength();

	public void setStrength(int strength);

	public int getIntelligence();

	public void setIntelligence(int intelligence);

	public int getAgility();

	public void setAgility(int agility);

	public String getName();

	public int getAttackDamage();

	public int[] getAttackCritDamage();

}
