package rpggame;

import rpggame.person.player.Player;
import rpggame.story.Story;
import rpggame.story.StoryTeller;

public class RpgGameApp {

	public static void main(String[] args) {

		// tests
		// package into a jar/exe file for execution on any computer
		// TODO: Combat Interface and have CombatCrits and CombatNoCrits, have a
		// CombatService for all the doubled methods?
		// TODO: split up story teller into Act I Act II and Act III???
		// TODO: attack 2 enemies, pick which enemy to attack
		// TODO: change some of the big else if blocks to switch statements?
		// TODO: continue making txt files for StoryTeller

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
