package dev.codenmore.ld35.states;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.codenmore.ld35.Main;

public abstract class State {
	
	private static Stack<State> states = new Stack<State>();
	
	public static void pushState(State s){
		if(peekState() != null)
			peekState().hide();
		states.push(s);
		s.show();
	}
	
	public static void popState(){
		if(states.isEmpty())
			return;
		states.pop().hide();
		if(peekState() != null)
			peekState().show();
	}
	
	public static void swapState(State s){
		if(!states.isEmpty())
			states.pop().hide();
		states.push(s);
		s.show();
	}
	
	public static State peekState(){
		if(states.isEmpty())
			return null;
		return states.peek();
	}
	
	// Class
	
	protected Main main;
	
	public State(Main main){this.main = main;}
	
	public abstract void tick(float delta);
	
	public abstract void render(SpriteBatch batch);
	
	public void show(){}
	
	public void hide(){}
	
	public Main getMain(){
		return main;
	}

}
