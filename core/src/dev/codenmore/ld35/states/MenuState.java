package dev.codenmore.ld35.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dev.codenmore.ld35.Main;
import dev.codenmore.ld35.assets.Assets;

public class MenuState extends State {
	
	// Storage
	private TextureRegion bg, title;
	private float titlePos = 55.0f;
	private float titleMax = titlePos, titleMin = 49.0f, titleSpeed = 4.0f;
	private boolean titleDirDown = true, alphaDirDown = false;
	private float alpha = 0.0f, alphaSpeed = 0.75f, delayTimer = 0.0f, delayMin = 1.0f;
	
	public MenuState(Main main){
		super(main);
		bg = Assets.getRegion("logo");
		title = Assets.getRegion("title");
	}

	@Override
	public void tick(float delta) {
		// Move the title
		if(titleDirDown){
			titlePos -= titleSpeed * delta;
			if(titlePos <= titleMin){
				titlePos = titleMin;
				titleDirDown = false;
			}
		}else{
			titlePos += titleSpeed * delta;
			if(titlePos >= titleMax){
				titlePos = titleMax;
				titleDirDown = true;
			}
		}
		// Change the start alpha
		if(alphaDirDown){
			alpha -= alphaSpeed * delta;
			if(alpha <= 0.0f){
				alpha = 0.0f;
				alphaDirDown = false;
			}
		}else{
			alpha += alphaSpeed * delta;
			if(alpha >= 1.0f){
				alpha = 1.0f;
				alphaDirDown = true;
			}
		}
		// Check for play
		delayTimer += delta;
		if(delayTimer > delayMin && (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.SPACE))){
			State.pushState(new TutorialState(main));
			delayTimer = 0.0f;
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		{
			batch.draw(bg, 0, 0, Main.WIDTH, Main.HEIGHT);
			batch.draw(title, 12, titlePos, 179, 39);
			Assets.setFontColor(1.0f, 1.0f, 1.0f, alpha);
			Assets.setFontScale(0.9f);
			Assets.drawString(batch, "PRESS  [ SPACE ]  OR TAP THE SCREEN TO PLAY!", 4, 44);
		}
		batch.end();
	}

}
