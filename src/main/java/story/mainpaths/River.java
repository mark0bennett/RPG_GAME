package story.mainpaths;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;
import rpggame.RpgGameApp;

public class River {
	
	public static boolean startRiver(Player player, Scanner scanner) throws InterruptedException {
			
		System.out.println("YOU ENTER THE RIVER");
		RpgGameApp.nextLine(scanner);
		
			boolean riverCompleted = Combat.twoEnemies(player, EnemyFactory.createEnemyCustomLevel(player, 1), EnemyFactory.createEnemyCustomLevel(player, 1), scanner, true);
			
			return riverCompleted;
		}

}
