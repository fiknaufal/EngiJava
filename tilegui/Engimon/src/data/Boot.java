package data;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static helpers.Artist.*;

public class Boot {
	
	private Player player;
	
	public Boot() {
		BeginSession();
		player = new Player(0, 0);
		
		Texture t, tplayer;
		while (!Display.isCloseRequested()) {
			player.move();
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 20; j++) {
					if (i < 8 && j < 10) {
						t = LoadTexture("res/grass2.png", "PNG");
						DrawQuadTex(t, j*64+2, i*64+2, 60, 60);
					} else if ((i < 8 && j >= 10)) {
						t = LoadTexture("res/ice2.png", "PNG");
						DrawQuadTex(t, j*64+2, i*64+2, 60, 60);
					} else if ((i >= 8 && j >= 10)) {
						t = LoadTexture("res/sea2.png", "PNG");
						DrawQuadTex(t, j*64+2, i*64+2, 60, 60);
					} else {
						t = LoadTexture("res/lava2.png", "PNG");
						DrawQuadTex(t, j*64+2, i*64+2, 60, 60);
					}
					if (j == player.getX() && i == player.getY()) {
						tplayer = LoadTexture("res/player.png", "PNG");
						DrawQuadTex(tplayer, j*64+3, i*64+1, 64, 64);
					}
				}
			}
			System.out.println("posisi = "+player.getX()+", "+player.getY());
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	public static void main (String[] args) {
		new Boot();
	}
}