package rpggame;

import rpggame.player.Player;
import rpggame.story.StoryTeller;

public class RpgGameApp {

	public static void main(String[] args) {

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
		// prof sells magic weapons, Weapons will need a price too
		// Leela, Amy, Bender when you meet them they increase stats
		//TODO: Story Interface and remove StoryTellerService?
		//TODO: Combat Interface and remove CombatService?

		StoryTeller storyTeller = new StoryTeller();

		storyTeller.intro();
		Player player = storyTeller.createPlayer();
		StoryTeller.printPlayer(player);
		storyTeller.startFirstFight(player);

		storyTeller.firstPathsSplit(player);

		storyTeller.meetFirstNPC(player);

		storyTeller.mainPathsSplit(player);

		System.out.println("now back in main method");

		// TODO: Boss fight with over-levelled boss...or 2
	}

}
