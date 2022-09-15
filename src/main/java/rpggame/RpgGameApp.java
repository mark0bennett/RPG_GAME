package rpggame;

import rpggame.player.Player;
import rpggame.story.StoryTeller;
import rpggame.story.StoryTellerService;

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

		StoryTellerService storyTellerService = new StoryTellerService();
		StoryTeller storyTeller = new StoryTeller(storyTellerService);

		storyTeller.intro();
		Player player = storyTeller.createPlayer();
		StoryTellerService.printPlayer(player);
		storyTeller.startFirstFight(player);

		storyTeller.churchGraveyardOrForest(player);

		storyTeller.meetFry(player);

		storyTeller.mainPaths(player);

		System.out.println("now back in main method");

		// TODO: Boss fight with over-levelled boss...or 2
	}

}
