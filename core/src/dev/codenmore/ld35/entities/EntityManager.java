package dev.codenmore.ld35.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import dev.codenmore.ld35.levels.Level;

public class EntityManager {
	
	// Information
	private Level level;
	private Array<Entity> entities, toRemove;
	
	public EntityManager(Level level){
		this.level = level;
		entities = new Array<Entity>();
		toRemove = new Array<Entity>();
	}
	
	public void tick(float delta){
		for(Entity e : entities)
			if(e.tick(delta))
				toRemove.add(e);
		
		for(Entity e : toRemove){
			entities.removeValue(e, true);
		}
		toRemove.clear();
	}
	
	public void render(SpriteBatch batch){
		for(Entity e : entities)
			e.render(batch);
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	public Level getLevel(){
		return level;
	}

}
