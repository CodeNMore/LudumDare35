package dev.codenmore.ld35.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codenmore.ld35.Main;
import dev.codenmore.ld35.levels.Level;
import dev.codenmore.ld35.status.StatusReport;

public class GameState extends State {
	
	// The level
	protected Level level;
	
	public GameState(Main main){
		super(main);
		level = new Level(this);
	}

	@Override
	public void tick(float delta) {
		level.tick(delta);
		StatusReport.tick(delta);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		{
			level.render(batch);
			StatusReport.render(batch);
		}
		batch.end();
	}

}
