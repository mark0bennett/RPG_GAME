package rpggame.combat;

import rpggame.player.Player;

public class Combat {

	private final CombatService combatService;

	public Combat() {
		this.combatService = new CombatService();
	}

	public boolean oneEnemy(Player player, int level, boolean withCrits) throws InterruptedException {
		boolean wonBattle = combatService.oneEnemy(player, level, withCrits);
		return wonBattle;
	}

	// TODO: with the scanner, lines are duplicated, scanner has leftover text
	// during combat, when you pick weapon

	public boolean twoEnemies(Player player, int level, int level2, boolean withCrits) throws InterruptedException {

		boolean wonBattle = combatService.twoEnemies(player, level, level2, withCrits);
		return wonBattle;
	}

}
