package rpggame.combat;

import rpggame.enemy.Enemy;
import rpggame.player.Player;
import rpggame.story.StoryTellerService;

public class Combat {

	private final CombatService combatService;

	public Combat() {
		this.combatService = new CombatService();
	}

	public boolean oneEnemy(Player player, boolean withCrits) throws InterruptedException {
		boolean wonBattle = combatService.oneEnemy(player, withCrits);
		return wonBattle;
	}

	// TODO: with the scanner, lines are duplicated, scanner has leftover text
	// during combat, when you pick weapon

	public boolean twoEnemies(Player player, boolean withCrits) throws InterruptedException {

		combatService.twoEnemies(player, withCrits);

		boolean wonBattle = false;

		printCombatHasBegun();
		player.printBackpack();
		pickWeapon(player, scanner);
		printVersusTextTwoEnemies(player, enemy, enemy2);

		int playerStrengthBeforeCombat = player.getStrength();
		System.out.println("Combat is automated for now...");

		// TODO: Need to create attackWithNoCritsTwoEnemies
		if (!withCrits) {
			wonBattle = attacksWithNoCrits(player, enemy);
		}

		if (withCrits) {
			wonBattle = attacksWithCritsTwoEnemies(player, enemy, enemy2);
		}

		// reset player strength to what it was before combat
		if (player.getStrength() < playerStrengthBeforeCombat) {
			System.out.println("Your strength has been restored");
			player.setStrength(playerStrengthBeforeCombat);
		}

		if (wonBattle) {
			showAndAddDroppedWeapon(player, enemy);
			// TODO: this is lazy, reusing same method for 2nd enemy
			showAndAddDroppedWeapon(player, enemy2);
			StoryTellerService.nextLine(scanner);
			System.out.println("You can now increase one of your stats by 1");
			increaseStats(player, scanner);
			printWinText(player);
		}

		if (!wonBattle) {
			printLossText();
		}
		return wonBattle;
	}

	private static void printVersusTextTwoEnemies(Player player, Enemy enemy, Enemy enemy2)
			throws InterruptedException {
		// show stats of you and your enemy
		System.out.println("--------------------------");
		System.out.println(player);
		Thread.sleep(ONE_SECOND);
		System.out.println("vs");
		Thread.sleep(ONE_SECOND);
		System.out.println(enemy);
		Thread.sleep(ONE_SECOND);
		System.out.println("and");
		System.out.println(enemy2);
		System.out.println("--------------------------");
		Thread.sleep(ONE_SECOND);
	}

	private static boolean attacksWithCritsTwoEnemies(Player player, Enemy enemy, Enemy enemy2)
			throws InterruptedException {
		boolean wonBattle = false;
		boolean firstEnemyDead = false;
		while (true) {
			// TODO: just attacks the first enemy then the second for now, maybe add a
			// choice later
			Thread.sleep(ONE_SECOND);
			// for clarity
			int[] playerAttackNumbers = player.attackCrit();
			int playerAttack = playerAttackNumbers[0];
			int playerCrit = playerAttackNumbers[1];
			// you attack
			System.out.println("You attack for " + playerAttack + " damage plus " + playerCrit + " Crit!");
			// enemy strength changed from attack
			enemy.setStrength(enemy.getStrength() - (playerAttack + playerCrit));
			Thread.sleep(ONE_SECOND);
			// check if first enemy is dead
			if (enemy.getStrength() < 1) {
				System.out.println("You Killed the first Enemy!");
				firstEnemyDead = true;
				break;
			}
			// enemy attacks
			// for clarity
			int[] enemyAttackNumbers = enemy.attackCrit();
			int enemyAttack = enemyAttackNumbers[0];
			int enemyCrit = enemyAttackNumbers[1];
			System.out.println("Enemy attacks for " + enemyAttack + " damage plus " + enemyCrit + " Crit!");
			// enemy strength changed from attack
			player.setStrength(player.getStrength() - (enemyAttack + enemyCrit));
			Thread.sleep(ONE_SECOND);
			// check if enemy is dead
			if (player.getStrength() < 1) {
				Thread.sleep(ONE_SECOND);
				System.out.println("You Lost!");
				wonBattle = false;
				break;
			}
			Thread.sleep(ONE_SECOND);
			System.out.println("--------------------------");
			Thread.sleep(ONE_SECOND);
			System.out.println(player);
			Thread.sleep(ONE_SECOND);
			System.out.println(enemy);
			Thread.sleep(ONE_SECOND);
			System.out.println(enemy2);
			Thread.sleep(ONE_SECOND);
			System.out.println("--------------------------");
			Thread.sleep(ONE_SECOND);
		}

		if (firstEnemyDead) {
			while (true) {
				Thread.sleep(ONE_SECOND);
				// for clarity
				int[] playerAttackNumbers = player.attackCrit();
				int playerAttack = playerAttackNumbers[0];
				int playerCrit = playerAttackNumbers[1];
				// you attack
				System.out.println(
						"You attack the second enemy for " + playerAttack + " damage plus " + playerCrit + " Crit!");
				// enemy strength changed from attack
				enemy2.setStrength(enemy2.getStrength() - (playerAttack + playerCrit));
				Thread.sleep(ONE_SECOND);
				// check if second enemy is dead
				if (enemy2.getStrength() < 1) {
					System.out.println("You Killed the second Enemy and Won!");
					wonBattle = true;
					break;
				}
				// enemy attacks
				// for clarity
				int[] enemy2AttackNumbers = enemy2.attackCrit();
				int enemy2Attack = enemy2AttackNumbers[0];
				int enemy2Crit = enemy2AttackNumbers[1];
				System.out
						.println("Second Enemy attacks for " + enemy2Attack + " damage plus " + enemy2Crit + " Crit!");
				// enemy strength changed from attack
				player.setStrength(player.getStrength() - (enemy2Attack + enemy2Crit));
				Thread.sleep(ONE_SECOND);
				// check if enemy is dead
				if (player.getStrength() < 1) {
					Thread.sleep(ONE_SECOND);
					System.out.println("You Lost!");
					wonBattle = false;
					break;
				}
				Thread.sleep(ONE_SECOND);
				System.out.println("--------------------------");
				Thread.sleep(ONE_SECOND);
				System.out.println(player);
				Thread.sleep(ONE_SECOND);
				System.out.println(enemy2);
				Thread.sleep(ONE_SECOND);
				System.out.println("--------------------------");
				Thread.sleep(ONE_SECOND);
			}
		}
		return wonBattle;
	}

}
