package rpggame;

import rpggame.gamecharacter.player.Player;
import rpggame.story.Story;
import rpggame.story.StoryTeller;

public class RpgGameApp {

	public static void main(String[] args) {

		// tests
		// package into a jar/exe file for execution on any computer
		// TODO: think about DI, what if I change Combat/the way other classes are
		// implemented?
		// TODO: reading a file from a dir - application properties, dont hard code path
		// TODO: decouple classes!
		// TODO: clean up combat class
		// TODO: use multiple try catch blocks instead of if else and show text only in
		// StoryTeller or Combat, depending on exception thrown (no println in service
		// classes) and also readers throw exceptions, custom error messages back to
		// StoryTeller - pickWeapon()

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
