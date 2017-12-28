package critterworld;


import critterworld.Critter.CritterShape;

public class Critter4 extends Critter {
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	//Athlete Critter where the critter runs a lot or walks depending on his current direction
	public Critter4() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	
	@Override
	public void doTimeStep() {
		//if the direction is greater than 5, then the critter will run a lot but if not critter will walk once
		if (dir > 5) {
			run(dir);
			run(dir);
			run(dir);

		} else {
			walk(dir);
		}

	}

	@Override
	public boolean fight(String opponent) {
		run(dir);
		run(dir);// this athlete critter will try its best to runaway and not fight
		walk(dir);
		return false;
	}

	@Override
	public String toString () {
		return "4";
	}
	@Override
	public CritterShape viewShape() { return CritterShape.STAR; }

	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.LIGHTBLUE; }
	@Override
	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.BLACK; }

	

	
	public static void runStats(java.util.List<Critter> crit3) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : crit3) {
			Critter4 c = (Critter4) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + crit3.size() + " total Critter4    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * crit3.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * crit3.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * crit3.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * crit3.size()) + "% left   ");
		System.out.println();
	}
	
}
