package data;

import org.lwjgl.input.Keyboard;

public class Pointer {
	private int idx;
	private int maxidx;
	
	public Pointer() {
		this.idx = 0;
		this.maxidx = 2;
	}
	
	public void update() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.idx = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.idx = 1;
		}
	}
	
	public int getIndex() {
		return idx;
	}
	
	public int enter() {
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			return idx;
		}else {
			return -1;
		}
	}
}
