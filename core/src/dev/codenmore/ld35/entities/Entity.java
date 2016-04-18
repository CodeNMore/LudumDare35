package dev.codenmore.ld35.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import dev.codenmore.ld35.Main;
import dev.codenmore.ld35.assets.Assets;
import dev.codenmore.ld35.levels.Level;
import dev.codenmore.ld35.levels.Press;
import dev.codenmore.ld35.levels.Spawner;

public abstract class Entity {

	// Globals
	public static final int ENTITY_WIDTH = 9, ENTITY_HEIGHT = 9,
			ENTITY_START_HEIGHT = Level.GROUND_LEVEL + 17;
	// Constants
	private static final float ROT_INC = -220;
	// Storage
	protected EntityManager manager;
	protected TextureRegion texture;
	protected float speed;
	protected float x, y, vy;
	protected float width, height, rotation;
	protected boolean inSucker = false, suckerInited = false, doRotate = false, splashMade = false;
	protected boolean penalty = false;

	public Entity(EntityManager manager, TextureRegion texture, float speed) {
		this.manager = manager;
		this.texture = texture;
		this.speed = speed;
		rotation = 0;
		x = 0;
		y = ENTITY_START_HEIGHT;
		vy = 0;
		width = ENTITY_WIDTH;
		height = ENTITY_HEIGHT;
	}
	
	public abstract void onLet();
	
	public abstract void onTake();

	public boolean tick(float delta) {
		if (!inSucker) {
			// Horizontal movement
			x += speed * delta;
			// Apply gravity always
			vy -= Level.GRAVITY * delta;
			// Check for sucker gravity
			if (manager.getLevel().getPress().isSucking()
					&& x >= Press.SUCKER_X
					&& x < Press.SUCKER_X + Press.SUCKER_LENGTH) {
				vy += Press.SUCKER_FORCE * delta;
				x -= speed * delta * 0.2f;
			}
			// Launch if needed
			if(x > Level.CLIFF_X && !doRotate){
				// Start rotation
				doRotate = true;
				// Launch somehow
				if(y < Level.GROUND_LEVEL + 3)
					vy += MathUtils.random(50.0f, 260.0f) * delta;
			}
			// Apply vertical velocity
			y += vy;
			if(x < Spawner.WIDTH){
				if (y < ENTITY_START_HEIGHT) {
					vy = 0.0f;
					y = ENTITY_START_HEIGHT;
				}
			}else if (y < Level.GROUND_LEVEL + 1 && x < Level.CLIFF_X) {
				vy = 0.0f;
				y = Level.GROUND_LEVEL + 1;
			}
			// Check if now in sucker
			if (manager.getLevel().getPress().isSucking()
					&& x >= Press.SUCKER_X
					&& x < Press.SUCKER_X + Press.SUCKER_LENGTH
					&& y >= Press.SUCKER_THRESH){
				inSucker = true;
				onTake();
			}
			if(doRotate){
				rotation += ROT_INC * delta;
			}
			if(!splashMade && x > Level.CLIFF_X && y + height / 2 < Level.GROUND_LEVEL){
				splashMade = true;
				int offset = manager.getLevel().getWater().getOffset();
				manager.getLevel().getWater().makeSplash((int) (offset - (Main.WIDTH - x)), vy * 6);
				Assets.playSound("splash", 0.6f);
			}
		} else {
			if (!suckerInited) {
				x = Press.SUCKER_X + 9;
				y = Press.SUCKER_THRESH;
				suckerInited = true;
			}
			y += Press.SUCKER_FORCE * delta;
			if (y > Press.SUCKER_TOP)
				y = Press.SUCKER_TOP;
			if (y == Press.SUCKER_TOP) {
				x += Press.SUCKER_FORCE * delta;
			}
		}
		// Check for let
		if(!penalty && x > Level.CLIFF_X && y < Level.GROUND_LEVEL){
			penalty = true;
			onLet();
		}

		if (x > Main.WIDTH)
			return true;
		return false;
	}

	public void render(SpriteBatch batch) {
		batch.draw(texture, x, y, width / 2, height / 2, width, height, 1.0f, 1.0f, rotation);
	}

}
