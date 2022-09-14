package story.mainpaths;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;

public class River {
	
	private static final int MS_1000 = 1000;
	
	public static boolean startRiver(Player player, Scanner scanner) throws InterruptedException {
			
		Thread.sleep(MS_1000);
		System.out.println("YOU ENTER THE RIVER");
		Thread.sleep(MS_1000);
		
			boolean riverCompleted = Combat.twoEnemies(player, EnemyFactory.createEnemyCustomLevel(player, 1), EnemyFactory.createEnemyCustomLevel(player, 1), scanner, true);
			
			return riverCompleted;
		}

}
