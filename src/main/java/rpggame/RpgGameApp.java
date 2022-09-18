package rpggame;

import rpggame.player.Player;
import rpggame.story.Story;
import rpggame.story.StoryTeller;

public class RpgGameApp {

	public static void main(String[] args) {

		// SAVE GAME TO A TXT FILE WITH STATS, and LOAD IN A GAME with story progression
		// tests
		// add sound?
		// add some ascii art
		// find weapons randomly or scripted finding random weapons
		// package into a jar/exe file for execution on any computer
		// TODO: Combat Interface and have CombatCrits and CombatNoCrits, have a
		// CombatService for all the doubled methods?
		// TODO: split up story teller into Act I Act II and Act III???
		// TODO: can the story be read in from txt files using AsciiArtReader??
		// TODO: Weapon toString cleaner with aligned tabs

		Story storyTeller = new StoryTeller();

		storyTeller.intro();
		Player player = storyTeller.createPlayer();
		StoryTeller.printPlayer(player);

		storyTeller.startFirstFight(player);

		storyTeller.firstPathsSplit(player);

		storyTeller.meetFirstNPC(player);

		storyTeller.mainPathsSplit(player);

		storyTeller.lastPathsSplit(player);

		storyTeller.finalBoss(player);
	}

}
