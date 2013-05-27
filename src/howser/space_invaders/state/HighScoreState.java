package howser.space_invaders.state;

import howser.space_invaders.InputHandler;
import howser.space_invaders.TextBoxInput;
import howser.space_invaders.gfx.Font;
import howser.space_invaders.gfx.Frame;
import howser.space_invaders.highscore.HighscoreManager;
import howser.space_invaders.highscore.Score;
import howser.space_invaders.state.messages.StateMessage;

import java.awt.event.KeyEvent;

public class HighScoreState extends BaseState {

	private InputHandler input;
	private TextBoxInput nameBox;
	private int lastScore;
	private boolean addScore;
	private Font font;
	private HighscoreManager highscoreManager;

	public HighScoreState(String name, StateManager stateManager,
			InputHandler input) {
		super(name, stateManager);
		this.input = input;
		font = new Font("/main_font.png");
		nameBox = new TextBoxInput(40, 150, "Player", font);
		stateManager.addKeyListener(nameBox);
		highscoreManager = new HighscoreManager("highscores.dat", font);
	}

	public void tick() {
		if (input.isKeyPressed(KeyEvent.VK_ENTER) && addScore) {
			Score s = new Score(nameBox.value, lastScore);
			addScore = false;
			nameBox.setVisible(false);
			highscoreManager.addHighscore(s);
		}
		if (input.keyPressedThisFrame(KeyEvent.VK_ESCAPE)){
			stateManager.changeState("main_menu_state");
		}
	}

	public void render(Frame frame) {
		nameBox.render(frame);
		highscoreManager.render(frame);
	}

	public void onEnter() {
		if (addScore) {
			nameBox.setVisible(true);
		}
		input.addKeyListen(KeyEvent.VK_ENTER);
		input.addKeyListen(KeyEvent.VK_ESCAPE);
		highscoreManager.loadHighscoreFile();
		nameBox.value = "Player";
	}

	public void onExit() {
		nameBox.setVisible(false);
		input.removeKeyListen(KeyEvent.VK_ENTER);
		input.removeKeyListen(KeyEvent.VK_ESCAPE);
		highscoreManager.saveHighscoreFile();
	}

	public void reset() {

	}

	public void receiveMessage(StateMessage message) {
		if (message.sender == "game_state") {
			if ((int) message.message.get(0) > highscoreManager
					.getScoreThreshold()) {
				lastScore = (int) message.message.get(0);
				addScore = true;
				nameBox.setVisible(true);
			} else {
				addScore = false;
			}
		}
		if (message.sender == "main_menu") {
			addScore = false;
		}
	}
}
