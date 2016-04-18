package dev.codenmore.ld35.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dev.codenmore.ld35.Main;
import dev.codenmore.ld35.assets.Assets;

public class ScoreState extends State {
	
	// Storage
	private TextureRegion bg;
	private float delayTimer = 0.0f, delayMin = 1.0f;
	private long score;
	private int highStreak, wave;
	
	public ScoreState(Main main, long score, int highStreak, int wave){
		super(main);
		bg = Assets.getRegion("logo");
		this.score = score;
		this.highStreak = highStreak;
		this.wave = wave;
	}

	@Override
	public void tick(float delta) {
		// Check for play
		delayTimer += delta;
		if(delayTimer > delayMin && (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.SPACE))){
			State.popState();
			delayTimer = 0.0f;
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		{
			batch.draw(bg, 0, 0, Main.WIDTH, Main.HEIGHT);
			
			Assets.setFontColor(Color.RED);
			Assets.setFontScale(0.9f);
			Assets.drawString(batch, "YOU  SCORED:  " + score, 71, 96);
			Assets.drawString(batch, "YOUR  HIGHEST  STREAK  WAS:  " + highStreak, 4, 86);
			Assets.drawString(batch, "YOU  MADE  IT  TO  WAVE:  " + wave, 28, 76);
			Assets.setFontColor(Color.BLUE);
			Assets.drawString(batch, "TRY NOT TO DROWN TOO MANY SHEEP NEXT TIME!", 4, 62);
			Assets.setFontColor(Color.WHITE);
			Assets.drawString(batch, "PRESS  [ SPACE ]  OR TAP THE SCREEN TO\n                 GO  TO  THE  MAIN  MENU!", 17, 48);
		}
		batch.end();
	}

}
