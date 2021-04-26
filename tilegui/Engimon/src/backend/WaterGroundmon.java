package backend;
import java.util.Vector;

public class WaterGroundmon extends Engimon {
    public WaterGroundmon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py) {
        super(name, p1name, p2name, p1spc, p2spc,"WaterGroundmon", exp, Element.WATER, Element.GROUND, px, py, "BUMM BYURR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.WATER, Element.GROUND);
    }

    public WaterGroundmon(int x, int px, int py) {
        super("Wild WaterGroundmon", "WaterGroundmon Ibu", "WaterGroundmon Ibu juga", "WaterGroundmon", "WaterGroundmon", "WaterGroundmon", x, Element.WATER, Element.GROUND, px, py, "BUMM BYURR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.WATER, Element.GROUND);
    }

    public WaterGroundmon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py, int health) {
        super(name, p1name, p2name, p1spc, p2spc,"WaterGroundmon", exp, Element.WATER, Element.GROUND, px, py, "BUMM BYURR", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.WATER, Element.GROUND);
    }

    public WaterGroundmon(int x, int px, int py, int health) {
        super("Wild WaterGroundmon", "WaterGroundmon Ibu", "WaterGroundmon Ibu juga", "WaterGroundmon", "WaterGroundmon", "WaterGroundmon", x, Element.WATER, Element.GROUND, px, py, "BUMM BYURR", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.WATER, Element.GROUND);
    }

    //Constructor buat Load
    public WaterGroundmon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py, int health, int cumExp, int maxExp, Vector<Skill> sk) {
        super(name, p1name, p2name, p1spc, p2spc,"WaterGroundmon", exp, Element.WATER, Element.GROUND, px, py, "BUMM BYURR", health, cumExp, maxExp);
        skill = sk;
    }
}