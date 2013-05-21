package howser.space_invaders.state;

import howser.space_invaders.InputHandler;
import howser.space_invaders.entity.PlayerShip;
import howser.space_invaders.entity.Ship;
import howser.space_invaders.gfx.Colour;
import howser.space_invaders.gfx.Font;
import howser.space_invaders.gfx.Frame;
import howser.space_invaders.gfx.SpriteSheet;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class GameState extends BaseState{

	private ArrayList<Ship> enemies;
	private PlayerShip player;
	private SpriteSheet sprites;
	private InputHandler input;
	private boolean paused = false;
	private Font font;
	private int score;
	private Random rand;
	
	
	public GameState(String name, StateManager stateManager, InputHandler input) {
		super(name, stateManager);
		sprites = new SpriteSheet("/sprite_sheet.png");
		font = new Font("/main_font.png");
		this.input = input;
		rand = new Random();
	}

	public void tick() {
		if (!paused){
			player.tick();
		}
		if (input.keyPressedThisFrame(KeyEvent.VK_ESCAPE)){
			paused = !paused;
		}
	}

	public void render(Frame frame) {
		frame.clear(Colour.BLACK);
		player.render(frame);
		if (paused){
			frame.renderString("Game Paused", font, frame.getWidth()/2 - 6*8, 100, Colour.WHITE);
		}
	}

	public void onEnter() {
		enemies = new ArrayList<Ship>();
		player = new PlayerShip(sprites, 0,0, 16, 16, 0, 160, 2, input);
		input.addKeyListen(KeyEvent.VK_ESCAPE);
	}

	public void onExit() {
		input.clearKeyListens();
		enemies.clear();
	}

	public void reset() {
		
	}
	
	public void generateScenery(){
		
	}
}
