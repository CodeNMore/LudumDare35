package dev.codenmore.ld35.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codenmore.ld35.Main;
import dev.codenmore.ld35.assets.Assets;

public class LoadingState extends State {
	
	// Information
	private boolean createMenu;
	private static final float MIN_TIME = 3.0f;
	private float timer = 0.0f;
	// Image
	private Texture splash;
	
	public LoadingState(Main main, boolean createMenu){
		super(main);
		this.createMenu = createMenu;
	}

	@Override
	public void tick(float delta) {
		timer += delta;
		if(Assets.step() && timer >= MIN_TIME){
			if(createMenu)
				State.swapState(new MenuState(main));
			else
				State.popState();
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		if(splash != null)
			batch.draw(splash, 0, 0, Main.WIDTH, Main.HEIGHT);
		batch.end();
	}
	
	@Override
	public void show(){
		splash = new Texture("textures/splash.png");
	}
	
	@Override
	public void hide(){
		splash.dispose();
	}

}
