package story.firstpathsplit;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;

public class Graveyard {
	
public static boolean startGraveyard(Player player, Scanner scanner) throws InterruptedException {
		
		System.out.println("YOUR IN THE GRAVEYARD NOW - COMBAT SHOULD START");
		
		boolean graveyardCompleted = Combat.oneEnemy(player, EnemyFactory.createEqualEnemy(player), scanner);
		
		return graveyardCompleted;
	}

}
