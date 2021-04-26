package backend;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Map {
    public enum gameState{ Jalan, Battle, Bag, Exit}

    private ArrayList<String> mapMatrix = new ArrayList<>();
    private Player player = new Player();
    private ArrayList<Engimon> wildEngi = new ArrayList<>();;
    private int level;
    private int round = 0;
    private SkillGacha sg = new SkillGacha();
    private gameState state;
    
    public Player getPlayer() {
    	return player;
    }

    public void gameFlow() {

        String cmd;
        Scanner sc = new Scanner(System.in);
        while (state != gameState.Exit && !player.lose()) {
            if (isBattle()) {
                this.state = gameState.Battle;
            }
            if (state == gameState.Jalan) {
                round++;
                show();
                printMenu();
                cmd = sc.nextLine();
                if(cmd.compareToIgnoreCase("exit") == 0){
                    this.state = gameState.Exit;
                }
            }
        }
        sc.close();
    }

    public void showGUI(Graphics g){

    }

    public Map(int n, String namafile, int maxengi){
        try {
            File myObj = new File(namafile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                mapMatrix.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Firemon f = new Firemon(3900, 0, 0, 3);
        Firemon f1 = new Firemon(3200, 0, 0, 3);
        Electricmon e = new Electricmon(3200, 0, 0, 3);
        Electricmon e1 = new Electricmon(3200, 0, 0, 3);
        WaterGroundmon w = new WaterGroundmon(3200, 0, 0, 3);
        player.addEngimon(f);
        player.addEngimon(e);
        player.addEngimon(f1);
        player.addEngimon(e1);
        player.addEngimon(w);
        level = n;
        
        while (wildEngi.size() < maxengi){
            addEngi();
        }
        state = gameState.Jalan;
    }

    public void addEngi(){
        int r = ThreadLocalRandom.current().nextInt(player.maxLevelEngi(), 41)*100;
        int x = ThreadLocalRandom.current().nextInt(0, mapMatrix.get(0).length()), y = ThreadLocalRandom.current().nextInt(0, mapMatrix.size());
        Engimon w;
        Position p = new Position(x, y);
        System.out.println(player.maxLevelEngi());
        System.out.println(r);
        while(!nobodyThere(p)){
            x =ThreadLocalRandom.current().nextInt(0, mapMatrix.get(0).length());
            y = ThreadLocalRandom.current().nextInt(0, mapMatrix.size());
            p.setX(x);
            p.setY(y);
        }

        if (mapMatrix.get(y).charAt(x) == '+'){
            int e1 =ThreadLocalRandom.current().nextInt(0, 4);
            switch(e1){
                case 0:
                    w = new Watermon(r, x, y);
                    break;
                case 1:
                    w = new Icemon(r, x, y);
                    break;
                case 2:
                    w = new WaterIcemon(r, x, y);
                    break;
//                case 3:
                default:
                    w = new WaterGroundmon(r, x, y);
                    break;
            }
            wildEngi.add(w);
        } else if(mapMatrix.get(y).charAt(x) == '-'){
            int e2 = ThreadLocalRandom.current().nextInt(0, 4);
            switch(e2){
                case 0:
                    w = new Firemon(r, x, y);
                    break;
                case 1:
                    w = new Groundmon(r, x, y);
                    break;
                case 2:
                    w = new Electricmon(r, x, y);
                    break;
//                case 3:
                default:
                    w = new FireElectricmon(r, x, y);
                    break;
            }
            wildEngi.add(w);
        }else if(mapMatrix.get(y).charAt(x) == '*'){
            int e2 = ThreadLocalRandom.current().nextInt(0, 2);
            switch(e2){
                case 0:
                    w = new Firemon(r, x, y);
                    break;
                case 1:
                default:
                    w = new FireElectricmon(r, x, y);
                    break;
            }
            wildEngi.add(w);
        }else if(mapMatrix.get(y).charAt(x) == 'o'){
            int e2 = ThreadLocalRandom.current().nextInt(0, 2);
            switch(e2){
                case 0:
                    w = new Icemon(r, x, y);
                    break;
                case 1:
                default:
                    w = new WaterIcemon(r, x, y);
                    break;
            }
            wildEngi.add(w);
        }
    }

    public void show(){
        char[][] maps = new char[mapMatrix.size()][mapMatrix.get(0).length()];
        for (int i = mapMatrix.size()-1; i >= 0; i--){
            for (int j = 0; j < mapMatrix.get(0).length(); j++){
                maps[i][j] = mapMatrix.get(i).charAt(j);
            }
        }
        maps[player.getPlayerPos().getY()][player.getPlayerPos().getX()] = 'P';
        maps[player.getActivePos().getY()][player.getActivePos().getX()] = 'X';
        for(int i = 0; i < wildEngi.size(); i++){
            maps[wildEngi.get(i).getEngimonPos().getY()][wildEngi.get(i).getEngimonPos().getX()] = wildEngi.get(i).getMapSymbol(level);
        }
        for (int i = mapMatrix.size()-1; i >= 0; i--){
            for (int j = 0; j < mapMatrix.get(0).length(); j++){
                System.out.print(maps[i][j]);
            }
            System.out.println();
        }
    }
    
    public ArrayList<String> getMapMatrix(){
    	return mapMatrix;
    }
    
    public char[][] getMap(){
        char[][] maps = new char[mapMatrix.size()][mapMatrix.get(0).length()];
        for (int i = mapMatrix.size()-1; i >= 0; i--){
            for (int j = 0; j < mapMatrix.get(0).length(); j++){
                maps[i][j] = mapMatrix.get(i).charAt(j);
            }
        }
        return maps;
    }
    
    public char[][] getMapIsi(){
        char[][] maps = new char[mapMatrix.size()][mapMatrix.get(0).length()];
        for (int i = mapMatrix.size()-1; i >= 0; i--){
            for (int j = 0; j < mapMatrix.get(0).length(); j++){
                maps[i][j] = mapMatrix.get(i).charAt(j);
            }
        }
        maps[player.getPlayerPos().getY()][player.getPlayerPos().getX()] = 'P';
        maps[player.getActivePos().getY()][player.getActivePos().getX()] = 'X';
        for(int i = 0; i < wildEngi.size(); i++){
            maps[wildEngi.get(i).getEngimonPos().getY()][wildEngi.get(i).getEngimonPos().getX()] = wildEngi.get(i).getMapSymbol(level);
        }
        return maps;
    }

    int counting = 0;
    public void randomiseEnemyMovement(){
        int i, r, m;
        Position p;

        i=0;
        while(i < wildEngi.size()){
            r = ThreadLocalRandom.current().nextInt(1, 5);
            m = ThreadLocalRandom.current().nextInt(0, 12);
            p = wildEngi.get(i).randomMove(r);
            if(m==0 && isInMap(p) && canEngimonWalk(wildEngi.get(i), p) && nobodyThere(p)){
                wildEngi.get(i).setEngimonPos(p.getX(), p.getY());
                if (round % 10 == 0) {
                    boolean deadAfterGrow = wildEngi.get(i).plusExp(10);
                    if (deadAfterGrow) {
                        wildEngi.remove(i);
                        addEngi();
                    }
                    else {
                        i++;
                    }
                }
                else {
                    i++;
                }
            }
            i++;
        }
    }

    public int idSurroundEnemy(){
        for (int i = 0; i < wildEngi.size(); i++){
            if (player.getPlayerPos().getY()+1 == wildEngi.get(i).getEngimonPos().getY() && player.getPlayerPos().getX() == wildEngi.get(i).getEngimonPos().getX()){
                return i;
            }
            if (player.getPlayerPos().getY() == wildEngi.get(i).getEngimonPos().getY() && player.getPlayerPos().getX()+1 == wildEngi.get(i).getEngimonPos().getX()){
                return i;
            }
            if (player.getPlayerPos().getY()-1 == wildEngi.get(i).getEngimonPos().getY() && player.getPlayerPos().getX() == wildEngi.get(i).getEngimonPos().getX()){
                return i;
            }
            if (player.getPlayerPos().getY() == wildEngi.get(i).getEngimonPos().getY() && player.getPlayerPos().getX()-1 == wildEngi.get(i).getEngimonPos().getX()){
                return i;
            }
        }
        return -1;
    }

    public boolean isBattle(){
        for (int i = 0; i < wildEngi.size(); i++){
            if (player.getPlayerPos().getY()+1 == wildEngi.get(i).getEngimonPos().getY() && player.getPlayerPos().getX() == wildEngi.get(i).getEngimonPos().getX()){
                return true;
            }
            if (player.getPlayerPos().getY() == wildEngi.get(i).getEngimonPos().getY() && player.getPlayerPos().getX()+1 == wildEngi.get(i).getEngimonPos().getX()){
                return true;
            }
            if (player.getPlayerPos().getY()-1 == wildEngi.get(i).getEngimonPos().getY() && player.getPlayerPos().getX() == wildEngi.get(i).getEngimonPos().getX()){
                return true;
            }
            if (player.getPlayerPos().getY() == wildEngi.get(i).getEngimonPos().getY() && player.getPlayerPos().getX()-1 == wildEngi.get(i).getEngimonPos().getX()){
                return true;
            }
        }
        return false;
    }

    public boolean canEngimonWalk(Engimon e, Position p){
        Element e1 = e.getElement1(), e2 = e.getElement2();
        char alas = mapMatrix.get(p.getY()).charAt(p.getX());
        if(alas == '-'){
            if(e1==Element.FIRE || e1 == Element.ELECTRIC || e1 == Element.GROUND || e2==Element.FIRE || e2 == Element.ELECTRIC || e2 == Element.GROUND){
                return true;
            }
        }
        else if (alas == '+'){
            if(e1==Element.WATER || e1 ==Element.ICE || e2==Element.WATER || e2 ==Element.ICE){
                return true;
            }
        }
        else if (alas == '*'){
            if(e1==Element.FIRE || e2==Element.FIRE){
                return true;
            }
        }
        else if (alas == 'o'){
            if(e1 ==Element.ICE || e2 ==Element.ICE){
                return true;
            }
        }
        return false;
    }

    public boolean nobodyThere(Position p){
        if(p.isEqual(player.getPlayerPos())){
            return false;
        }
        for(int i = 0; i < wildEngi.size(); i++){
            if(p.isEqual(wildEngi.get(i).getEngimonPos())){
                return false;
            }
        }
        return true;
    }

    public boolean isInMap(Position p){
        return p.getX() >= 0 && p.getX() < mapMatrix.get(0).length() && p.getY() >= 0 && p.getY() < mapMatrix.size();
    }

    public void printMenu(){
        System.out.println("\n");
        System.out.println("--------------------");
        System.out.println("Available Commands:");
        System.out.println("1. w,a,s,d: Move\n2. bag: Open Bag\n3. show: Show Active Engimon\n4. pet: Pet Active Engimon\n5. exit: Exit the game");
        System.out.println("--------------------");
        System.out.println("command: ");
    }

    public void printBag(){
        System.out.println("--------------------");
        System.out.println("1. engimons: show engimons\n2. skillItems: show skill items\n3. chooseActive: pilih active engimon\n4. breed: Breed Engimon\n5. learn: Learn Skill\n6. close: close bag");
        System.out.println("command: ");
    }
    
    //Getter buat SaveLoad

    public ArrayList<Engimon> getWildEngi() {
        return wildEngi;
    }

    public int getLevel() {
        return level;
    }

    public int getRound() {
        return round;
    }

    public gameState getGameState() {
        return state;
    }

}
//
//        void Map::gameFlow(){
//        string cmd;
//        while (state != Exit && !player.lose()){
//        if (isBattle()){
//        this->state = Battle;
//        }
//        if (state == Jalan){
//        show();
//        cout << "\n";
//        cout << "--------------------" << endl;
//        cout << "Available Commands:" << endl;
//        cout << "1. w,a,s,d: Move\n2. bag: Open Bag\n3. show: Show Active Engimon\n4. pet: Pet Active Engimon\n5. exit: Exit the game" << endl;
//        cout << "--------------------" << endl;
//        cout << "command: ";
//        cin >> cmd;
//        cout << "\n";
//        if (cmd == "w"||cmd == "a"||cmd == "s"||cmd == "d"){
//        if (cmd == "w"){
//        if (player.getPlayerPos().getY() < mapMatrix.size()-1){
//        player.Move(cmd);
//        }
//        }else if (cmd == "a"){
//        if (player.getPlayerPos().getX() > 0){
//        player.Move(cmd);
//        }
//        }else if (cmd == "s"){
//        if (player.getPlayerPos().getY() > 0){
//        player.Move(cmd);
//        }
//        }else{
//        if (player.getPlayerPos().getX() < mapMatrix[0].length()-1){
//        player.Move(cmd);
//        }
//        }
//        randomiseEnemyMovement();
//        }else if (cmd == "bag"){
//        state = Bag;
//        }else if (cmd == "show"){
//        player.showActiveEngimon();
//        }else if (cmd == "pet"){
//        player.petEngi();
//        }else if (cmd == "exit"){
//        state = Exit;
//        }else{
//        cout << "Command tidak dikenali" << endl;
//        }
//        }else if (state == Bag){
//        while (state == Bag){
//        cout << "--- BAG ---" << endl;
//        cout << "Available Commands:" << endl;
//        cout << "1. engimons: show engimons\n2. skillItems: show skill items\n3. chooseActive: pilih active engimon\n4. breed: Breed Engimon\n5. learn: Learn Skill\n6. close: close bag" << endl;
//        cout << "-----------" << endl;
//        cout << "command: ";
//        string cmdbag;
//        cin >> cmdbag;
//        cout << "\n";
//        if (cmdbag == "engimons"){
//        bool keluar = false;
//        while (!keluar){
//        player.showEngimonList();
//        cout << "Ketik -1 untuk kembali ke bag\nCek Engimon dengan nomor: ";
//        int idx;
//        cin >> idx;
//        if (idx == -1){
//        keluar = true;
//        } else{
//        cout << endl;
//        player.showEngimon(idx-1);
//        }
//        cout << endl;
//        }
//
//        }else if(cmdbag == "skillItems"){
//        player.showSkillItemList();
//        cout << endl;
//        }else if(cmdbag == "chooseActive"){
//        player.showEngimonList();
//        cout << "Pilih Active Engimon dengan nomor: " ;
//        int idx;
//        cin >> idx;
//        cout << "\n";
//        player.setActiveEngi(idx-1);
//        cout << endl;
//        }else if (cmdbag == "breed"){
//        player.showEngimonList();
//        cout << "Pilih Engimon 1: " ;
//        int idx1;
//        cin >> idx1;
//        cout << "Pilih Engimon 2: " ;
//        int idx2;
//        cin >> idx2;
//        cout << "\n";
//        player.breedEngimon(idx1-1, idx2-1);
//        cout << endl;
//        }else if(cmdbag == "learn"){
//        player.showSkillItemList();
//        cout << "Pilih nomor skill item: ";
//        int idxsi;
//        cin >> idxsi;
//        player.showEngimonList();
//        cout << "Pilih nomor engimon yang akan dipelajari: ";
//        int idxengi;
//        cin >> idxengi;
//        player.useSkillItem(idxsi-1, idxengi-1);
//        cout << endl;
//        }
//        else if (cmdbag == "close"){
//        state = Jalan;
//        }else{
//        cout << "Command tidak dikenali" << endl;
//        cout << endl;
//        }
//        }
//
//        }else if (state == Battle){
//        cout << "\n";
//        show();
//        cout << "\n";
//        cout << "You are battling:" << endl;
//        wildEngi[idSurroundEnemy()].printData();
//        cout << "Battle result:" << endl;
//        if (player.battle(wildEngi[idSurroundEnemy()])){
//        if (player.addEngimon(wildEngi[idSurroundEnemy()])){
//        cout << "Kamu menang!\n"<< wildEngi[idSurroundEnemy()].getName() << " dimasukkan ke dalam inventory!" << endl;
//        SkillItem si(sg.getRandomSkill(wildEngi[idSurroundEnemy()].getElement1(), wildEngi[idSurroundEnemy()].getElement2()), 1);
//        cout << si.getSkill().getSkillName() << " didapatkan" << endl;
//        player.addSkillItem(si);
//        }else{
//        cout << "Kamu menang!\n"<< wildEngi[idSurroundEnemy()].getName() << " tidak dapat dimasukkan ke dalam inventory!" << endl;
//        }
//        wildEngi.erase(wildEngi.begin()+idSurroundEnemy());
//        addEngi();
//        state = Jalan;
//        }
//        else{
//        cout << "Kamu Kalah!" << endl;
//        if (!player.lose()){
//        cout << "Pilih Engimon lain untuk bertarung :"<< endl;
//        player.showEngimonList();
//        cout << "Pilih engimon nomor: " << endl;
//        int id;
//        cin >> id;
//        player.setActiveEngi(id-1);
//        }
//        }
//        }
//        }
//        }



