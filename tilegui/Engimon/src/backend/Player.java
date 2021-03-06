package backend;

import java.awt.Font;
import java.util.*;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import data.Pointer;


public class Player implements Grafik {

    private Position playerPos = new Position();
    private Inventory<Engimon> inventoryE = new Inventory<Engimon>();
    private Inventory<SkillItem> inventoryS = new Inventory<SkillItem>();
    private int maxInv;
    private int idActiveEngimon;
    private String icon;
    int udahDipencet = 0;

    public int firstBreedEngi = -1;
    public int secondBreedEngi = -1;

    public Player(){
        playerPos.setX(1);
        playerPos.setY(0);
        maxInv = 50;
        idActiveEngimon = 0;
    }

    public boolean lose(){
        return inventoryE.getSize() == 0;
    }

    public void showActiveEngimon(){
        inventoryE.getElement(idActiveEngimon).printData();
    }

    public void showEngimon(int idx){
        inventoryE.getElement(idx).printData();
    }
    
    public int maxLevelEngi() {
    	int n = 0;
    	for(int i = 0; i < inventoryE.getSize(); i++) {
    		if(n < inventoryE.getElement(i).getLevel()) {
    			n = inventoryE.getElement(i).getLevel();
    		}
    	}
    	return n;
    }
    
