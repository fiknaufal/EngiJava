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
}