public class Particle {
	public int id;
	// positions.
	private long xPos;
	private long yPos;
	private long zPos;
	// velocities.
	private long xV;
	private long yV;
	private long zV;
	// accelerations.
	private long xA;
	private long yA;
	private long zA;
	
	public Particle(int id,long xPos, long yPos, long zPos, long xV, long yV, long zV, long xA, long yA, long zA) {
		this.id = id;
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		
		this.xV = xV;
		this.yV = yV;
		this.zV = zV;
		
		this.xA = xA;
		this.yA = yA;
		this.zA = zA;
	}
	
	public long getXPos() { return xPos; }
	public long getYPos() { return yPos; }
	public long getZPos() { return zPos; }
	
	public String positionString() {
		return Long.toString(xPos)+Long.toString(yPos)+Long.toString(zPos);
	}
	
	public long getManhattanDistance() {
		return Math.abs(xPos) + Math.abs(yPos) + Math.abs(zPos);
	}
	
	public void update() {
		xV += xA;
		yV += yA;
		zV += zA;
		
		xPos += xV;
		yPos += yV;
		zPos += zV;
	}
	
}
