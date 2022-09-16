package rpggame.story;

import rpggame.player.Player;

public interface Story {

	public void intro();

	public Player createPlayer();

	public void startFirstFight(Player player);

	public void firstPathsSplit(Player player);

	public void meetFirstNPC(Player player);

	public void mainPathsSplit(Player player);
	
	public void lastPathsSplit(Player player);

}
