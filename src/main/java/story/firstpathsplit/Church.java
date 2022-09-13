package story.firstpathsplit;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;

public class Church {
	
	public static boolean startChurch(Player player, Scanner scanner) throws InterruptedException {
		
		System.out.println("YOUR IN THE CHURCH NOW - COMBAT SHOULD START");
		
		boolean churchCompleted = Combat.oneEnemy(player, EnemyFactory.createEqualEnemy(player), scanner);
		
		return churchCompleted;
	}

}
