public class Watermon extends Engimon {
    public Watermon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py) {
        super(name, p1name, p2name, p1spc, p2spc,"Watermon", exp, Elements.WATER, Elements.NONE, px, py, "BYURBYURR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Elements.WATER, Elements.NONE);
    }

    public Watermon(int x, int px, int py) {
        super("Wild Watermon", "Watermon Ibu", "Watermon Ibu juga", "Watermon", "Watermon", "Watermon", x, Elements.WATER, Elements.NONE, px, py, "BYURBYURR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Elements.WATER, Elements.NONE);
    }
}