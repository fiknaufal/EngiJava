package backend;

import java.util.Vector;
import java.util.Scanner;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;

public class Engimon implements Grafik {
    protected Vector<Skill> skill;

    protected String name;
    protected String[] parentNames;
    protected String[] parentSpecies;
    protected String species;
    protected Element[] elements;
    protected String sound;
    protected static int maxExp = 4099;
    protected int exp;
    protected int cumulativeExp;
    protected Position engimonPos = new Position(); //Class Positionnya belum?
    protected String icon;
    protected int health;

    // Constructor dengan parameter health
    public Engimon(String names, String p1name, String p2name, String p1spc, String p2spc, String spc, int expr, Element e1, Element e2, int px, int py, String sounds, int health) {
        //Versi C++: Engimon(string names, string p1name, string p2name, string p1spc, string p2spc, string spc, int expr, Element e1, Element e2, int px, int py, string sounds) : engimonPos(px, py)
        //Initializernya langsung aku tambahin di bawah jadi engimonPos.setX(px) sama engimonPos.setY(py)
        //Kayanya ini masih belum sepenuhnya tertranslasikan ke Java
        name = names;
        parentNames = new String[2];
        parentNames[0] = p1name;
        parentNames[1] = p2name;
        parentSpecies = new String[2];
        parentSpecies[0] = p1spc;
        parentSpecies[1] = p2spc;
        exp = expr;
        cumulativeExp = expr;
        elements = new Element[2];
        elements[0] = e1;
        elements[1] = e2;
        sound = sounds;
        species = spc;
        engimonPos.setX(px);
        engimonPos.setY(py);
        icon = "res/eevee.png";
        this.health = health;
        
        skill = new Vector<Skill>();
    }

    // Constructor tanpa parameter health
    public Engimon(String names, String p1name, String p2name, String p1spc, String p2spc, String spc, int expr, Element e1, Element e2, int px, int py, String sounds) {
        //Versi C++: Engimon(string names, string p1name, string p2name, string p1spc, string p2spc, string spc, int expr, Element e1, Element e2, int px, int py, string sounds) : engimonPos(px, py)
        //Initializernya langsung aku tambahin di bawah jadi engimonPos.setX(px) sama engimonPos.setY(py)
        //Kayanya ini masih belum sepenuhnya tertranslasikan ke Java
        name = names;
        parentNames = new String[2];
        parentNames[0] = p1name;
        parentNames[1] = p2name;
        parentSpecies = new String[2];
        parentSpecies[0] = p1spc;
        parentSpecies[1] = p2spc;
        exp = expr;
        cumulativeExp = expr;
        elements = new Element[2];
        elements[0] = e1;
        elements[1] = e2;
        sound = sounds;
        species = spc;
        engimonPos.setX(px);
        engimonPos.setY(py);
        icon = "res/eevee.png";
        this.health = 1;
        
        skill = new Vector<Skill>();
    }

    public Engimon(Engimon other){
        //Versi C++: Engimon(const Engimon& other)
        //Di java ga ada const, adanya final. Tapi masih aga beda juga sih. Cm sementara aku ake final yak
        name = other.name;
        parentNames = new String[2];
        parentNames[0] = other.parentNames[0];
        parentNames[1] = other.parentNames[1];
        parentSpecies = new String[2];
        parentSpecies[0] = other.parentSpecies[0];
        parentSpecies[1] = other.parentSpecies[1];
        exp = other.exp;
        cumulativeExp = other.cumulativeExp;
        elements = new Element[2];
        elements[0] = other.elements[0];
        elements[1] = other.elements[1];
        sound = other.sound;
        species = other.species;
        engimonPos = other.engimonPos;
        icon = "res/eevee.png";
        this.skill = other.skill;
        
        skill = new Vector<Skill>(other.skill);
    }

    public int getLevel(){
        return exp/100;
    }

    public int getCml(){
        return cumulativeExp;
    }

