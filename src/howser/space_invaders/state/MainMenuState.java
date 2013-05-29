package howser.space_invaders.state;

import howser.space_invaders.InputHandler;
import howser.space_invaders.gfx.Colour;
import howser.space_invaders.gfx.Font;
import howser.space_invaders.gfx.Frame;
import howser.space_invaders.state.messages.StateMessage;

import java.awt.event.KeyEvent;

public class MainMenuState extends BaseState {

	private Font menuFont;
	private int selectedItem;
	private final int ySpacing = 16;
	private InputHandler input;

	public MainMenuState(String name, StateManager stateManager,
			InputHandler input) {
		super(name, stateManager);
		this.input = input;
		menuFont = new Font("/main_font.png");
	}

	public void tick() {
		if (input.keyPressedThisFrame(KeyEvent.VK_DOWN)){
			selectedItem++;
		}
		if (input.keyPressedThisFrame(KeyEvent.VK_UP)){
			selectedItem--;
		}
		if (selectedItem < 0){
			selectedItem = 0;
		} else if (selectedItem > 2){
			selectedItem = 2;
		}
		if (input.keyPressedThisFrame(KeyEvent.VK_SPACE)){
			switch(selectedItem){
			case 0:
				this.stateManager.changeState("game_state");
				break;
			case 1:
				stateManager.changeState("highscore_state");
				break;
			case 2: 
				this.stateManager.changeState("exit_state");
				break;
			}
		}
	}

	public void render(Frame frame) {
		frame.clear(Colour.BLACK);
		int h = frame.getHeight();
		frame.renderString("Play", menuFont, 100, h / 4 + ySpacing * 0,
				Colour.WHITE);
		frame.renderString("High scores", menuFont, 100, h / 4 + ySpacing * 1,
				Colour.WHITE);
		frame.renderString("Exit", menuFont, 100, h / 4 + ySpacing * 2,
				Colour.WHITE);
		frame.renderString("x", menuFont, 90, h / 4 + ySpacing * selectedItem,
				Colour.RED);
		frame.renderString("By:HoWseR", menuFont, 20, frame.getHeight()-20, Colour.getColour(255, 125, 125));
	}

	public void onEnter() {
		selectedItem = 0;
		input.addKeyListen(KeyEvent.VK_UP);
		input.addKeyListen(KeyEvent.VK_DOWN);
		input.addKeyListen(KeyEvent.VK_SPACE);
	}

	public void onExit() {
		input.removeKeyListen(KeyEvent.VK_UP);
		input.removeKeyListen(KeyEvent.VK_DOWN);
		input.removeKeyListen(KeyEvent.VK_SPACE);
	}

	public void reset() {
		
	}

	public void receiveMessage(StateMessage message) {
		
	}
}
