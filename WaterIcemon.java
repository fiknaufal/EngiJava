public class WaterIcemon extends Engimon {
    public WaterIcemon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py) {
        super(name, p1name, p2name, p1spc, p2spc,"WaterIcemon", exp, Elements.WATER, Elements.ICE, px, py, "BYUR BRRR");

        skillgacha sg;
        skill = sg.getSkillGen(Elements.WATER, Elements.ICE);
    }

    public WaterIcemon(int x, int px, int py) {
        super("Wild WaterIcemon", "WaterIcemon Ibu", "WaterIcemon Ibu juga", "WaterIcemon", "WaterIcemon", "WaterIcemon", x, Elements.WATER, Elements.ICE, px, py, "BYUR BRRR");

        skillgacha sg;
        skill = sg.getSkillGen(Elements.WATER, Elements.ICE);
    }
}