package howser.space_invaders;

import howser.space_invaders.gfx.Colour;
import howser.space_invaders.gfx.Font;
import howser.space_invaders.gfx.Frame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextBoxInput implements KeyListener {

	private int x, y;
	public String value;
	public final int maxChars = 15;
	private Font font;
	private boolean visible;
	private int tick;

	public TextBoxInput(int x, int y, String defaultValue, Font font) {
		this.x = x;
		this.y = y;
		this.value = defaultValue;
		this.font = font;
	}

	public void setVisible(boolean b) {
		visible = b;
	}
	
	public boolean getVisible(){
		return visible;
	}
	
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE && value.length() != 0) {
			value = value.substring(0, value.length() - 1);
		}
	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {
		if (font.charExists(arg0.getKeyChar()) && visible && value.length() != maxChars) {
			value = value + arg0.getKeyChar();
		}
	}

	public void render(Frame frame) {
		if (visible) {
			frame.renderBorderedRectangle(x, y, 15 * 8 + 4, 14, Colour.RED,
					Colour.BLACK);
			frame.renderString(value, font, x + 2, y + 2, Colour.WHITE);
			if (tick % 30 < 15){
				frame.renderRectangle(x+value.length()*font.getCharWidth() + 2, y+2, 1, font.getCharHeight(), Colour.WHITE);
				
			}
			tick++;
		}
	}
}
