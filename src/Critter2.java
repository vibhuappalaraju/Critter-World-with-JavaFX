

package critterworld;
import critterworld.Critter.CritterShape;


public class Critter2 extends Critter {


	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	//constructor for Critter2, puts in 2 into each gene from 0-7 on which direction they'll go
	//dir holds a random integer up to 8
	public Critter2() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}

	//fight method for Critter 2, will try to not fight opponent no matter what
	@Override
	public boolean fight(String opponent) {
		return false;
	}
	
	//doTimeStep for Critter2, will walk in direction of dir mod 8
	public void doTimeStep() {
		String lookString = look(dir, true);
		if(lookString==null){
			if(dir > 8){
				dir = dir % 8;
			}
			run(dir);
		}
		else{
			if(dir > 8){
				dir = dir % 8;
			}
			walk(dir);
		}
		
	}
	
	//creates the shape of the critter
	@Override
	public CritterShape viewShape() { return CritterShape.SQUARE; }

	//chooses which outline color for the critter to display
	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.GOLD; }
	
	//chooses which fill color for the critter to display
	@Override
	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.MISTYROSE; }
	
	//runstats of Critter2, shows what percentage Critter1 will try to go in a direction
	public static void runStats(java.util.List<Critter> critter2) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : critter2) {
			Critter2 c = (Critter2) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + critter2.size() + " total MyCritter1s    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * critter2.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * critter2.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * critter2.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * critter2.size()) + "% left   ");
		System.out.println();
	}
	
	//Critter2 will print a 2 to show where it exists
	public String toString() {
		return "2";
	}
	
}
