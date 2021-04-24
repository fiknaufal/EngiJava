public class Groundmon extends Engimon {
    public Groundmon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py) {
        super(name, p1name, p2name, p1spc, p2spc,"Groundmon", exp, Elements.GROUND, Elements.NONE, px, py, "BUMBUMMM");

        skillgacha sg;
        skill = sg.getSkillGen(Elements.GROUND, Elements.NONE);
    }

    public Groundmon(int x, int px, int py) {
        super("Wild Groundmon", "Groundmon Ibu", "Groundmon Ibu juga", "Groundmon", "Groundmon", "Groundmon", x, Elements.GROUND, Elements.NONE, px, py, "BUMBUMMM");

        skillgacha sg;
        skill = sg.getSkillGen(Elements.GROUND, Elements.NONE);
    }
}