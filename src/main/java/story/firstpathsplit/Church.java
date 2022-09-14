package story.firstpathsplit;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;
import rpggame.RpgGameApp;

public class Church {
	
	public static boolean startChurch(Player player, Scanner scanner) throws InterruptedException {
		System.out.println("YOU ENTER THE CHURCH...");
		RpgGameApp.nextLine(scanner);
		System.out.println("It's quiet, a little too quiet...");
		RpgGameApp.nextLine(scanner);
		System.out.println("...");
		System.out.println("*CRASH!!!!!!!!*");
		RpgGameApp.nextLine(scanner);
		System.out.println("Something huge has just smashed through the wall and is coming RIGHT AT YOU!");
		RpgGameApp.nextLine(scanner);
		
		boolean churchCompleted = Combat.oneEnemy(player, EnemyFactory.createEnemyCustomLevel(player, 0), scanner, false);
		
		return churchCompleted;
	}

}
