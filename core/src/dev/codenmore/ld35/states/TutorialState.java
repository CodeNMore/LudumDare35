package dev.codenmore.ld35.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dev.codenmore.ld35.Main;
import dev.codenmore.ld35.assets.Assets;

public class TutorialState extends State {

	private TextureRegion bg;
	private float delayTimer = 0.0f, delayMin = 1.5f;

	public TutorialState(Main main) {
		super(main);
		bg = Assets.getRegion("tutorial");
	}

	@Override
	public void tick(float delta) {
		// Check for play
		delayTimer += delta;
		if (delayTimer > delayMin
				&& (Gdx.input.justTouched() || Gdx.input
						.isKeyJustPressed(Keys.SPACE))) {
			State.swapState(new GameState(main));
			delayTimer = 0.0f;
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		{
			batch.draw(bg, 0, 0, Main.WIDTH, Main.HEIGHT);
			Assets.setFontColor(Color.GREEN);
			Assets.setFontScale(0.7f);
			Assets.drawString(batch, "GOOD  SHEEP", 2, 14);
			Assets.drawString(batch, "BAD  SHEEP", 60, 10);
			Assets.getFont().getData().setLineHeight(10.0f);
			Assets.drawString(batch, "HOLD   [ SPACE ]\nOR  PRESS  THE\nSCREEN  TO  SUCK\nUP GOOD SHEEP\nINTO  THE  TUBE ,\nAND LET BAD\nSHEEP  FALL\nINTO  THE  WATER!", 142, 86);
			Assets.drawString(batch, "PRESS   [ SPACE ]  OR  TAP  THE", 10, 84);
			Assets.drawString(batch, "SCREEN  TO   BEGIN", 26, 77);
		}
		batch.end();
	}

}
