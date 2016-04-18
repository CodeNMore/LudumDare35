package dev.codenmore.ld35.status;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codenmore.ld35.assets.Assets;

public class StatusReport {

	private static final String BAD = "YOU SUCKED UP  A  BAD  SHEEP!",
			LET = "YOU  LET A  GOOD   SHEEP   DIE!";

	private static String statusMsg = "";
	private static float alpha = 0, speed = 1.5f, direction = 1;

	private StatusReport() {
	}

	public static void tick(float delta) {
		if (statusMsg == "")
			return;

		alpha += speed * direction * delta;
		if (direction > 0 && alpha >= 1)
			direction = -1;
		if (direction < 0 && alpha <= 0)
			statusMsg = "";
	}

	public static void render(SpriteBatch batch) {
		if (statusMsg == "")
			return;

		Assets.setFontColor(1.0f, 0.0f, 0.0f, alpha);
		Assets.setFontScale(1.0f);
		Assets.drawString(batch, statusMsg, 1, 16);
	}
	
	public static void reset(){
		statusMsg = "";
		alpha = 0;
		direction = 1;
	}

	public static void suckedUpBad() {
		if (statusMsg != "" && (alpha > 0.2f || direction > 0))
			return;
		statusMsg = BAD;
		alpha = 0;
		direction = 1;
	}

	public static void letGoodGo() {
		if (statusMsg != "" && (alpha > 0.2f || direction > 0))
			return;
		statusMsg = LET;
		alpha = 0;
		direction = 1;
	}

}
