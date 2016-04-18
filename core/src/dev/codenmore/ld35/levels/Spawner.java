package dev.codenmore.ld35.levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import dev.codenmore.ld35.assets.Assets;
import dev.codenmore.ld35.entities.BadEntity;
import dev.codenmore.ld35.entities.Entity;
import dev.codenmore.ld35.entities.GoodEntity;

public class Spawner {
	
	// Constants
	public static final int WIDTH = 22, HEIGHT = 22;
	// Level
	private Level level;
	private TextureRegion texture;
	// Generation
	private int waveNumber = 0;
	private int enemiesToGenerate = 0;
	private float minEnemySpeed, maxEnemySpeed, minWait, maxWait;
	private float timer = 0, currentWait = 0;
	private boolean waitForNext = false;
	
	public Spawner(Level level){
		this.level = level;
		texture = Assets.getRegion("spawner");
		waveNumber = 1;
		nextWave();
	}
	
	public void tick(float delta){
		timer += delta;
		if(waitForNext && timer > currentWait){
			waitForNext = false;
			Assets.playSound("nextwave");
			nextWave();
		}else if(timer > currentWait){
			Entity e;
			if(MathUtils.randomBoolean((float) (0.2 * Math.sin(2 * waveNumber) + 0.5))){
				e = new GoodEntity(level.getEntityManager(), MathUtils.random(minEnemySpeed, maxEnemySpeed));
			}else{
				e = new BadEntity(level.getEntityManager(), MathUtils.random(minEnemySpeed, maxEnemySpeed));
			}
			level.getEntityManager().addEntity(e);
			Assets.playSound("spawn");
			nextEnemy();
		}
	}
	
	private void nextEnemy(){
		timer = 0.0f;
		currentWait = MathUtils.random(minWait, maxWait);
		enemiesToGenerate--;
		if(enemiesToGenerate <= 0){
			currentWait = 5.0f;
			waveNumber++;
			waitForNext = true;
		}
	}
	
	private void nextWave(){
		if(enemiesToGenerate > 0)
			return;
		
		enemiesToGenerate = (int) (8 * Math.sqrt(waveNumber));
		minEnemySpeed = (float) (Math.pow(waveNumber, 1/2.8f) * 30.0f);
		maxEnemySpeed = (float) (minEnemySpeed + Math.sqrt(waveNumber) / 2);
		minWait = (float) (Math.pow(Math.E, -waveNumber/15) * Math.sin(waveNumber * 2 * Math.PI) + 1 - waveNumber / 50);
		minWait = Math.max(minWait, 0.2f);
		maxWait = minWait + 4 / waveNumber;
		maxWait = Math.min(maxWait, 2.5f);
		
		nextEnemy();
	}
	
	public void render(SpriteBatch batch){
		batch.draw(texture, 0, Level.GROUND_LEVEL + 16, WIDTH, HEIGHT);
	}
	
	public Level getLevel(){
		return level;
	}
	
	public int getWaveNumber(){
		return waveNumber;
	}

}
