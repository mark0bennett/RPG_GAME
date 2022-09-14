package story.mainpaths;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;

public class RobotArms {
	
	private static final int MS_1000 = 1000;
	
	public static boolean startRobotArms(Player player, Scanner scanner) throws InterruptedException {
			
		Thread.sleep(MS_1000);
		System.out.println("YOU ENTER ROBOT ARMS APARTMENTS");
		Thread.sleep(MS_1000);
		
			boolean robotArmsCompleted = Combat.twoEnemies(player, EnemyFactory.createEnemyCustomLevel(player, 3), EnemyFactory.createEnemyCustomLevel(player, 3), scanner, true);
			
			return robotArmsCompleted;
		}

}
