package dev.codenmore.ld35.levels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dev.codenmore.ld35.assets.Assets;

public class Water {
	
	// Constants
	private static final float S = 0.03f;
	// Information
	private TextureRegion texture;
	private float x;
	private int width;
	// Water springs
	private WaterSpring[] springs;
	
	public Water(float x, int width, int height){
		this.x = x;
		this.width = width;
		springs = new WaterSpring[width];
		for(int i = 0;i < springs.length;++i)
			springs[i] = new WaterSpring(height);
		texture = Assets.getRegion("color");
		
		makeSplash(width / 2, 4.0f);
	}
	
	public void tick(float delta){
		// Update springs
		for(int i = 0;i < springs.length;++i){
			springs[i].tick(delta);
		}
		// Simulate pulling
		float[] left = new float[springs.length];
		float[] right = new float[springs.length];
		for(int j = 0;j < 8;++j){
			for(int i = 0;i < springs.length;++i){
				if(i > 0){
					left[i] = S * (springs[i].getPos() - springs[i - 1].getPos());
					springs[i - 1].incVel(left[i]);
				}
				if(i < springs.length - 1){
					right[i] = S * (springs[i].getPos() - springs[i + 1].getPos());
					springs[i + 1].incVel(right[i]);
				}
			}
			for(int i = 0;i < springs.length;++i){
				if(i > 0)
					springs[i - 1].incPos(left[i]);
				if(i < springs.length - 1)
					springs[i + 1].incPos(right[i]);
			}
		}
	}
	
	public void makeSplash(int idx, float amt){
		if(idx < 0 || idx >= springs.length)
			return;
		springs[idx].incPos(amt);
	}
	
	public void render(SpriteBatch batch){
		batch.setColor(0.0f, 0.0f, 1.0f, 0.7f);
		{
			for(int i = 0;i < springs.length;++i){
				batch.draw(texture, x + i, 0, 1, springs[i].getPos());
			}
		}
		batch.setColor(Color.WHITE);
	}
	
	public int getOffset(){
		return width;
	}

}
