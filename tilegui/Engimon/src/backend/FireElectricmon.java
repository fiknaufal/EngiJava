import java.util.Vector;

public class FireElectricmon extends Engimon {
    public FireElectricmon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py) {
        super(name, p1name, p2name, p1spc, p2spc,"FireElectricmon", exp, Element.FIRE, Element.ELECTRIC, px, py, "ROAR AYAY");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.FIRE, Element.ELECTRIC);
    }

    public FireElectricmon(int x, int px, int py) {
        super("Wild FireElectricmon", "FireElectricmon Ibu", "FireElectricmon Ibu juga", "FireElectricmon", "FireElectricmon", "FireElectricmon", x, Element.FIRE, Element.ELECTRIC, px, py, "ROAR AYAY");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.FIRE, Element.ELECTRIC);
    }

    public FireElectricmon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py, int health) {
        super(name, p1name, p2name, p1spc, p2spc,"FireElectricmon", exp, Element.FIRE, Element.ELECTRIC, px, py, "ROAR AYAY", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.FIRE, Element.ELECTRIC);
    }

    public FireElectricmon(int x, int px, int py, int health) {
        super("Wild FireElectricmon", "FireElectricmon Ibu", "FireElectricmon Ibu juga", "FireElectricmon", "FireElectricmon", "FireElectricmon", x, Element.FIRE, Element.ELECTRIC, px, py, "ROAR AYAY", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.FIRE, Element.ELECTRIC);
    }

    //Constructor buat Load
    public FireElectricmon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py, int health, int cumExp, int maxExp, Vector<Skill> sk) {
        super(name, p1name, p2name, p1spc, p2spc,"FireElectricmon", exp, Element.WATER, Element.GROUND, px, py, "ROAR AYAY", health, cumExp, maxExp);
        skill = sk;
    }
}