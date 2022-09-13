package story.firstpathsplit;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;

public class Forest {
	
public static boolean startForest(Player player, Scanner scanner) throws InterruptedException {
		
		System.out.println("YOUR IN THE FOREST NOW - COMBAT SHOULD START");
		
		boolean forestCompleted = Combat.oneEnemy(player, EnemyFactory.createEqualEnemy(player), scanner);
		
		return forestCompleted;
	}

}
