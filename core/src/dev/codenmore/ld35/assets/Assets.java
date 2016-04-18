package dev.codenmore.ld35.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;

public class Assets {
	
	// Management
	private static AssetManager manager;
	private static boolean inited = false, posted = false;
	// Raw storage
	private static final String PTH_ATLAS = "textures/textures.atlas",
						  PTH_FONT  = "font/font.fnt",
						  PTH_MUSIC = "music/ocean.wav";
	private static final String[] PTH_SOUNDS = {
		"sounds/splash.wav",
		"sounds/sucker.wav",
		"sounds/intube.wav",
		"sounds/spawn.wav",
		"sounds/nextwave.wav",
		"sounds/wrong.wav"
	};
	private static TextureAtlas atlas;
	private static BitmapFont font;
	private static Music music;
	// Data storage
	private static ObjectMap<String, TextureRegion> regions;
	private static ObjectMap<String, Sound> sounds;
	
	private Assets(){}
	
	private static void init(){
		manager = new AssetManager();
		regions = new ObjectMap<String, TextureRegion>();
		sounds = new ObjectMap<String, Sound>();
		manager.load(PTH_ATLAS, TextureAtlas.class);
		manager.load(PTH_FONT, BitmapFont.class);
		manager.load(PTH_MUSIC, Music.class);
		for(String p : PTH_SOUNDS)
			manager.load(p, Sound.class);
	}
	
	private static void post(){
		atlas = manager.get(PTH_ATLAS, TextureAtlas.class);
		font = manager.get(PTH_FONT, BitmapFont.class);
		music = manager.get(PTH_MUSIC, Music.class);
		toggleMusic();
		for(String p : PTH_SOUNDS){
			sounds.put(p.substring(7, p.indexOf(".wav")), manager.get(p, Sound.class));
		}
	}
	
	public static void dispose(){
		manager.dispose();
	}
	
	// Sounds
	
	public static void playSound(String name){
		playSound(name, 1.0f);
	}
	
	public static void playSound(String name, float volume){
		sounds.get(name).play(volume);
	}
	
	public static void startSound(String name){
		sounds.get(name).loop(0.7f);
	}
	
	public static void stopSound(String name){
		sounds.get(name).stop();
	}
	
	// Music
	
	public static void toggleMusic(){
		if(music.isPlaying())
			music.stop();
		else{
			music.setLooping(true);
			music.setVolume(0.2f);
			music.play();
		}
	}
	
	// Font
	
	public static BitmapFont getFont(){
		return font;
	}
	
	public static void setFontScale(float s){
		font.getData().setScale(s);
	}
	
	public static void setFontColor(Color c){
		font.setColor(c);
	}
	
	public static void setFontColor(float r, float g, float b, float a){
		font.setColor(r, g, b, a);
	}
	
	public static void drawString(SpriteBatch batch, String msg, float x, float y){
		font.draw(batch, msg, x, y);
	}
	
	// Textures
	
	public static TextureRegion getRegion(String name){
		if(regions.containsKey(name))
			return regions.get(name);
		regions.put(name, atlas.findRegion(name));
		return regions.get(name);
	}
	
	// Loading
	
	public static boolean step(){
		if(!inited){
			init();
			inited = true;
		}
		if(manager.update()){
			if(!posted){
				post();
				posted = true;
			}
			return true;
		}
		return false;
	}
	
	public float getProgress(){
		return manager.getProgress();
	}

}
