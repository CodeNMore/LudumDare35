package dev.codenmore.ld35.levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dev.codenmore.ld35.Main;
import dev.codenmore.ld35.assets.Assets;
import dev.codenmore.ld35.entities.EntityManager;
import dev.codenmore.ld35.levels.score.Score;
import dev.codenmore.ld35.states.GameState;
import dev.codenmore.ld35.states.ScoreState;
import dev.codenmore.ld35.states.State;
import dev.codenmore.ld35.status.StatusReport;

public class Level {
	
	// Globals
	public static final int GROUND_LEVEL = 26, CLIFF_X = 142;
	public static final float GRAVITY = 10.0f;
	// Information
	private GameState game;
	private TextureRegion background;
	private Press press;
	private Spawner spawner;
	private EntityManager entityManager;
	private Water water;
	private Score score;
	
	public Level(GameState game){
		this.game = game;
		background = Assets.getRegion("bg");
		press = new Press(this);
		spawner = new Spawner(this);
		entityManager = new EntityManager(this);
		water = new Water(CLIFF_X + 1, Main.WIDTH - CLIFF_X, GROUND_LEVEL - 1);
		score = new Score(this);
	}
	
	public void tick(float delta){
		press.tick(delta);
		spawner.tick(delta);
		entityManager.tick(delta);
		water.tick(delta);
		score.tick(delta);
	}
	
	public void render(SpriteBatch batch){
		batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
		press.backRender(batch);
		entityManager.render(batch);
		spawner.render(batch);
		press.render(batch);
		water.render(batch);
		score.render(batch);
	}
	
	public void lose(){
		StatusReport.reset();
		Assets.stopSound("sucker");
		State.swapState(new ScoreState(game.getMain(), score.getScore(), score.getHighStreak(), spawner.getWaveNumber()));
	}
	
	// Getters

	public GameState getGame() {
		return game;
	}

	public Press getPress() {
		return press;
	}
	
	public Water getWater(){
		return water;
	}

	public Spawner getSpawner() {
		return spawner;
	}
	
	public Score getScore(){
		return score;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
