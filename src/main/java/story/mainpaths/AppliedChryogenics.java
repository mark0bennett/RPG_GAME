package story.mainpaths;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;

public class AppliedChryogenics {
	
	private static final int MS_1000 = 1000;
	
	public static boolean startAppliedChryogenics(Player player, Scanner scanner) throws InterruptedException {
			
		Thread.sleep(MS_1000);
		System.out.println("YOU ENTER APPLIED CHRYOGENICS");
		Thread.sleep(MS_1000);
		
			boolean appliedChryogenicsCompleted = Combat.twoEnemies(player, EnemyFactory.createEnemyCustomLevel(player, 2), EnemyFactory.createEnemyCustomLevel(player, 2), scanner, true);
			
			return appliedChryogenicsCompleted;
		}

}
