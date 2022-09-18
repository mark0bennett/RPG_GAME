package rpggame.story;

import rpggame.player.Player;

public interface Story {

	void intro();

	Player createPlayer();

	void startFirstFight(Player player);

	void firstPathsSplit(Player player);

	void meetFirstNPC(Player player);

	void mainPathsSplit(Player player);

	void lastPathsSplit(Player player);

	void finalBoss(Player player);

}
