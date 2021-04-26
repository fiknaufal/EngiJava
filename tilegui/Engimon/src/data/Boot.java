package data;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static helpers.Artist.*;
import backend.*;

public class Boot {
	private int gameState;
	private Map map;
	
	public Boot() {
		BeginSession();
		map = new Map(0, "Map", 10);
		gameState = 0;
		
		Texture t, tplayer, tmainmenu;
		Pointer p = new Pointer();
		while (!Display.isCloseRequested()) {
			if (gameState == 0) {
				p.update();
				if (p.getIndex() == 0) {
					tmainmenu = LoadTexture("res/mainpil1.png", "PNG");
					DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
				}else {
					tmainmenu = LoadTexture("res/mainpil2.png", "PNG");
					DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
				}
				int jawab = p.enter();
				if (jawab == 0) {
					gameState = 1;
				}
				if (jawab == 1) {
					//load
					gameState = 1;
				}

			}
			
			if (gameState == 1) {
				t = LoadTexture("res/blacks.png", "PNG");
				DrawQuadTex(t, 0, 0, 2000, 960);
				if(map.getPlayer().Move(map.getMapMatrix().size()-1, map.getMapMatrix().get(0).length()-1)) {
					map.randomiseEnemyMovement();
				}
				char[][] mapPolos = map.getMap();
				char[][] mapBerisi = map.getMapIsi();
				
				for (int i = 0; i < map.getMapMatrix().size(); i++) {
					for (int j = 0; j < map.getMapMatrix().get(0).length(); j++) {
						if (mapPolos[i][j] == '-') {
							t = LoadTexture("res/grass2.png", "PNG");
							DrawQuadTex(t, j*panjangTile(), i*panjangTile(), panjangTile(), panjangTile());
						}else if(mapPolos[i][j] == '+'){
							t = LoadTexture("res/sea2.png", "PNG");
							DrawQuadTex(t, j*panjangTile(), i*panjangTile(), panjangTile(), panjangTile());
						}else if(mapPolos[i][j] == 'o'){
							t = LoadTexture("res/ice2.png", "PNG");
							DrawQuadTex(t, j*panjangTile(), i*panjangTile(), panjangTile(), panjangTile());
						}else if(mapPolos[i][j] == '*'){
							t = LoadTexture("res/lava2.png", "PNG");
							DrawQuadTex(t, j*panjangTile(), i*panjangTile(), panjangTile(), panjangTile());
						}
					}
				}
				for (int i = map.getMapMatrix().size()-1; i >= 0; i--) {
					for (int j = 0; j < map.getMapMatrix().get(0).length(); j++) {
						if (mapBerisi[i][j] == 'P') {
							t = LoadTexture("res/player.png", "PNG");
							DrawQuadTex(t, j*panjangTile(), i*panjangTile(), panjangTile(), panjangTile());
						}if(mapBerisi[i][j] == 'X'){
							t = LoadTexture("res/eevee.png", "PNG");
							DrawQuadTex(t, j*panjangTile(), i*panjangTile(), panjangTile(), panjangTile());
						}for (int k = 0; k < map.getWildEngi().size(); k++) {
							if (map.getWildEngi().get(k).getEngimonPos().getX() == j && map.getWildEngi().get(k).getEngimonPos().getY() == i) {
								t = LoadTexture(map.getWildEngi().get(k).getIcon(), "PNG");
								DrawQuadTex(t, j*panjangTile(), i*panjangTile(), panjangTile(), panjangTile());
							}
						}
						
					}
				}
				int menu = p.esc();
				if(menu != -1) {
//					map.getPlayer().showEngimon(0);
					p.resetMenu();
					gameState = 3;
				}
				if (map.isBattle()) {
					gameState = 2;
				}
			}
			if (gameState == 2) {
				p.update();
				if (p.getIndex() == 0) {
					tmainmenu = LoadTexture("res/bfi.png", "PNG");
					DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
				}else {
					tmainmenu = LoadTexture("res/br.png", "PNG");
					DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
				}
				int jawab = p.enter();
				if (jawab == 0) {
					gameState = 1;
					//battle
				}
				if (jawab == 1) {
					gameState = 1;
					map.getWildEngi().remove(map.idSurroundEnemy());
					map.addEngi();
				}
			}
			if(gameState == 3) {
				p.menuUpdate();
				int pilihan = p.enter();
				int state = p.getMenu() % 4;
				if(pilihan != -1) {
					gameState = 1;
//					switch(state) {
//					case 0:
//						// open bag
//					case 1:
//						// show ative engimon
//					case 2:
//						// pet
//					case 3:
//						// exit
//						gameState = 1;
//						
//					}
				}
				else {
					if(state == 0) {
						tmainmenu = LoadTexture("res/mb.png", "PNG");
						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					}
					else if(state == 1) {
						tmainmenu = LoadTexture("res/ms.png", "PNG");
						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					}
					else if(state == 2) {
						tmainmenu = LoadTexture("res/mp.png", "PNG");
						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					}
					else if(state == 3) {
						tmainmenu = LoadTexture("res/me.png", "PNG");
						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					}
//					switch(state) {
//					case 0:
//						// render open bag
//						tmainmenu = LoadTexture("res/mb.png", "PNG");
//						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
//					case 1:
//						// render show active engimon
//						tmainmenu = LoadTexture("res/ms.png", "PNG");
//						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
//					case 2:
//						// render pet
//						tmainmenu = LoadTexture("res/mp.png", "PNG");
//						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
//					case 3:
//						// render exit
//						tmainmenu = LoadTexture("res/me.png", "PNG");
//						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
//					}
				}
//				gameState = 0;
			}
			Display.update();
			Display.sync(500);
		}
		Display.destroy();
	}
	
	public float panjangTile() {
		if ((map.getMapMatrix().size()/map.getMapMatrix().get(0).length()) > (960/1280)) {
			return 960/map.getMapMatrix().size();
		}
		else {
			return 960/map.getMapMatrix().get(0).length();
		}
	}
	
	public static void main (String[] args) {
		new Boot();
	}
}