package dev.codenmore.ld35.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dev.codenmore.ld35.assets.Assets;
import dev.codenmore.ld35.status.StatusReport;

public class GoodEntity extends Entity {
	
	private Animation anim;
	private float animTimer = 0.0f;

	public GoodEntity(EntityManager manager, float speed) {
		super(manager, Assets.getRegion("good1.1"), speed);
		anim = new Animation(0.2f, new TextureRegion[]{
				Assets.getRegion("good1.1"), Assets.getRegion("good1.2"),
				Assets.getRegion("good1.3"), Assets.getRegion("good1.4")
		});
	}
	
	@Override
	public boolean tick(float delta){
		boolean ret = super.tick(delta);
		animTimer += delta;
		texture = anim.getKeyFrame(animTimer, true);
		return ret;
	}
	
	@Override
	public void onLet(){
		StatusReport.letGoodGo();
		manager.getLevel().getScore().onError();
		Assets.playSound("wrong");
	}
	
	@Override
	public void onTake(){
		manager.getLevel().getScore().onGood();
		Assets.playSound("intube");
	}

}
