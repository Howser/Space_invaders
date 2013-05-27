package howser.space_invaders;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

class Key {
	public int keyCode;
	public boolean isPressed;
	public boolean wasPressed;
	
	public Key(int keyCode){
		this.keyCode = keyCode;
		this.isPressed = false;
		this.wasPressed = false;
	}
}

public class InputHandler implements KeyListener {

	private ArrayList<Key> listenKeys;
	
	public InputHandler() {
		listenKeys = new ArrayList<Key>();
	}
	
	public void addKeyListen(int keyCode){
		listenKeys.add(new Key(keyCode));
	}
	
	public void removeKeyListen(int keyCode){
		for (Key k : listenKeys){
			if (k.keyCode == keyCode){
				listenKeys.remove(k);
				return;
			}
		}
	}
	
	public void clearKeyListens(){
		listenKeys.clear();
	}

	public void keyPressed(KeyEvent e) {
		for (int i = 0; i < listenKeys.size(); i++){
			if (e.getKeyCode() == listenKeys.get(i).keyCode){
				listenKeys.get(i).isPressed = true;
				break;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		for (int i = 0; i < listenKeys.size(); i++){
			if (e.getKeyCode() == listenKeys.get(i).keyCode){
				listenKeys.get(i).isPressed = false;
				listenKeys.get(i).wasPressed = false;
				break;
			}
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public boolean isKeyPressed(int keyCode) {
		for (int i = 0; i < listenKeys.size(); i++){
			if (listenKeys.get(i).keyCode == keyCode){
				if (listenKeys.get(i).isPressed){
					return true;
				}
			}
		}
		return false;
	}

	public boolean isKeyReleased(int keyCode) {
		for (int i = 0; i < listenKeys.size(); i++){
			if (listenKeys.get(i).keyCode == keyCode){
				if (!listenKeys.get(i).isPressed){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean keyPressedThisFrame(int keyCode){
		for (int i = 0; i < listenKeys.size(); i++){
			if (listenKeys.get(i).keyCode == keyCode){
				if (listenKeys.get(i).isPressed && !listenKeys.get(i).wasPressed){
					listenKeys.get(i).wasPressed = true;
					return true;
				}
			}
		}
		return false;
	}
}
