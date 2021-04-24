public class Icemon extends Engimon {
    public Icemon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py) {
        super(name, p1name, p2name, p1spc, p2spc,"Icemon", exp, Elements.ICE, Elements.NONE, px, py, "BRRR BRRR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Elements.ICE, Elements.NONE);
    }

    public Icemon(int x, int px, int py) {
        super("Wild Icemon", "Icemon Ibu", "Icemon Ibu juga", "Icemon", "Icemon", "Icemon", x, Elements.ICE, Elements.NONE, px, py, "BRRR BRRR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Elements.ICE, Elements.NONE);
    }
}