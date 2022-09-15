package rpggame.story;

import rpggame.player.Player;

public interface Story {

	public void intro();

	public Player createPlayer();

	public void startFirstFight(Player player);

	public void churchGraveyardOrForest(Player player);

	public void meetFry(Player player);

	public void mainPaths(Player player);

}
