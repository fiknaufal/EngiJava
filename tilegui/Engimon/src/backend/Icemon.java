package backend;
import java.util.Vector;

public class Icemon extends Engimon {
    public Icemon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py) {
        super(name, p1name, p2name, p1spc, p2spc,"Icemon", exp, Element.ICE, Element.NONE, px, py, "BRRR BRRR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.ICE, Element.NONE);
    }

    public Icemon(int x, int px, int py) {
        super("Wild Icemon", "Icemon Ibu", "Icemon Ibu juga", "Icemon", "Icemon", "Icemon", x, Element.ICE, Element.NONE, px, py, "BRRR BRRR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.ICE, Element.NONE);
    }

    public Icemon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py, int health) {
        super(name, p1name, p2name, p1spc, p2spc,"Icemon", exp, Element.ICE, Element.NONE, px, py, "BRRR BRRR", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.ICE, Element.NONE);
    }

    public Icemon(int x, int px, int py, int health) {
        super("Wild Icemon", "Icemon Ibu", "Icemon Ibu juga", "Icemon", "Icemon", "Icemon", x, Element.ICE, Element.NONE, px, py, "BRRR BRRR", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.ICE, Element.NONE);
    }

    //Constructor buat Load
    public Icemon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py, int health, int cumExp, int maxExp, Vector<Skill> sk) {
        super(name, p1name, p2name, p1spc, p2spc,"Icemon", exp, Element.WATER, Element.GROUND, px, py, "BRRR BRRR", health, cumExp, maxExp);
        skill = sk;
    }
}