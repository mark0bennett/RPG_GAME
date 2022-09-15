package rpggame.story;

import rpggame.player.Player;

public class StoryTeller {

	private final StoryTellerService storyTellerService;

	public StoryTeller(StoryTellerService storyTellerService) {
		this.storyTellerService = storyTellerService;
	}

	public void intro() {
		storyTellerService.intro();
	}

	public Player createPlayer() {
		Player player = storyTellerService.createPlayer();
		return player;
	}

	public void startFirstFight(Player player) throws InterruptedException {
		storyTellerService.startFirstFight(player);
	}

	public void churchGraveyardOrForest(Player player) throws InterruptedException {
		storyTellerService.churchGraveyardOrForest(player);
	}

	public void meetFry(Player player) {
		storyTellerService.meetFry(player);
	}

	public void mainPaths(Player player) throws InterruptedException {
		storyTellerService.mainPaths(player);
	}

}
