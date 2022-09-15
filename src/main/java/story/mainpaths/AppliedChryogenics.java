package story.mainpaths;

import java.util.Scanner;

import rpggame.RpgGameApp;
import rpggame.combat.Combat;
import rpggame.player.Player;
import rpggame.utils.EnemyFactory;

public class AppliedChryogenics {
	
	public static boolean startAppliedChryogenics(Player player, Scanner scanner) throws InterruptedException {
			
		System.out.println("YOU ENTER APPLIED CHRYOGENICS");
		RpgGameApp.nextLine(scanner);
		
			boolean appliedChryogenicsCompleted = Combat.twoEnemies(player, EnemyFactory.createEnemyCustomLevel(player, 2), EnemyFactory.createEnemyCustomLevel(player, 2), scanner, true);
			
			return appliedChryogenicsCompleted;
		}

}
