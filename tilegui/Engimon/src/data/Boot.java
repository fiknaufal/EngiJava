package data;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;
import java.io.IOException;

import static helpers.Artist.*;
import backend.*;

public class Boot {
	private int gameState;
	private Map map;
	private SaveLoad save =  new SaveLoad();
	private boolean lose;
	private int skillIndex = 0; //buat passing skill mana yang mau di learn
	
	public Boot() {
		BeginSession();
		map = new Map(0, "Map", 10);
		gameState = 0;
		lose = false;
		
		Texture t, tplayer, tmainmenu;
		Pointer p = new Pointer();
		while (!Display.isCloseRequested() && lose == false) {
			if (gameState == 0) {//menu utama
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
					try {
						SaveLoad.Load("savefile.txt");
					} catch (FileNotFoundException e) {
						System.out.println("File Save tidak ditemukan. Mohon periksa kembali nama file anda!");
						e.printStackTrace();
					}
					gameState = 1;
				}

			}
			
			if (gameState == 1) {//map
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
			if (gameState == 2) {//battle
				p.update();
				if (p.getIndex() == 0) {
					tmainmenu = LoadTexture("res/bfi.png", "PNG");
					DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					map.getWildEngi().get(map.idSurroundEnemy()).printData();
					t = LoadTexture(map.getWildEngi().get(map.idSurroundEnemy()).getIcon(), "PNG");
					DrawQuadTex(t, 500, 250, 64, 64);
				}else {
					tmainmenu = LoadTexture("res/br.png", "PNG");
					DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					map.getWildEngi().get(map.idSurroundEnemy()).printData();
					t = LoadTexture(map.getWildEngi().get(map.idSurroundEnemy()).getIcon(), "PNG");
					DrawQuadTex(t, 500, 250, 64, 64);
				}
				int jawab = p.enter();
				if (jawab == 0) {
					if (map.getPlayer().battle(map.getWildEngi().get(map.idSurroundEnemy()))) {
						if(map.getPlayer().addEngimon(map.getWildEngi().get(map.idSurroundEnemy()))) {
							SkillItem si = new SkillItem(map.getWildEngi().get(map.idSurroundEnemy()).getSkill().get(0), 1);
							map.getPlayer().addSkillItem(si);
						}
						map.getWildEngi().remove(map.idSurroundEnemy());
						map.addEngi();
					}else {
						if (!map.getPlayer().lose()) {
							map.getWildEngi().remove(map.idSurroundEnemy());
							map.addEngi();
						}
						else {
							lose = true;
						}
					}
					gameState = 1;
					gameState = 1;
					//battle
				}
				if (jawab == 1) {
					gameState = 1;
					map.getWildEngi().remove(map.idSurroundEnemy());
					map.addEngi();
				}
			}
			if(gameState == 3) {//menu dalem game
				p.menuUpdate();
				int pilihan = p.enter();
				int state = p.getMenu() % 6;
				if(pilihan != -1) {
					gameState = 1;
					if(state == 0) {
						// open bag
						p.resetMenu();
						gameState = 4;
					}
					else if(state == 1) {
						// show active engimon
						gameState = 5;
					}
					else if(state == 2) {
						// pet
						gameState = 6;
					}
					else if(state == 3) {
						// exit
						gameState = 0;
					}
					else if(state == 4) {
						// save
						try {
							save.Save(map, "savefile.txt");
						}
						catch(IOException e) {
							// kasih pesan error
							gameState = 7;
						}
						gameState = 0;
					}
					else if(state == 5) {
						// return
						gameState = 1;
					}
				}
				else {
					if(state == 0) {
						tmainmenu = LoadTexture("res/mb2.png", "PNG");
						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					}
					else if(state == 1) {
						tmainmenu = LoadTexture("res/ms2.png", "PNG");
						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					}
					else if(state == 2) {
						tmainmenu = LoadTexture("res/mp2.png", "PNG");
						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					}
					else if(state == 3) {
						tmainmenu = LoadTexture("res/me2.png", "PNG");
						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					}
					else if(state == 4) {
						tmainmenu = LoadTexture("res/msa2.png", "PNG");
						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					}
					else if(state == 5) {
						tmainmenu = LoadTexture("res/mr2.png", "PNG");
						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					}
				}
			}
			if(gameState == 4) {//bag
				p.menuUpdate();
				int pilihan = p.enter();
				int state = p.getMenu() % 5;
				if(pilihan != -1) {
					if(state == 0) {
						// show engimon
						p.resetMenu();
						gameState = 8;
					}
					else if(state == 1) {
						// show skill items
						gameState = 9;
					}
					else if(state == 2) {
						// choose active
						gameState = 10;
					}
					else if(state == 3) {
						// breed
						gameState = 11;
					}
					else if(state == 4) {
						// close
						p.resetMenu();
						gameState = 3;
					}
				}
				else {
					if(state == 0) {
						tmainmenu = LoadTexture("res/bse.png", "PNG");
						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					}
					else if(state == 1) {
						tmainmenu = LoadTexture("res/bss.png", "PNG");
						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					}
					else if(state == 2) {
						tmainmenu = LoadTexture("res/bca.png", "PNG");
						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					}
					else if(state == 3) {
						tmainmenu = LoadTexture("res/bb.png", "PNG");
						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					}
					else if(state == 4) {
						tmainmenu = LoadTexture("res/bc.png", "PNG");
						DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
					}
				}
			}
			if(gameState == 5) {//active Engimon
				tmainmenu = LoadTexture("res/blacks.png", "PNG");
				DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
				map.getPlayer().showActiveEngimon();
				if (p.esc() != -1)
					gameState = 3;
				
			}
			if(gameState == 6) {// pet sound
				tmainmenu = LoadTexture("res/blacks.png", "PNG");
				DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
				map.getPlayer().petEngi();
				if(p.esc() != -1) {
					gameState = 3;
				}
				
			}
			if(gameState == 7) {//save error
				tmainmenu = LoadTexture("res/blacks.png", "PNG");
				DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
				TrueTypeFont font;
		    	Font awtFont = new Font("Times New Roman", Font.BOLD, 50); //name, style (PLAIN, BOLD, or ITALIC), size
		    	font = new TrueTypeFont(awtFont, false);
		    	
		    	
		    	font.drawString((float) 600, (float) 400, "Save Error", Color.white);
		    	if(p.esc() != -1) {
		    		gameState = 1;
		    	}
				
			}
			if(gameState == 8) {//show engimon
				p.menuUpdate();
				int pilihan = p.enter();
				int back = p.esc();
				int state = p.getMenu() % map.getPlayer().getEngimonCount();
				tmainmenu = LoadTexture("res/blacks.png", "PNG");
				DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
				if(pilihan == -1) {
					map.getPlayer().showEngimonList(500, state);
					if(back != -1) {
						gameState = 4;
					}
				}
				else {
					// show engimon data
					gameState = 12;
				}
			}
			if(gameState == 9) {//show skill items
				p.menuUpdate();
				int pilihan = p.enter();
				int back = p.esc();
				int state = p.getMenu() % map.getPlayer().getUniqueSkillItem();
				tmainmenu = LoadTexture("res/blacks.png", "PNG");
				DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
				if(pilihan == -1) {
					map.getPlayer().showSkillItemList(state);
					if(back != -1) {
						gameState = 4;
					}
				}
				else {
					// show skill data
					skillIndex = state;
					gameState = 13;
				}
				
			}
			if(gameState == 10) {//choose active
				TrueTypeFont font;
		    	Font awtFont = new Font("Times New Roman", Font.BOLD, 50); //name, style (PLAIN, BOLD, or ITALIC), size
		    	font = new TrueTypeFont(awtFont, false);
		    	
				p.menuUpdate();
				int pilihan = p.enter();
				int state = p.getMenu() % map.getPlayer().getEngimonCount();
				tmainmenu = LoadTexture("res/blacks.png", "PNG");
				DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
				font.drawString((float) 500, (float) 250, "Choose Active", Color.white);
				if(p.esc() != -1) {
		    		gameState = 4;
		    	}
				if(pilihan == -1) {
					map.getPlayer().showEngimonList(500, state);
				}
				else {
					map.getPlayer().setActiveEngi(state);
					gameState = 4;
				}
				
			}
			if(gameState == 11) {//breed
				p.menuUpdate();
				int pilihan = p.enter();
				int state = p.getMenu() % map.getPlayer().getEngimonCount();
				tmainmenu = LoadTexture("res/blacks.png", "PNG");
				DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
				
				if (p.esc() != -1) {
					map.getPlayer().firstBreedEngi = -1;
					map.getPlayer().secondBreedEngi = -1;
					gameState = 4;
				}
				else if(map.getPlayer().firstBreedEngi == -1) {
					map.getPlayer().showEngimonList(50, state);
					map.getPlayer().showEngimonList(600, 0);
					if (pilihan != -1) {
						map.getPlayer().firstBreedEngi = state;
					}
				}
				else if(map.getPlayer().secondBreedEngi == -1) {
					map.getPlayer().showEngimonList(50, map.getPlayer().firstBreedEngi);
					map.getPlayer().showEngimonList(600, state);
					if ((pilihan != -1) && (state != map.getPlayer().firstBreedEngi)) {
						map.getPlayer().secondBreedEngi = state;
					}
				}
				else {
					map.getPlayer().breedEngimon(map.getPlayer().firstBreedEngi, map.getPlayer().secondBreedEngi);
					map.getPlayer().firstBreedEngi = -1;
					map.getPlayer().secondBreedEngi = -1;
					gameState = 3;
				}
			}
			if(gameState == 12) {//show engimon status
				tmainmenu = LoadTexture("res/blacks.png", "PNG");
				DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
				map.getPlayer().getInventoryE().getElement(p.getMenu() % map.getPlayer().getEngimonCount()).printData();
				if(p.esc() != -1) {
					gameState = 4;
				}
				
			}
			if(gameState == 13) {//learn skill
				p.menuUpdate();
				int pilihan = p.enter();
				int back = p.esc();
				int state = p.getMenu() % map.getPlayer().getEngimonCount();
				tmainmenu = LoadTexture("res/blacks.png", "PNG");
				DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
				if(pilihan == -1) {
					map.getPlayer().showEngimonList(state);
					if(back != -1) {
						gameState = 9;
					}
				}
				else {
					// skill di learn sama engimonnya
					if(map.getPlayer().useSkillItem(skillIndex, state)) {
						gameState = 14;
					}
					else {
						gameState = 4;
					}
				}
			}
			if(gameState == 14) {
				tmainmenu = LoadTexture("res/blacks.png", "PNG");
				DrawQuadTex(tmainmenu, 0, 0, 2000, 960);
				TrueTypeFont font;
		    	Font awtFont = new Font("Times New Roman", Font.BOLD, 50); //name, style (PLAIN, BOLD, or ITALIC), size
		    	font = new TrueTypeFont(awtFont, false);
		    	
		    	
		    	font.drawString((float) 400, (float) 400, "Gagal mempelajari skill.", Color.white);
		    	font.drawString((float) 400, (float) 455, "Engimon memiliki elemen berbeda!", Color.white);
		    	if(p.esc() != -1) {
					gameState = 4;
				}
			}
			if (gameState ==15) {
				Character c = null;
				
				if (p.anyKeyDown())
					c = p.keyChar();
				
				map.getPlayer().showAnakName(c);
				
				if (p.enter() != -1 && (!map.getPlayer().childName.isEmpty())) {
					map.getPlayer().breedEngimon(map.getPlayer().firstBreedEngi, map.getPlayer().secondBreedEngi);
					map.getPlayer().firstBreedEngi = -1;
					map.getPlayer().secondBreedEngi = -1;
					map.getPlayer().childName = "";
					gameState = 3;
				}
			}
			Display.update();
			Display.sync(10);
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