    public double getElmtAdv(final Engimon lawan){
        //Versi C++: getElmtAdv(const Engimon& lawan)
        //Sama kayak tadi, adanya final bukan const.
        double[][] tabelAdv = {{0, 0, 0, 0, 0 ,0}, {0,1,0,1,0.5,2}, {0,2,1,0,1,1}, {0,1,2,1,0,1.5}, {0,1.5,1,2,1,0}, {0,0,1,0.5,2,1}};
        double[] adv = new double[4];
        adv[0] = tabelAdv[elements[0].ordinal()][lawan.elements[0].ordinal()];
        adv[1] = tabelAdv[elements[0].ordinal()][lawan.elements[1].ordinal()];
        adv[2] = tabelAdv[elements[1].ordinal()][lawan.elements[0].ordinal()];
        adv[3] = tabelAdv[elements[1].ordinal()][lawan.elements[1].ordinal()];

        double max = 0;
        for(int i = 0; i < 4; i++){
            if (adv[i] > max){
                max = adv[i];
            }
        }
        return max;
    }

    //
    public Position getEngimonPos(){
        //Versi C++: Position& getEngimonPos()
        return engimonPos;
    }

    public void setEngimonPos(int x, int y){
        engimonPos.setX(x);
        engimonPos.setY(y);
    }

    public void printSound(){
        //Kayanya kalau di GUI ini ntar perlu disesuaiin lagi?
    	TrueTypeFont font;
    	Font awtFont = new Font("Times New Roman", Font.BOLD, 50); //name, style (PLAIN, BOLD, or ITALIC), size
    	font = new TrueTypeFont(awtFont, false);
    	
    	
    	font.drawString((float) 500, (float) 300, name + " : " + sound, Color.white);
//        System.out.printf("%s : $s\n",name,sound);
    }

    public void setLevelAfterBreeding(){
        exp -= 100*3;
    }
    
    public void printSkills(){
        for(int i = 0; i< skill.size();i++){
            System.out.printf("Skill %d: \n",i+1);
            System.out.printf("   Nama Skill: %s\n", skill.get(i).getSkillName());
            System.out.printf("   Base Power: %d\n", skill.get(i).getBasePower());
            System.out.printf("   Mastery   : %d\n", skill.get(i).getMasteryLevel());
        }
    }

    public void printData(){
    	TrueTypeFont font;
    	Font awtFont = new Font("Times New Roman", Font.BOLD, 24); //name, style (PLAIN, BOLD, or ITALIC), size
    	font = new TrueTypeFont(awtFont, false);
    	
    	
    	font.drawString((float) 500, (float) 300, "Nama: "+name, Color.white);
    	font.drawString((float) 500, (float) 325, "Parent 1: "+ parentNames[0] + "species "+parentSpecies[0], Color.white);
    	font.drawString((float) 500, (float) 350, "Parent 1: "+ parentNames[1] + "species "+parentSpecies[1], Color.white);
    	font.drawString((float) 500, (float) 375, "Element: "+getElementName(elements[0]) +"/"+getElementName(elements[1]), Color.white);
    	font.drawString((float) 500, (float) 400, "Level: "+getLevel(), Color.white);
    	font.drawString((float) 500, (float) 425, "Experience: "+exp%100, Color.white);
    	font.drawString((float) 500, (float) 450, "Cumulative Experience: "+cumulativeExp, Color.white);
    	font.drawString((float) 500, (float) 475, "Health: "+health, Color.white);
       
    }
//    public void printData(){
//        //Ini juga mungkin perlu disesuaiin sama GUI
//        System.out.printf("Nama: %s\n", name);
//        System.out.printf("Parent 1: %s species %s\n",parentNames[0],parentSpecies[0]);
//        System.out.printf("Parent 2: %s species %s\n",parentNames[1],parentSpecies[1]);
//        System.out.printf("Element: %s/%s\n",getElementName(elements[0]),getElementName(elements[1]));
//        System.out.printf("Level: %d\n", getLevel());
//        System.out.printf("Experience: %d\n", exp%100);
//        System.out.printf("Cumulative Experience: %d\n", cumulativeExp);
//        printSkills();
//        System.out.println("");
//    }

    public boolean plusExp(int i){
        //Ini padanan throw apaan ya gaes?
        //Aku ubah pake print dulu
        exp += i;
        cumulativeExp += i;
        if(cumulativeExp >= maxExp){
            //Versi c++: throw "mati bosku";
            System.out.println("mati bosku");
            return true;
        }
        return false;
    }

