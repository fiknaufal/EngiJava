public class Electricmon extends Engimon {
    public Electricmon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py) {
        super(name, p1name, p2name, p1spc, p2spc,"Electricmon", exp, Elements.ELECTRIC, Elements.NONE, px, py, "AYAYAYAYYY");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Elements.ELECTRIC, Elements.NONE);
    }

    public Electricmon(int x, int px, int py) {
        super("Wild Electricmon", "Electricmon Ibu", "Electricmon Ibu juga", "Electricmon", "Electricmon", "Electricmon", x, Elements.ELECTRIC, Elements.NONE, px, py, "AYAYAYAYYY");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Elements.ELECTRIC, Elements.NONE);
    }
}