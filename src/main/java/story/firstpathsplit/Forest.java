package story.firstpathsplit;

import java.util.Scanner;

import characters.player.Player;
import characters.utils.EnemyFactory;
import combat.Combat;

public class Forest {
	
	private static final int MS_1000 = 1000;
	
public static boolean startForest(Player player, Scanner scanner) throws InterruptedException {
		
	Thread.sleep(MS_1000);
	System.out.println("YOU ENTER THE FOREST...cautiously optimistic...");
	Thread.sleep(MS_1000);
	System.out.println("A sign reads - TURN AROUND NOW - Prof. Farnsw......");
	Thread.sleep(MS_1000);
	System.out.println("Some horrid looking animal falls from the trees above");
	Thread.sleep(MS_1000);
	System.out.println("An Atomic Mutant Man, who would be well suited to play Basketball jumps from a tree");
	Thread.sleep(MS_1000);
	System.out.println("That Cannon in your chest doesn't frighten me!");
	
		boolean forestCompleted = Combat.oneEnemy(player, EnemyFactory.createEqualEnemy(player), scanner);
		
		return forestCompleted;
	}

}
