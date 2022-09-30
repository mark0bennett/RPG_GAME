package rpggame;

import rpggame.gamecharacter.player.Player;
import rpggame.story.Story;
import rpggame.story.StoryTeller;

public class RpgGameApp {

	public static void main(String[] args) {

		// tests
		// package into a jar/exe file for execution on any computer
		// TODO: attack 2 enemies, pick which enemy to attack
		// TODO: best way to pass scanner around? maybe look at random too?
		// TODO: think about DI, what if I change Combat/the way other classes are
		// implemented?
		// TODO: try with resources, for scanner/reader
		// TODO: method and variable names, be verbose
		// TODO: variables either global or right where you need them
		// TODO: reading a file from a dir - application properties, dont hard code path
		// TODO: Store.java as a new class for vendors?
		// TODO: decouple classes!
		// TODO: var keyword
		// TODO: clean up combat class
		// TODO: use multiple try catch blocks instead of if else and show text only in
		// StoryTeller or Combat, depending on exception thrown (no println in service
		// classes)

		Story storyTeller = new StoryTeller();

		storyTeller.intro();
		Player player = storyTeller.createPlayer();

		storyTeller.startFirstFight(player);

		storyTeller.firstPathsSplit(player);

		storyTeller.meetFirstNPC(player);

		storyTeller.mainPathsSplit(player);

		storyTeller.lastPathsSplit(player);

		storyTeller.finalBoss(player);
	}

}
