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
