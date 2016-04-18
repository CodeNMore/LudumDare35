package dev.codenmore.ld35.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import dev.codenmore.ld35.assets.Assets;
import dev.codenmore.ld35.status.StatusReport;

public class BadEntity extends Entity {
	
	private Animation anim;
	private float animTimer = 0.0f;

	public BadEntity(EntityManager manager, float speed) {
		super(manager, Assets.getRegion("bad1.1"), speed);
		if(MathUtils.randomBoolean()){
			anim = new Animation(0.2f, new TextureRegion[]{
					Assets.getRegion("bad1.1"), Assets.getRegion("bad1.2"),
					Assets.getRegion("bad1.3"), Assets.getRegion("bad1.4")
			});
		}else{
			anim = new Animation(0.2f, new TextureRegion[]{
					Assets.getRegion("bad2.1"), Assets.getRegion("bad2.2"),
					Assets.getRegion("bad2.3"), Assets.getRegion("bad2.4")
			});
		}
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
		manager.getLevel().getScore().onGood();
	}
	
	@Override
	public void onTake(){
		StatusReport.suckedUpBad();
		manager.getLevel().getScore().onError();
		Assets.playSound("intube");
		Assets.playSound("wrong");
	}

}
