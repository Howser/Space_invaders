package howser.space_invaders.state;

import howser.space_invaders.Game;
import howser.space_invaders.gfx.Frame;
import howser.space_invaders.state.messages.StateMessage;

import java.awt.event.KeyListener;
import java.util.ArrayList;

public class StateManager {
	
	private int currentState;
	private ArrayList<BaseState> states;
	
	private Game game;
	
	public StateManager(Game game){
		states = new ArrayList<BaseState>();
		currentState = 0;
		this.game = game;
	}
	
	public void addState(BaseState state){
		states.add(state);
	}
	
	public BaseState getState(String state){
		for (int i = 0; i < states.size(); i++){
			if (states.get(i).name == state){
				return states.get(i);
			}
		}
		return null;
	}
	
	public void changeState(String state){
		for (int i = 0; i < states.size(); i++){
			if (states.get(i).name == state){
				states.get(currentState).onExit();
				currentState = i;
				states.get(i).onEnter();
				return;
			}
		}
	}
	
	public void tick(){
		states.get(currentState).tick();
	}
	
	public void render(Frame frame){
		states.get(currentState).render(frame);
	}
	
	public void clearStates(){
		states.clear();
	}
	
	public void resetState(String state){
		for (int i = 0; i< states.size(); i++){
			if (states.get(i).name == state){
				states.get(i).reset();
			}
		}
	}
	
	public void sendMessage(StateMessage message){
		for (BaseState s : states){
			if (s.name == message.recipient){
				s.receiveMessage(message);
			}
		}
	}
	
	public void addKeyListener(KeyListener e){
		game.addKeyListener(e);
	}
}
