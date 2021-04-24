public class Firemon extends Engimon {
    public Firemon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py) {
        super(name, p1name, p2name, p1spc, p2spc,"Firemon", exp, Elements.FIRE, Elements.NONE, px, py, "ROARRR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Elements.FIRE, Elements.NONE);
    }

    public Firemon(int x, int px, int py) {
        super("Wild Firemon", "Firemon Ibu", "Firemon Ibu juga", "Firemon", "Firemon", "Firemon", x, Elements.FIRE, Elements.NONE, px, py, "ROARRR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Elements.FIRE, Elements.NONE);
    }
}