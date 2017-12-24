public class Day20 {
	
	public static void main(String[] args) {
		GPU gpu = new GPU();
		gpu.simulate(15000);
		gpu.printParticleWithClosestDistance();
		gpu.printParticlesLeft();
	}
}
