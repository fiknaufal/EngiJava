package data;

import org.lwjgl.input.Keyboard;

public class Pointer {
	private int idx;
	private int maxidx;
	private int pilihanMenu = 0;
	private int penanda = 0;
	private int penandaEnter = 0;
	
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
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN) && penandaEnter == 0) {
			penandaEnter = 1;
			return idx;
		}else {
			penandaEnter = 0;
			return -1;
		}
	}
	
	public int esc() {
		if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
			return idx;
		}else {
			return -1;
		}
	}
	
	public void menuUpdate() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W) && penanda == 0) {
			pilihanMenu -= 1;
			penanda = 1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)&& penanda == 0) {
			pilihanMenu += 1;
			penanda = 1;
		}
		if(!Keyboard.isKeyDown(Keyboard.KEY_W) && !Keyboard.isKeyDown(Keyboard.KEY_S)) {
			penanda = 0;
		}
	}
	
	public int getMenu() {
		return pilihanMenu;
	}
	
	public void resetMenu() {
		pilihanMenu = 0;
	}
	
	public boolean anyKeyDown() {
		return Keyboard.getEventKeyState();
	}
	
	public char keyChar() {
		return Keyboard.getEventCharacter();
	}
}
