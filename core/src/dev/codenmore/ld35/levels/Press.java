package dev.codenmore.ld35.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dev.codenmore.ld35.Main;
import dev.codenmore.ld35.assets.Assets;

public class Press {

	// Constants
	private static final int WIDTH = 79, HEIGHT = 74;
	// Globals
	public static final int SUCKER_X = Main.WIDTH - WIDTH - 2, SUCKER_LENGTH = 16,
			SUCKER_FORCE = 20, SUCKER_THRESH = Level.GROUND_LEVEL + 18,
			SUCKER_TOP = SUCKER_THRESH + 43;
	// Level
	private Level level;
	private TextureRegion texture, backTexture;
	// The sucker part
	private boolean sucking = false, didStartSound = false;
	private Animation suckAnim;
	private TextureRegion suckTexture;
	private float animTimer = 0.0f;

	public Press(Level level) {
		this.level = level;
		texture = Assets.getRegion("press");
		backTexture = Assets.getRegion("press_back");
		suckAnim = new Animation(0.1f, new TextureRegion[]{Assets.getRegion("sucker1"),
				Assets.getRegion("sucker2")});
	}

	public void tick(float delta) {
		// The sucker
		animTimer += delta;
		suckTexture = suckAnim.getKeyFrame(animTimer, true);
		
		// Input
		if(Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isButtonPressed(0)){
			sucking = true;
			if(!didStartSound){
				didStartSound = true;
				Assets.startSound("sucker");
			}
		}else{
			sucking = false;
			if(didStartSound){
				didStartSound = false;
				Assets.stopSound("sucker");
			}
		}
	}
	
	public void backRender(SpriteBatch batch){
		batch.draw(backTexture, Main.WIDTH - WIDTH + 1, Level.GROUND_LEVEL, 20, 15);
	}

	public void render(SpriteBatch batch) {
		batch.draw(texture, Main.WIDTH - WIDTH, Level.GROUND_LEVEL, WIDTH, HEIGHT);
		// Sucker
		if(sucking)
			batch.draw(suckTexture, Main.WIDTH - WIDTH + 1, Level.GROUND_LEVEL + 3, SUCKER_LENGTH + 2, SUCKER_LENGTH - 6);
	}
	
	public Level getLevel(){
		return level;
	}
	
	public boolean isSucking(){
		return sucking;
	}

}
