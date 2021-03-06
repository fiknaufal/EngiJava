import java.util.Vector;

public class Firemon extends Engimon {
    public Firemon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py) {
        super(name, p1name, p2name, p1spc, p2spc,"Firemon", exp, Element.FIRE, Element.NONE, px, py, "ROARRR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.FIRE, Element.NONE);
    }

    public Firemon(int x, int px, int py) {
        super("Wild Firemon", "Firemon Ibu", "Firemon Ibu juga", "Firemon", "Firemon", "Firemon", x, Element.FIRE, Element.NONE, px, py, "ROARRR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.FIRE, Element.NONE);
    }

    public Firemon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py, int health) {
        super(name, p1name, p2name, p1spc, p2spc,"Firemon", exp, Element.FIRE, Element.NONE, px, py, "ROARRR", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.FIRE, Element.NONE);
    }

    public Firemon(int x, int px, int py, int health) {
        super("Wild Firemon", "Firemon Ibu", "Firemon Ibu juga", "Firemon", "Firemon", "Firemon", x, Element.FIRE, Element.NONE, px, py, "ROARRR", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.FIRE, Element.NONE);
    }

    //Constructor buat Load
    public Firemon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py, int health, int cumExp, int maxExp, Vector<Skill> sk) {
        super(name, p1name, p2name, p1spc, p2spc,"Firemon", exp, Element.WATER, Element.GROUND, px, py, "ROARRR", health, cumExp, maxExp);
        skill = sk;
    }
}