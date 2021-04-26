package data;

import org.lwjgl.input.Keyboard;

public class Pointer {
	private int idx;
	private int maxidx;
	private int pilihanMenu = 0;
	private int penanda = 0;
	
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
	
}
