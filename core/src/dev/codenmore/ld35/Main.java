package dev.codenmore.ld35;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import dev.codenmore.ld35.assets.Assets;
import dev.codenmore.ld35.states.LoadingState;
import dev.codenmore.ld35.states.State;

public class Main extends ApplicationAdapter {
	
	// Globals
	public static final String TITLE = "Sheep Shift";
	public static final int WIDTH = 200, HEIGHT = 100, WINSCALE = 4;
	// Base rendering stuff
	private OrthographicCamera cam;
	private SpriteBatch batch;
	private Viewport viewport;
	
	public Main(){}
	
	@Override
	public void create(){
		// Graphics stuff
		batch = new SpriteBatch();
		cam = new OrthographicCamera(WIDTH, HEIGHT);
		cam.setToOrtho(false);
		viewport = new FitViewport(WIDTH, HEIGHT, cam);
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		// Resize desktop window
		if(Gdx.app.getType() == ApplicationType.Desktop)
			Gdx.graphics.setWindowedMode(WIDTH * WINSCALE, HEIGHT * WINSCALE);
		// States
		State.pushState(new LoadingState(this, true));
	}
	
	@Override
	public void render(){
		// Reset
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		// Check for mute
		if(Gdx.input.isKeyJustPressed(Keys.M))
			Assets.toggleMusic();
		// Render
		if(State.peekState() != null)
			State.peekState().tick(Gdx.graphics.getDeltaTime());
		if(State.peekState() != null)
			State.peekState().render(batch);
	}
	
	@Override
	public void dispose(){
		// States
		while(State.peekState() != null)
			State.popState();
		// Rendering
		batch.dispose();
		// Assets
		Assets.dispose();
	}
	
	@Override
	public void resize(int width, int height){
		viewport.update(width, height);
	}
	
	@Override
	public void pause(){
		
	}
	
	@Override
	public void resume(){
		
	}
	
	public OrthographicCamera getCamera(){
		return cam;
	}
	
	public Viewport getViewport(){
		return viewport;
	}
	
}
