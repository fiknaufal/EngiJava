import java.util.Vector;

public class WaterIcemon extends Engimon {
    public WaterIcemon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py) {
        super(name, p1name, p2name, p1spc, p2spc,"WaterIcemon", exp, Element.WATER, Element.ICE, px, py, "BYUR BRRR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.WATER, Element.ICE);
    }

    public WaterIcemon(int x, int px, int py) {
        super("Wild WaterIcemon", "WaterIcemon Ibu", "WaterIcemon Ibu juga", "WaterIcemon", "WaterIcemon", "WaterIcemon", x, Element.WATER, Element.ICE, px, py, "BYUR BRRR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.WATER, Element.ICE);
    }

    public WaterIcemon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py, int health) {
        super(name, p1name, p2name, p1spc, p2spc,"WaterIcemon", exp, Element.WATER, Element.ICE, px, py, "BYUR BRRR", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.WATER, Element.ICE);
    }

    public WaterIcemon(int x, int px, int py, int health) {
        super("Wild WaterIcemon", "WaterIcemon Ibu", "WaterIcemon Ibu juga", "WaterIcemon", "WaterIcemon", "WaterIcemon", x, Element.WATER, Element.ICE, px, py, "BYUR BRRR", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.WATER, Element.ICE);
    }

    //Constructor buat Load
    public WaterIcemon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py, int health, int cumExp, int maxExp, Vector<Skill> sk) {
        super(name, p1name, p2name, p1spc, p2spc,"WaterIcemon", exp, Element.WATER, Element.GROUND, px, py, "BYUR BRRR", health, cumExp, maxExp);
        skill = sk;
    }
}