    public boolean Move(int bawah, int kanan){
        if (Keyboard.isKeyDown(Keyboard.KEY_W) && playerPos.getY() != 0 && udahDipencet == 0) {
        	inventoryE.getElement(idActiveEngimon).setEngimonPos(playerPos.getX(), playerPos.getY());
            int curY = playerPos.getY();
            playerPos.setY(curY-1);
            udahDipencet = 1;
            return true;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A) && playerPos.getX() != 0&& udahDipencet == 0){
        	inventoryE.getElement(idActiveEngimon).setEngimonPos(playerPos.getX(), playerPos.getY());
            int curX = playerPos.getX();
            playerPos.setX(curX-1);
            udahDipencet = 1;
            return true;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S) && playerPos.getY() != bawah&& udahDipencet == 0){
        	inventoryE.getElement(idActiveEngimon).setEngimonPos(playerPos.getX(), playerPos.getY());
            int curY = playerPos.getY();
            playerPos.setY(curY+1);
            udahDipencet = 1;
            return true;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D) && playerPos.getX() != kanan&& udahDipencet == 0){
        	inventoryE.getElement(idActiveEngimon).setEngimonPos(playerPos.getX(), playerPos.getY());
            int curX = playerPos.getX();
            playerPos.setX(curX+1);
            udahDipencet = 1;
            return true;
        }
        if(!(Keyboard.isKeyDown(Keyboard.KEY_W) | Keyboard.isKeyDown(Keyboard.KEY_A) | Keyboard.isKeyDown(Keyboard.KEY_S) | Keyboard.isKeyDown(Keyboard.KEY_D))) {
        	udahDipencet = 0;
        }
        return false;
    }

    public Position getActivePos(){
        return inventoryE.getElement(idActiveEngimon).getEngimonPos();
    }

    public void MoveActiveEngi(){
        int x = playerPos.getX(), y = playerPos.getY(), x1 = x, y1 = y-1;
        boolean outidx = false;
        if(x1 < 0 || x1 > 14 || y1 < 0 || y1 > 14/* || obstacle()*/){ // bawah gabisa
            outidx = true;
            y1 = y+1;
        }
        if(x1 < 0 || x1 > 14 || y1 < 0 || y1 > 14){ // atas gabisa
            outidx = true;
            x1 = x-1;
            y1 = y;
        }
        if(x1 < 0 || x1 > 14 || y1 < 0 || y1 > 14){ // kiri gabisa
            outidx = true;
            x1 = x+1;
        }
        if(x1 < 0 || x1 > 14 || y1 < 0 || y1 > 14){ // kanan gabisa
            System.out.print("anjir dikepung");
        }
        else{
            inventoryE.getElement(idActiveEngimon).setEngimonPos(x1, y1);
        }
        if(outidx){
//            throw "bambang mau kemana sih\n"; belom jadi wkwk
        }
    }

    public Position getPlayerPos(){
        return playerPos;
    }

    public int getInvCount(){
        return getEngimonCount() + getSkillItemCount();
    }

    public int getEngimonCount() {
    	return inventoryE.getSize();
    }
    
    public int getSkillItemCount() {
    	int n = 0;
        ArrayList<SkillItem> v = inventoryS.getArray();
        for(int i = 0; i < v.size(); i++){
            n += inventoryS.getElement(i).getJumlah();
        }
        return n;
    }
    
    public int getUniqueSkillItem() {
    	if(inventoryS.getSize() != 0) {
    		return inventoryS.getSize();
    	}
    	return -1;
    }
    public boolean addEngimon(Engimon e){
        if(getInvCount() < maxInv){
            inventoryE.add(e);
            inventoryE.getArray().sort(new EngimonSorter());
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public String getIcon() {
        return icon;
    }

    public void removeEngimon(int i){
        inventoryE.removeAtIdx(i);
    }

    public void changeEngimonName(int idx, String name){
        inventoryE.getElement(idx).setName(name);
    }
    public class EngimonSorter implements Comparator<Engimon>{

        @Override
        public int compare(Engimon o1, Engimon o2) {
            int step1 = o1.getElement1().compareTo(o2.getElement1());
            if(step1 == 0){
                int step2 = o1.getElement2().compareTo(o2.getElement2());
                if(step2 == 0){
                    int step3 = Integer.compare(o1.getLevel(), o2.getLevel());
                    if(step3 == 0){
                        return o1.getName().compareToIgnoreCase(o2.getName());
                    }
                    return step3;
                }
                return step2;
            }
            return step1;
        }
    }

    public boolean addSkillItem(SkillItem s){
        if(getInvCount() < maxInv){
            Optional<SkillItem> sa = inventoryS.getArray().stream().filter(o -> o.getSkill().isEqual(s.getSkill())).findAny();
            if(sa.isPresent()){
                sa.get().add(s.getJumlah());
            }
            else{
                inventoryS.add(s);
                inventoryS.getArray().sort(new SkillItemSorter());
            }
            return true;
        }
        else{
            return false;
        }
    }

    public boolean removeSkillItem(int idx, int n){
        SkillItem sa = inventoryS.getElement(idx);
        if(sa.getJumlah() < n){
            return false;
        }
        else{
            sa.add(-n);
            updateSkillItem();
            return true;
        }
    }

    public class SkillItemSorter implements Comparator<SkillItem>{

        @Override
        public int compare(SkillItem o1, SkillItem o2) {
            return Integer.compare(o1.getSkill().getBasePower(), o2.getSkill().getBasePower());
        }
    }

//    public void showEngimonList(){
//        int j = 1;
//        System.out.println("List of Engimon");
//        Engimon engi;
//        for(int i = 0; i < inventoryE.getSize(); i++){
//            engi = inventoryE.getElement(i);
//            System.out.printf("%d. %s %s lv. %d\n", j, engi.getName(), engi.getSpecies(), engi.getLevel());
//            j++;
//        }
//    }
    
    public void showEngimonList(float hOffset, int idx){
    	TrueTypeFont font;
    	Font awtFont = new Font("Times New Roman", Font.BOLD, 24); //name, style (PLAIN, BOLD, or ITALIC), size
    	font = new TrueTypeFont(awtFont, false);
    	
    	
        int j = 1;
        String s = "(dipilih)";
        System.out.println("List of Engimon");
        Engimon engi;
        for(int i = 0; i < inventoryE.getSize(); i++){
            engi = inventoryE.getElement(i);
            if(i == idx) {
            	font.drawString(hOffset, (float) 300 + i*25, String.format("%d. %s %s lv. %d %s\n", j, engi.getName(), engi.getSpecies(), engi.getLevel(), s), Color.white);
            }
            else {
            	font.drawString(hOffset, (float) 300 + i*25, String.format("%d. %s %s lv. %d\n", j, engi.getName(), engi.getSpecies(), engi.getLevel()), Color.white);
            }
            j++;
        }
    }

    public void showSkillItemList(int idx){
    	TrueTypeFont font;
    	Font awtFont = new Font("Times New Roman", Font.BOLD, 24); //name, style (PLAIN, BOLD, or ITALIC), size
    	font = new TrueTypeFont(awtFont, false);
    	
        int j = 1;
        String s = "(learn)";
        font.drawString((float) 500, (float) 250, "List of Skill Item", Color.white);
        ArrayList<SkillItem> e = inventoryS.getArray();
        for(int i = 0; i < e.size(); i++){
        	if(i == idx) {
            	font.drawString((float) 500, (float) 300 + i*25, String.format("%d. %s lv. %d %s\n", j, e.get(i).getSkill().getSkillName(), e.get(i).getJumlah(), s), Color.white);
            }
            else {
            	font.drawString((float) 500, (float) 300 + i*25, String.format("%d. %s lv. %d\n", j, e.get(i).getSkill().getSkillName(), e.get(i).getJumlah()), Color.white);
            }
            j++;
        }
    }

    public boolean useSkillItem(int idxsi, int idxengi){
        boolean hasil = inventoryS.getElement(idxsi).learnSkill(inventoryE.getElement(idxengi));
        updateSkillItem();
        return hasil;
    }

    public void setActiveEngi(int i){
        inventoryE.getElement(idActiveEngimon).setEngimonPos(-1,-1);
        if (i < inventoryE.getSize()){
            idActiveEngimon = i;
        }
        else{
            System.out.println("Id tidak valid, set Active Engimon pada Engimon pertama list");
            idActiveEngimon = 0;
        }
    }

    public void petEngi(){
        inventoryE.getElement(idActiveEngimon).printSound();
    }

    // Fungsi gelud
    public boolean battle(Engimon e) {
        int powerE1 = 0;
        int powerE2 = 0;
        Engimon pEngi = inventoryE.getElement(idActiveEngimon);

        for (int i=0; i<pEngi.getSkill().size(); i++)
            powerE1 += pEngi.getSkill().get(i).getBasePower() * pEngi.getSkill().get(i).getMasteryLevel();

        for (int i=0; i<e.getSkill().size(); i++) 
            powerE2 = e.getSkill().get(i).getBasePower() * e.getSkill().get(i).getMasteryLevel();

        powerE1 += pEngi.getLevel() * pEngi.getElmtAdv(e);
        powerE2 += e.getLevel() * e.getElmtAdv(pEngi);

        if (powerE1 < powerE2) {
            if (pEngi.decreaseHealth() == 0) {
            	inventoryE.removeAtIdx(idActiveEngimon);
	            setActiveEngi(0);
	            MoveActiveEngi();
            }
            return false;
        }

        else {
            pEngi.plusExp(50);
            if (pEngi.getCml() >= 4000) {
                inventoryE.removeAtIdx(idActiveEngimon);
                setActiveEngi(0);
                MoveActiveEngi();
                System.out.println("Active Engimon mati karena melampaui batas level maksimal!\nActive Engimon sekarang adalah Engimon teratas di list.");
            }
            return true;
        }
    }
//    bool Player::battle(Engimon e) {
//        int powerE1 = 0;
//        int powerE2 = 0;
//
//        // Misalkan atribut skill udh ad di engimon
//        for (int i=0; i<inventoryE.getVector()[idActiveEngimon].getSkill().size(); i++) {
//            powerE1 += inventoryE.getVector()[idActiveEngimon].getSkill()[i].getBasePower() * inventoryE.getVector()[idActiveEngimon].getSkill()[i].getMasteryLevel();
//        }
//
//        for (int i=0; i<e.getSkill().size(); i++) {
//            powerE2 += e.getSkill()[i].getBasePower() * e.getSkill()[i].getMasteryLevel();
//        }
//
//        powerE1 += inventoryE.getVector()[idActiveEngimon].getLevel() * inventoryE.getVector()[idActiveEngimon].getElmtAdv(e);
//        powerE2 += e.getLevel() * e.getElmtAdv(inventoryE.getVector()[idActiveEngimon]);
//
//        if (powerE1 < powerE2){
//            inventoryE.removeAtIdx(idActiveEngimon);
//        }else{
//            inventoryE.getVector()[idActiveEngimon].plusExp(50);
//            if (inventoryE.getVector()[idActiveEngimon].getCml() >= 4000){
//                inventoryE.removeAtIdx(idActiveEngimon);
//                setActiveEngi(0);
//                cout << "Active Engimon mati karena melampaui batas level maksimal!\nActive Engimon sekarang adalah Engimon teratas di list." << endl;
//            }
//        }
//        return (powerE1 >= powerE2);
//    }
//

    // Fungsi kawin
    public void breedEngimon (int idxA, int idxB) {

        // Cek level ortu
        if ((inventoryE.getArray().get(idxA).getLevel() < 4) && (inventoryE.getArray().get(idxB).getLevel() < 4)) {
            System.out.println("Level Engimon belum cukup");
            return;
        }

        // Cek inventory
        else if (this.getInvCount() > maxInv) {
            System.out.println("Inventory penuh");
            return;
        }

        // Nyalin engimon ortu
        Engimon engiA = new Engimon(inventoryE.getArray().get(idxA));
        engiA.setElement2(Element.NONE);
        Engimon engiB = new Engimon(inventoryE.getArray().get(idxB));
        engiA.setElement2(Element.NONE);
        
        // Nentuin elemen anak
        Element[] childElmt = {Element.NONE, Element.NONE};
        String spc, sound;
        if (engiA.getElmtAdv(engiB) > 1) {
            childElmt[0] = engiA.getElement1();
            spc = engiA.getSpecies();
            sound = engiA.getSound();
        }
        else if (engiB.getElmtAdv(engiA) > 1) {
            childElmt[0] = engiB.getElement1();
            spc = engiB.getSpecies();
            sound = engiB.getSound();
        }
        else {
            if (engiA.getElement1() == engiB.getElement1()) {
                childElmt[0] = engiA.getElement1();
                spc = engiA.getSpecies();
                sound = engiA.getSound();
            }
            else {
                childElmt[0] = engiA.getElement1().ordinal() > engiB.getElement1().ordinal() ? engiA.getElement1() : engiB.getElement1();
                childElmt[1] = engiA.getElement1().ordinal() < engiB.getElement1().ordinal() ? engiA.getElement1() : engiB.getElement1();
                if (childElmt[0] == Element.FIRE) {
                    spc = "FireElectricmon";
                }
                else {
                    if (childElmt[1] == Element.ICE) {
                        spc = "WaterIcemon";
                    }
                    else {
                        spc = "WaterGroundmon";
                    }
                }
                sound = engiA.getSound();
            }
        }
        
        String childName = "Bob";

        // Buat objek Engimon baru dgn element dan nama di atas
        Engimon anak = new Engimon(childName, engiA.getName(), engiB.getName(), engiA.getSpecies(), engiB.getSpecies(), spc, 100, childElmt[0], childElmt[1], -1, -1, sound, 3);

        System.out.println("Udah construct anak");
        
        // Masukin skill-skill nya
        Vector<Skill> skillsA = new Vector<Skill>(engiA.getSkill());
        Vector<Skill> skillsB = new Vector<Skill>(engiB.getSkill());

        // Singkirin skill yang g kompatibel (UPDATE: G perlu lg)
        
        // Nentuin ngambil signature skill anak atau dari ortu
        SkillGacha sg = new SkillGacha();
        Skill signatureSkill = sg.getRandomSkill(childElmt[0], childElmt[1]);
        boolean foundSignSkill = false;
        int i=0;
        
        int engiASkillCount = skillsA.size();
        int engiBSkillCount = skillsB.size();
        while ((i<4) && !foundSignSkill) {
            if (signatureSkill.isEqual(skillsA.get(i%engiASkillCount)) || signatureSkill.isEqual(skillsB.get(i%engiBSkillCount)))
                foundSignSkill = true;
            i++;
        }
        
        if (!foundSignSkill)
            anak.addSkill(signatureSkill);

        System.out.println("Udah skill bagian 1");
        
        // Masukin ke engimon anak
        i=0;
        while ((anak.getSkill().size() < 4) && (!skillsA.isEmpty() || !skillsB.isEmpty())) {
            int skillAIdx = -1;
            if (!skillsA.isEmpty()) {
                for(i=0; i<skillsA.size(); i++) {
                    if (skillAIdx == -1) {
                        skillAIdx = i;
                    }
                    else if (skillsA.get(i).getMasteryLevel() > skillsA.get(skillAIdx).getMasteryLevel()) {
                        skillAIdx = i;
                    }
                }
            }

            int skillBIdx = -1;
            if (!skillsB.isEmpty()) {
                for(i=0; i<skillsB.size(); i++) {
                    if (skillBIdx == -1) {
                        skillBIdx = i;
                    }
                    else if (skillsB.get(i).getMasteryLevel() > skillsB.get(skillBIdx).getMasteryLevel()) {
                        skillBIdx = i;
                    }
                }
            }

            if ((skillBIdx > -1) && (skillAIdx > -1)) {

                if (skillsA.get(skillAIdx).getMasteryLevel() > skillsB.get(skillBIdx).getMasteryLevel()) {
                    anak.addSkill(skillsA.get(skillAIdx));
                    skillsA.remove(skillAIdx); //(skillsA.begin() + skillAIdx);
                }
                else if (skillsA.get(skillAIdx).getMasteryLevel() < skillsB.get(skillBIdx).getMasteryLevel()) {
                    anak.addSkill(skillsB.get(skillBIdx));
                    skillsB.remove(skillBIdx);//erase(skillsB.begin() + skillBIdx);
                }
                else {
                    if (skillsA.get(skillAIdx).isEqual(skillsB.get(skillBIdx))) {
                        Skill upgradedSkill = skillsA.get(skillAIdx);
                        upgradedSkill.masteryLevelUp();
                        anak.addSkill(upgradedSkill);
                        skillsA.remove(skillAIdx); //erase(skillsA.begin() + skillAIdx);
                        skillsB.remove(skillBIdx); //erase(skillsB.begin() + skillBIdx);
                    }
                    else {
                        anak.addSkill(skillsA.get(skillAIdx));
                        skillsA.remove(skillAIdx); // erase(skillsA.begin() + skillAIdx);
                    }
                }
            }

            else {
                if (skillAIdx > -1) {
                    anak.addSkill(skillsA.get(skillAIdx));
                    skillsA.remove(skillAIdx); // erase(skillsA.begin() + skillAIdx);
                }

                else {
                    anak.addSkill(skillsB.get(skillBIdx));
                    skillsB.remove(skillBIdx); // erase(skillsB.begin() + skillBIdx);
                }
            }
        }
        // Tambahin anak ke list
        inventoryE.getArray().get(idxA).setLevelAfterBreeding();
        inventoryE.getArray().get(idxB).setLevelAfterBreeding();
        addEngimon(anak);
    }
    

    //Getter buat SaveLoad
    public Inventory<Engimon> getInventoryE() {
        return inventoryE;
    }

    public Inventory<SkillItem> getInventoryS() {
        return inventoryS;
    }

    public int getMaxInv() {
        return maxInv;
    }

    public int getIdActiveEngimon() {
        return idActiveEngimon;
    }

    //Setter buat SaveLoad
    public void setPlayerPos(int x, int y) {
        playerPos.setX(x);
        playerPos.setY(y);
    }
//    // fungsi breeding
//// Tinggal digabungin ke class player jg, nnt anaknya langsung dimasukin ke list aj, kgk usah jd output
//    void Player::breedEngimon (int idxA, int idxB) {
//
//        int breedingCase = -1;
//
//        // Cek level ortu
//        if ((inventoryE.getVector()[idxA].getLevel() <= 30) || (inventoryE.getVector()[idxB].getLevel() <= 30))
//            // Bingung antara throw atau munculin output aj
//            cout << "Level Engimon belum cukup" << endl;
//
//            // Cek muat atau g list engimon player (Blom bise diimplementasiin)
//        else if (this->getInvCount() > maxInv)
//        cout << "Inventory penuh" << endl;
//
//    /*
//    // Cek elemen ortu dual atau g, mungkin di-g bisa dlu-in
//    else if ((inventoryE.getVector()[idxA].getElement2() != None) || (inventoryE.getVector()[idxB].getElement2() != None))
//        cout << "Dual elmt engimon g punya kelamin" << endl;
//    */
//
//    else {
//            Engimon engiA(inventoryE.getVector()[idxA]);
//            engiA.setElement2(None);
//            Engimon engiB(inventoryE.getVector()[idxB]);
//            engiB.setElement2(None);
//
//            Element childElmt[2] = {None, None};
//            string spc; string sound;
//            if (engiA.getElmtAdv(engiB) > 1) {
//                childElmt[0] = engiA.getElement1();
//                spc = engiA.getSpecies();
//                sound = engiA.getSound();
//
//                breedingCase = 0;
//            }
//            else if (engiB.getElmtAdv(engiA) > 1) {
//                childElmt[0] = engiB.getElement1();
//                spc = engiB.getSpecies();
//                sound = engiB.getSound();
//
//                breedingCase = 1;
//            }
//            else {
//                if (engiA.getElement1() == engiB.getElement1()) {
//                    childElmt[0] = engiA.getElement1();
//                    spc = engiA.getSpecies();
//                    sound = engiA.getSound();
//
//                    breedingCase = 2;
//                }
//                else {
//                    childElmt[0] = min(engiA.getElement1(), engiB.getElement1());
//                    childElmt[1] = max(engiA.getElement1(), engiB.getElement1());
//                    if (childElmt[0] == Fire) {
//                        spc = "FireElectricmon";
//                    }
//                    else {
//                        if (childElmt[1] == Ice) {
//                            spc = "WaterIcemon";
//                        }
//                        else {
//                            spc = "WaterGroundmon";
//                        }
//                    }
//                    sound = engiA.getSound();
//
//                    breedingCase = 3;
//                }
//            }
//
//            // Masukin nama
//            string childName;
//            cout << "Your new engimon name: ";
//            cin >> childName;
//
//            // Buat objek Engimon baru dgn element dan nama diatas
//            Engimon anak = Engimon(childName, engiA.getName(), engiB.getName(), engiA.getSpecies(), engiB.getSpecies(), spc, 3100, childElmt[0], childElmt[1], -1, -1, sound);
//
//            // Masukin skill-skill nya
//            vector<Skill> skillsA (engiA.getSkill());
//            vector<Skill> skillsB (engiB.getSkill());
//
//
//            // Singkirin skill yg g kompatibel
//            int i = 0;
//            while ((!skillsA.empty()) && (i< skillsA.size())) {
//                bool compatible = false;
//                for (Element e : skillsA[i].getElement()) {
//                    if ((e == childElmt[0]) || (e == childElmt[1]))
//                        compatible = true;
//                }
//
//                if (!compatible) {
//                    skillsA.erase(skillsA.begin() + i);
//                }
//                else {
//                    i++;
//                }
//            }
//
//            i = 0;
//            while ((!skillsB.empty()) && (i < skillsB.size())) {
//                bool compatible = false;
//                for (Element e : skillsB[i].getElement()) {
//                    if ((e == childElmt[0]) || (e == childElmt[1]))
//                        compatible = true;
//                }
//
//                if (!compatible) {
//                    skillsB.erase(skillsB.begin() + i);
//                }
//                else {
//                    i++;
//                }
//            }
//
//
//            while ((anak.getSkill().size() <= 4) && (!skillsA.empty() || !skillsB.empty())) {
//                int skillAIdx = -1;
//                if (!skillsA.empty()) {
//                    for(int i=0; i<skillsA.size(); i++) {
//                        if (skillAIdx == -1) {
//                            skillAIdx = i;
//                        }
//                        else if (skillsA[i].getMasteryLevel() > skillsA[skillAIdx].getMasteryLevel()) {
//                            skillAIdx = i;
//                        }
//                    }
//                }
//
//                int skillBIdx = -1;
//                if (!skillsB.empty()) {
//                    for(int i=0; i<skillsB.size(); i++) {
//                        if (skillBIdx == -1) {
//                            skillBIdx = i;
//                        }
//                        else if (skillsB[i].getMasteryLevel() > skillsB[skillBIdx].getMasteryLevel()) {
//                            skillBIdx = i;
//                        }
//                    }
//                }
//
//                if ((skillBIdx > -1) && (skillAIdx > -1)) {
//
//                    if (skillsA[skillAIdx].getMasteryLevel() > skillsB[skillBIdx].getMasteryLevel()) {
//                        anak.addSkill(skillsA[skillAIdx]);
//                        skillsA.erase(skillsA.begin() + skillAIdx);
//                    }
//                    else if (skillsA[skillAIdx].getMasteryLevel() < skillsB[skillBIdx].getMasteryLevel()) {
//                        anak.addSkill(skillsB[skillBIdx]);
//                        skillsB.erase(skillsB.begin() + skillBIdx);
//                    }
//                    else {
//                        if (skillsA[skillAIdx] == skillsB[skillBIdx]) {
//                            Skill upgradedSkill = skillsA[skillAIdx];
//                            upgradedSkill.masteryLevelUp();
//                            anak.addSkill(upgradedSkill);
//                            skillsA.erase(skillsA.begin() + skillAIdx);
//                            skillsB.erase(skillsB.begin() + skillBIdx);
//                        }
//                        else {
//                            anak.addSkill(skillsA[skillAIdx]);
//                            skillsA.erase(skillsA.begin() + skillAIdx);
//                        }
//                    }
//                }
//
//                else {
//                    if (skillAIdx > -1) {
//                        anak.addSkill(skillsA[skillAIdx]);
//                        skillsA.erase(skillsA.begin() + skillAIdx);
//                    }
//
//                    else {
//                        anak.addSkill(skillsB[skillBIdx]);
//                        skillsB.erase(skillsB.begin() + skillBIdx);
//                    }
//                }
//            }
//
//
//            // Tambahin anak ke list
//            addEngimon(anak);
//            inventoryE.getVector()[idxA].setLevelAfterBreeding();
//            inventoryE.getVector()[idxB].setLevelAfterBreeding();
//        }
//    }
//
    public void updateSkillItem(){
        int i = 0;
        while(i < inventoryS.getSize()){
            if(inventoryS.getElement(i).getJumlah() == 0){
                inventoryS.removeAtIdx(i);
            }
            else{
                i++;
            }
        }
    }
}
