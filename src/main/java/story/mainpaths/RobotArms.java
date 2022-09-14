package story.mainpaths;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;
import rpggame.RpgGameApp;

public class RobotArms {
	
	public static boolean startRobotArms(Player player, Scanner scanner) throws InterruptedException {
			
		System.out.println("YOU ENTER ROBOT ARMS APARTMENTS");
		RpgGameApp.nextLine(scanner);
		
			boolean robotArmsCompleted = Combat.twoEnemies(player, EnemyFactory.createEnemyCustomLevel(player, 3), EnemyFactory.createEnemyCustomLevel(player, 3), scanner, true);
			
			return robotArmsCompleted;
		}

}