    public boolean addSkill(final Skill sk){
        //Versi C++: addSkill(const Skill& sk)
        if (skill.size() < 4){
            for (int i = 0; i < skill.size(); i++){
                //versi C++: if(sk == skill[i])
                if(sk.isEqual(skill.get(i))){
                    System.out.println("Skill sudah dipelajari\n");
                    return false;
                }
            }
            skill.addElement(sk);
            return true;
        }
        else{
            System.out.println("Skill sudah penuh\n");
            printSkills();
            System.out.print("Pilih nomor skill yang ingin dihapus: ");

            Scanner input = new Scanner(System.in);
            int idxskill = input.nextInt();

            System.out.printf("%s dihapus!\n", skill.get(idxskill-1).getSkillName());
            skill.remove(idxskill-1);
            skill.addElement(sk);

            input.close();
            return true;
        }
    }

    public void setElement2(Element el){
        elements[1] = el;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSpecies(){
        return species;
    }

    public String getSound(){
        return sound;
    }

    public Element getElement1(){
        return elements[0];
    }

    public Element getElement2(){
        return elements[1];
    }

    public char getMapSymbol(int n){
        char[] big = {'F','W','E','G','I'};
        char[] small = {'f','w','e','g','i'};

        if(exp/100 < n){
            return small[elements[0].ordinal()-1];
        }
        else{
            return big[elements[0].ordinal()-1];
        }
    }

    public Vector<Skill> getSkill(){
        return skill;
    }

    public Position randomMove(int n){
        int x = engimonPos.getX();
        int y = engimonPos.getY();
        Position p = new Position(x,y);
        if(n == 1){
            p.setX(x+1);
        }
        else if(n == 2){
            p.setX(x-1);
        }
        else if(n == 3){
            p.setY(y+1);
        }
        else if(n == 4){
            p.setY(y-1);
        }
        return p;
    }

    public String getElementName(Element e){
        //Ini kalau egk salah jadi UPPERCASE semua karena sifat dari enum di Java
        //Mohon koreksinya
        //versi C++: Huruf pertama uppercase, setelahnya lowercase (ex: None, Fire,Electric)
        if(e == Element.NONE){
            return "NONE";
        }
        if (e == Element.FIRE){
            return "FIRE";
        }
        if (e == Element.ELECTRIC){
            return "Electric";
        }
        if (e == Element.GROUND){
            return "Ground";
        }
        if (e == Element.WATER){
            return "Water";
        } 
        if (e == Element.ICE){
            return "ICE";
        }
        else{
            return "Gaada bang";
        }
    }

    public int decreaseHealth() {
        health--;
        return health;
    }
    

    //Getter buat SaveLoad
    public String[] getParentNames() {
        return parentNames;
    }

    public String[] getParentSpecies() {
        return parentSpecies;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public int getExp() {
        return exp;
    }

    public int getHealth() {
        return health;
    }
    
    @Override
    public String getIcon() {
        return icon;
    }

    public Engimon(String names, String p1name, String p2name, String p1spc, String p2spc, String spc, int expr, Element e1, Element e2, int px, int py, String sounds, int health, int cumExp, int maxXp) {
        //Versi C++: Engimon(string names, string p1name, string p2name, string p1spc, string p2spc, string spc, int expr, Element e1, Element e2, int px, int py, string sounds) : engimonPos(px, py)
        //Initializernya langsung aku tambahin di bawah jadi engimonPos.setX(px) sama engimonPos.setY(py)
        //Kayanya ini masih belum sepenuhnya tertranslasikan ke Java
        name = names;
        parentNames = new String[2];
        parentNames[0] = p1name;
        parentNames[1] = p2name;
        parentSpecies = new String[2];
        parentSpecies[0] = p1spc;
        parentSpecies[1] = p2spc;
        exp = expr;
        elements = new Element[2];
        elements[0] = e1;
        elements[1] = e2;
        sound = sounds;
        species = spc;
        engimonPos.setX(px);
        engimonPos.setY(py);
        this.health = health;
        cumulativeExp = cumExp;
        maxExp = maxXp;
    }
}