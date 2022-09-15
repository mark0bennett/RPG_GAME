package rpggame;

import java.util.Scanner;

import rpggame.combat.Combat;
import rpggame.player.Player;
import rpggame.story.StoryTeller;
import rpggame.story.StoryTellerService;
import rpggame.utils.EnemyFactory;

public class RpgGameApp {

	public static void main(String[] args) throws InterruptedException {

		// SAVE GAME TO A TXT FILE WITH STATS, and LOAD IN A GAME with story progression
		// can we bring up a menu at any time between things happening, to delete
		// weapon, view stats, visit a vender etc
		// story progression, have a boolean when a 'section' is completed?
		// tests
		// add sound?
		// add some ascii art
		// find weapons randomly
		// package into a jar/exe file for execution on any computer

		// zoidberg isnt dead, you meet him later and he sells agi weapons
		// prof sells magic weapons
		// Leela, Amy, Bender when you meet them they increase stats

		// TODO: INSTEAD OF Thread.sleep() - use user input, like spacebar to progress
		// the text?

		StoryTellerService storyTellerService = new StoryTellerService();

		StoryTeller storyTeller = new StoryTeller(storyTellerService);

		storyTeller.intro();
		Player player = storyTeller.createPlayer();
		storyTeller.printPlayer(player);
		storyTeller.startFirstFight(player);
		
		//here

		storyTeller.churchGraveyardOrForest(player);

		Combat.oneEnemy(player, EnemyFactory.createEnemyCustomLevel(player, 2), true);

		StoryTeller.meetFry(player);

		// TODO: perhaps now into a while loop? to, go to vendor, contiue journey, save?
		StoryTeller.mainPaths(player);

		System.out.println("now back in main method");

		// TODO: Boss fight with over-levelled boss...or 2
		printPlayer(player);

	}

}
