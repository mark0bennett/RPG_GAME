package rpggame.combat;

import java.util.Scanner;

import rpggame.person.Person;
import rpggame.person.enemy.Enemy;
import rpggame.person.player.Player;
import rpggame.story.StoryTeller;
import rpggame.utils.EnemyFactory;
import rpggame.utils.SoundPlayer;

public class Combat {

	private final Scanner scanner;
	private final EnemyFactory enemyFactory;
	private final CombatService combatService;

	public Combat() {
		this.scanner = new Scanner(System.in);
		this.enemyFactory = new EnemyFactory();
		this.combatService = new CombatService(this.scanner);
	}

	public boolean oneEnemy(Player player, String name, int level, boolean withCrits) {
		boolean wonBattle = false;
		// print combat text and create enemy
		combatService.printCombatHasBegun();
		combatService.pickWeapon(player);
		Enemy enemy = enemyFactory.createEnemyCustomLevel(player, name, level);
		combatService.printVersusText(player, enemy);
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
			combatService.printWinText(player);
		} else {
			SoundPlayer.playSound("LifeIsCruel.wav");
			combatService.printLossText();
		}
		return wonBattle;
	}

	public boolean twoEnemies(Player player, String name1, int level, String name2, int level2, boolean withCrits) {
		boolean wonBattle = false;
		// print combat text and create enemy
		combatService.printCombatHasBegun();
		combatService.pickWeapon(player);
		Enemy enemy = enemyFactory.createEnemyCustomLevel(player, name1, level);
		Enemy enemy2 = enemyFactory.createEnemyCustomLevel(player, name2, level2);
		combatService.printVersusTextTwoEnemies(player, enemy, enemy2);

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
			combatService.printWinText(player);
		} else {
			SoundPlayer.playSound("LifeIsCruel.wav");
			combatService.printLossText();
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
			StoryTeller.nextLine(scanner);
			// check if enemy is dead
			if (enemy.getStrength() < 1) {
				System.out.println("You Won!");
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
			StoryTeller.nextLine(scanner);
			// check if enemy is dead
			if (player.getStrength() < 1) {
				System.out.println("You Lost!");
				wonBattle = false;
				break;
			}
			combatService.printPlayerAndOneEnemy(player, enemy);
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

	private void attackSequenceNoCrits(Person attacker, Person defender) {
		int attackDamage = attacker.getAttackDamage();
		System.out.println(attacker.getName() + " attacks " + defender.getName() + " for " + attackDamage + " damage!");
		defender.setStrength(defender.getStrength() - attackDamage);
	}

	private void attackSequenceWithCrits(Person attacker, Person defender) {
		int[] attackNumbers = attacker.getAttackCritDamage();
		int attackDamage = attackNumbers[0];
		int critDamage = attackNumbers[1];
		// you attack
		System.out.println(attacker.getName() + " attacks " + defender.getName() + " for " + attackDamage
				+ " damage plus " + critDamage + " Crit!");
		// enemy strength changed from attack
		defender.setStrength(defender.getStrength() - (attackDamage + critDamage));
	}

	public void printCombatHasBegun() {
		combatService.printCombatHasBegun();
	}

	public void printVersusText(Player player, Enemy enemy) {
		combatService.printVersusText(player, enemy);
	}

}
