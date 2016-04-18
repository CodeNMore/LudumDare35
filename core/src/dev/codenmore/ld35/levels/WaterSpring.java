package dev.codenmore.ld35.levels;



public class WaterSpring {
	
	// Constants
	private static final float K = 0.03f, D = 0.01f, MASS = 1.1f;
	// Information
	private float basePos, pos, vel;
	
	public WaterSpring(float pos){
		this.pos = pos;
		this.vel = 0.0f;
		this.basePos = pos;
	}
	
	public void tick(float delta){
		vel += ((-K * (pos - basePos)) / MASS) - D * vel;
		pos += vel;
	}
	
	public void incPos(float amt){
		pos += amt;
	}
	
	public void incVel(float amt){
		vel += amt;
	}
	
	public float getPos(){
		return pos;
	}

}
