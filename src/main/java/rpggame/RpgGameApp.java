package rpggame;

import rpggame.player.Player;
import rpggame.story.Story;
import rpggame.story.StoryTeller;

public class RpgGameApp {

	public static void main(String[] args) {

		// SAVE GAME TO A TXT FILE WITH STATS, and LOAD IN A GAME with story progression
		// can we bring up a menu at any time between things happening, to delete
		// weapon, view stats, visit a vender etc
		// tests
		// add sound?
		// add some ascii art
		// find weapons randomly
		// package into a jar/exe file for execution on any computer
		// TODO: Combat Interface and have CombatCrits and CombatNoCrits, have a CombatService for all the doubled methods?

		Story storyTeller = new StoryTeller();

		storyTeller.intro();
		Player player = storyTeller.createPlayer();
		StoryTeller.printPlayer(player);

		storyTeller.startFirstFight(player);

		storyTeller.firstPathsSplit(player);

		storyTeller.meetFirstNPC(player);

		storyTeller.mainPathsSplit(player);
		
		storyTeller.lastPathsSplit(player);

		System.out.println("now back in main method");

		// TODO: Boss fight with over-levelled boss...or 2 - Robot Santa? Hypnotoad?
	}

}
