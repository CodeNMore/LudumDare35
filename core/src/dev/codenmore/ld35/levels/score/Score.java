package dev.codenmore.ld35.levels.score;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codenmore.ld35.assets.Assets;
import dev.codenmore.ld35.levels.Level;

public class Score {
	
	private Level level;
	private long score = 0;
	private int streak = 0;
	private int lives = 3;
	private int highestStreak = 0;
	
	public Score(Level level){
		this.level = level;
	}
	
	public void tick(float delta){}
	
	public void render(SpriteBatch batch){
		Assets.setFontColor(Color.WHITE);
		Assets.setFontScale(0.7f);
		Assets.drawString(batch, "SCORE:  " + score, 7, 100);
		Assets.drawString(batch, "STREAK:  " + streak, 3, 92);
		Assets.drawString(batch, "TRIES:  " + lives, 7, 84);
		Assets.drawString(batch, "WAVE:  " + level.getSpawner().getWaveNumber(), 11, 76);
	}
	
	public void onGood(){
		incStreak(1);
		score += 100 * level.getSpawner().getWaveNumber();
	}
	
	public void onError(){
		if(streak > highestStreak)
			highestStreak = streak;
		score += streak * 2;
		streak = 0;
		incLife(-1);
	}
	
	public void incStreak(int amt){
		streak += amt;
	}
	
	public void incLife(int amt){
		lives += amt;
		if(lives <= 0)
			level.lose();
	}
	
	public long getScore(){
		return score;
	}
	
	public int getHighStreak(){
		return highestStreak;
	}

}
