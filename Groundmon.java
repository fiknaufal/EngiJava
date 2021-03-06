import java.util.Vector;

public class Groundmon extends Engimon {
    public Groundmon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py) {
        super(name, p1name, p2name, p1spc, p2spc,"Groundmon", exp, Element.GROUND, Element.NONE, px, py, "BUMBUMMM");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.GROUND, Element.NONE);
    }

    public Groundmon(int x, int px, int py) {
        super("Wild Groundmon", "Groundmon Ibu", "Groundmon Ibu juga", "Groundmon", "Groundmon", "Groundmon", x, Element.GROUND, Element.NONE, px, py, "BUMBUMMM");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.GROUND, Element.NONE);
    }

    public Groundmon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py, int health) {
        super(name, p1name, p2name, p1spc, p2spc,"Groundmon", exp, Element.GROUND, Element.NONE, px, py, "BUMBUMMM", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.GROUND, Element.NONE);
    }

    public Groundmon(int x, int px, int py, int health) {
        super("Wild Groundmon", "Groundmon Ibu", "Groundmon Ibu juga", "Groundmon", "Groundmon", "Groundmon", x, Element.GROUND, Element.NONE, px, py, "BUMBUMMM", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.GROUND, Element.NONE);
    }

    //Constructor buat Load
    public Groundmon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py, int health, int cumExp, int maxExp, Vector<Skill> sk) {
        super(name, p1name, p2name, p1spc, p2spc,"Groundmon", exp, Element.WATER, Element.GROUND, px, py, "BUMBUMMM", health, cumExp, maxExp);
        skill = sk;
    }
}