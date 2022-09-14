package story.firstpathsplit;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;
import rpggame.RpgGameApp;

public class Forest {
	
public static boolean startForest(Player player, Scanner scanner) throws InterruptedException {
		
	System.out.println("YOU ENTER THE FOREST...cautiously optimistic...");
	RpgGameApp.nextLine(scanner);
	System.out.println("A sign reads - TURN AROUND NOW - Prof. Farnsw......");
	RpgGameApp.nextLine(scanner);
	System.out.println("Some horrid looking animal falls from the trees above");
	RpgGameApp.nextLine(scanner);
	System.out.println("An Atomic Mutant Man, who would be well suited to play Basketball...");
	RpgGameApp.nextLine(scanner);
	System.out.println("That Cannon in your chest doesn't frighten me!");
	
		boolean forestCompleted = Combat.oneEnemy(player, EnemyFactory.createEnemyCustomLevel(player, 0), scanner, false);
		
		return forestCompleted;
	}

}
