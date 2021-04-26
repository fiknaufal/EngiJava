package backend;
import java.util.Vector;

public class Electricmon extends Engimon {
    public Electricmon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py) {
        super(name, p1name, p2name, p1spc, p2spc,"Electricmon", exp, Element.ELECTRIC, Element.NONE, px, py, "AYAYAYAYYY");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.ELECTRIC, Element.NONE);
    }

    public Electricmon(int x, int px, int py) {
        super("Wild Electricmon", "Electricmon Ibu", "Electricmon Ibu juga", "Electricmon", "Electricmon", "Electricmon", x, Element.ELECTRIC, Element.NONE, px, py, "AYAYAYAYYY");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.ELECTRIC, Element.NONE);
    }

    public Electricmon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py, int health) {
        super(name, p1name, p2name, p1spc, p2spc,"Electricmon", exp, Element.ELECTRIC, Element.NONE, px, py, "AYAYAYAYYY", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.ELECTRIC, Element.NONE);
    }

    public Electricmon(int x, int px, int py, int health) {
        super("Wild Electricmon", "Electricmon Ibu", "Electricmon Ibu juga", "Electricmon", "Electricmon", "Electricmon", x, Element.ELECTRIC, Element.NONE, px, py, "AYAYAYAYYY", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.ELECTRIC, Element.NONE);
    }

    //Constructor buat Load
    public Electricmon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py, int health, int cumExp, int maxExp, Vector<Skill> sk) {
        super(name, p1name, p2name, p1spc, p2spc,"Electricmon", exp, Element.WATER, Element.GROUND, px, py, "AYAYAYAYYY", health, cumExp, maxExp);
        skill = sk;
    }
}