package rpggame.combat;

import java.util.Scanner;

import rpggame.gamecharacter.AttackDamage;
import rpggame.gamecharacter.GameCharacter;
import rpggame.gamecharacter.enemy.Enemy;
import rpggame.gamecharacter.player.Player;
import rpggame.utils.EnemyFactory;
import rpggame.utils.Printer;
import rpggame.utils.SoundPlayer;

public class Combat {

	private final Scanner scanner;
	private final EnemyFactory enemyFactory;
	private final CombatService combatService;

	public Combat(Scanner scanner) {
		this.scanner = scanner;
		this.enemyFactory = new EnemyFactory();
		this.combatService = new CombatService(this.scanner);
	}

	public boolean createOneEnemy(Player player, String name, int level, boolean withCrits) {
		boolean wonBattle = false;
		// print combat text and create enemy
		Printer.printCombatHasBegun();
		combatService.pickWeapon(player);
		Enemy enemy = enemyFactory.createEnemyCustomLevel(player, name, level);
		Printer.printVersusTextOneEnemy(player, enemy);
		// for restoring health after battle
		int playerStrengthBeforeCombat = player.getStrength();
		// for dropping money based on enemy strength
		int enemyStrengthBeforeCombat = enemy.getStrength();
		// based on boolean passed in, battle with or without crits
		wonBattle = battleOneEnemy(player, enemy, withCrits);
		// reset player strength to what it was before combat
		if (player.getStrength() < playerStrengthBeforeCombat) {
			System.out.println("Your strength has been restored");
			player.setStrength(playerStrengthBeforeCombat);
		}
		// if you won, drop weapons and money and increase stats
		if (wonBattle) {
			combatService.showAndAddDroppedWeaponAndMoney(player, enemy, enemyStrengthBeforeCombat);
			combatService.increaseStats(player);
			Printer.printWinText(player);
		} else {
			Printer.printLossText();
		}
		return wonBattle;
	}

	public boolean createTwoEnemies(Player player, String name1, int level, String name2, int level2,
			boolean withCrits) {
		boolean wonBattle = false;
		// print combat text and create enemy
		Printer.printCombatHasBegun();
		combatService.pickWeapon(player);
		Enemy enemy = enemyFactory.createEnemyCustomLevel(player, name1, level);
		Enemy enemy2 = enemyFactory.createEnemyCustomLevel(player, name2, level2);
		Printer.printVersusTextTwoEnemies(player, enemy, enemy2);

		// for restoring health after battle
		int playerStrengthBeforeCombat = player.getStrength();
		// for dropping money based on enemy strength
		int enemyStrengthBeforeCombat = enemy.getStrength();
		// based on boolean passed in, battle with or without crits
		wonBattle = battleTwoEnemies(player, enemy, enemy2, withCrits);
		// reset player strength to what it was before combat
		if (player.getStrength() < playerStrengthBeforeCombat) {
			System.out.println("Your strength has been restored");
			player.setStrength(playerStrengthBeforeCombat);
		}
		// if you won, drop weapons and money and increase stats
		if (wonBattle) {
			combatService.showAndAddDroppedWeaponAndMoney(player, enemy, enemyStrengthBeforeCombat);
			combatService.showAndAddDroppedWeaponAndMoney(player, enemy2, enemyStrengthBeforeCombat);
			combatService.increaseStats(player);
			Printer.printWinText(player);
		} else {
			Printer.printLossText();
		}
		return wonBattle;
	}

	private boolean battleOneEnemy(Player player, Enemy enemy, boolean withCrits) {
		boolean wonBattle = false;
		while (true) {
			// you attack depending on withCrits
			// attacker is first variable passed in
			if (withCrits) {
				attackSequenceWithCrits(player, enemy);
			} else {
				attackSequenceNoCrits(player, enemy);
			}
			Printer.nextLine(scanner);
			// check if enemy is dead
			if (enemy.getStrength() < 1) {
				System.out.println("You Won!");
				Printer.nextLine(scanner);
				wonBattle = true;
				break;
			}
			// enemy attacks depending on withCrits
			// attacker is first variable passed in
			if (withCrits) {
				attackSequenceWithCrits(enemy, player);
			} else {
				attackSequenceNoCrits(enemy, player);
			}
			Printer.nextLine(scanner);
			// check if enemy is dead
			if (player.getStrength() < 1) {
				SoundPlayer.playSound("LifeIsCruel.wav");
				System.out.println("You Lost!");
				Printer.nextLine(scanner);
				wonBattle = false;
				break;
			}
			Printer.printPlayerAndOneEnemy(player, enemy);
		}
		return wonBattle;
	}

	private boolean battleTwoEnemies(Player player, Enemy enemy, Enemy enemy2, boolean withCrits) {
		boolean wonBattle = false;
		boolean firstEnemyDead = false;
		firstEnemyDead = battleOneEnemy(player, enemy, withCrits);
		if (firstEnemyDead) {
			wonBattle = battleOneEnemy(player, enemy2, withCrits);
		}
		return wonBattle;
	}

	private void attackSequenceNoCrits(GameCharacter attacker, GameCharacter defender) {
		AttackDamage attackDamage = attacker.attack();
		System.out.println(attacker.getName() + " attacks " + defender.getName() + " for "
				+ attackDamage.getBaseAttackDamage() + " damage!");
		defender.setStrength(defender.getStrength() - attackDamage.getBaseAttackDamage());
	}

	private void attackSequenceWithCrits(GameCharacter attacker, GameCharacter defender) {
		AttackDamage attackDamage = attacker.attack();
		System.out.println(attacker.getName() + " attacks " + defender.getName() + " for "
				+ attackDamage.getBaseAttackDamage() + " damage plus " + attackDamage.getCritAttackDamage() + " Crit!");
		defender.setStrength(
				defender.getStrength() - (attackDamage.getBaseAttackDamage() + attackDamage.getCritAttackDamage()));
	}

}
