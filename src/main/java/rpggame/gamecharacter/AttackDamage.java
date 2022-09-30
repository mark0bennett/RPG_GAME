package rpggame.gamecharacter;

public class AttackDamage {

	private int baseAttackDamage;
	private int critAttackDamage;

	public AttackDamage(int baseAttackDamage, int critAttackDamage) {
		this.baseAttackDamage = baseAttackDamage;
		this.critAttackDamage = critAttackDamage;
	}

	public int getBaseAttackDamage() {
		return baseAttackDamage;
	}

	public int getCritAttackDamage() {
		return critAttackDamage;
	}

}
