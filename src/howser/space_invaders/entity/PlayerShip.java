package howser.space_invaders.entity;

import java.awt.event.KeyEvent;

import howser.space_invaders.InputHandler;
import howser.space_invaders.gfx.Colour;
import howser.space_invaders.gfx.Frame;
import howser.space_invaders.gfx.SpriteSheet;

public class PlayerShip extends Ship{

	private InputHandler input;
	private int speed;
	
	public PlayerShip(SpriteSheet sheet, int sheetX, int sheetY, int width,
			int height, int x, int y, int speed, InputHandler input) {
		super(sheet, sheetX, sheetY, width, height, x, y);
		this.input = input;
		this.speed = speed;
		input.addKeyListen(KeyEvent.VK_LEFT);
		input.addKeyListen(KeyEvent.VK_RIGHT);
		input.addKeyListen(KeyEvent.VK_SPACE);
		sprite.setTint(Colour.RED);
	}

	public void tick() {
		if (input.isKeyPressed(KeyEvent.VK_LEFT)){
			Move(-speed, 0);
		}
		if (input.isKeyPressed(KeyEvent.VK_RIGHT)) {
			Move(speed, 0);
		}
		if (input.isKeyPressed(KeyEvent.VK_SPACE)){
			shoot();
		}
	}

	public void render(Frame frame) {
		frame.renderToFrame(sprite.getPixels(), x, y, width, height);
	}
	
	public void shoot(){
		System.out.println("SHOOTING");
	}
}
