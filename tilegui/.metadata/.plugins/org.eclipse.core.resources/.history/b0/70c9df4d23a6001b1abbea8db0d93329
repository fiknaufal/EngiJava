package data;

import org.lwjgl.input.Keyboard;

public class Player {
	private float x;
	private float y;
	
	public Player(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void move() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W) && this.y != 0) {
			this.y--;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S) && this.y != 14) {
			this.y++;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A) && this.x != 0) {
			this.x--;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D) && this.x != 19) {
			this.x++;
		}

	}
	
}
