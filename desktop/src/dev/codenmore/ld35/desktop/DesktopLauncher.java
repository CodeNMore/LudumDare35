package dev.codenmore.ld35.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import dev.codenmore.ld35.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		// Window setup
		config.title = Main.TITLE;
		config.width = Main.WIDTH * Main.WINSCALE;
		config.height = Main.HEIGHT * Main.WINSCALE;
		
		// Texture packer stuff
//		Settings s = new Settings();
//		s.fast = false;
//		s.combineSubdirectories = true;
//		s.duplicatePadding = false;
//		s.edgePadding = false;
//		s.paddingX = 0;
//		s.paddingY = 0;
//		s.pot = true;
//		TexturePacker.process(s, "../core/assets/raw", "../core/assets/textures", "textures");
		
		new LwjglApplication(new Main(), config);
	}
}
