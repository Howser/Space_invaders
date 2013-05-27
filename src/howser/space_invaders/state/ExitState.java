package howser.space_invaders.state;

import howser.space_invaders.Game;
import howser.space_invaders.gfx.Colour;
import howser.space_invaders.gfx.Frame;
import howser.space_invaders.state.messages.StateMessage;

public class ExitState extends BaseState{

	Game game;
	public ExitState(String name, StateManager stateManager, Game game) {
		super(name, stateManager);
		this.game = game;
	}

	public void tick() {
		
	}

	public void render(Frame frame) {
		frame.clear(Colour.RED);
	}

	public void onEnter() {
		game.stop();
	}

	public void onExit() {
		
	}

	public void reset() {
		
	}


	@Override
	public void receiveMessage(StateMessage message) {
		// TODO Auto-generated method stub
		
	}

}
