package rpggame.combat;

import rpggame.player.Player;

public class Combat {

	private final CombatService combatService;

	public Combat() {
		this.combatService = new CombatService();
	}

	public boolean oneEnemy(Player player, String name, int level, boolean withCrits) {
		boolean wonBattle = combatService.oneEnemy(player, name, level, withCrits);
		return wonBattle;
	}

	public boolean twoEnemies(Player player, String name1, int level, String name2, int level2, boolean withCrits) {
		boolean wonBattle = combatService.twoEnemies(player, name1, level, name2, level2, withCrits);
		return wonBattle;
	}

}
